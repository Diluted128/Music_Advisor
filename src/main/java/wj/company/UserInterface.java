package wj.company;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;

public class UserInterface {

    static boolean isUserAuthorized = false;
    static Scanner scanner = new Scanner(System.in);

    void userInterface() throws IOException, InterruptedException {

        Controller controller = new Controller();
        HttpResponse<String> response;

        String choice;
        boolean end = false;

        while (!end) {

            choice = scanner.nextLine();

            if (choice.equals("exit")) {
                System.out.println("---GOODBYE!---");
                end = true;
            }
            else if (choice.equals("auth") && !isUserAuthorized) { authorizeUser(); }
            else if (!isUserAuthorized) { System.out.println("Please, provide access for application."); }
            else {
                switch (choice) {
                    case "new":
                        response = controller.getResource("/v1/browse/new-releases");
                        controller.parseJsonNewAlbums(response);
                        break;
                    case "featured":
                        response = controller.getResource("/v1/browse/featured-playlists");
                        controller.parseJsonFeaturedPlaylists(response);
                        break;
                    case "categories":
                        response = controller.getResource("/v1/browse/categories");
                        controller.parseJsonCategories(response);
                        break;
                }
                if(choice.contains("playlists ")){
                    response = controller.getResource("/v1/browse/categories");
                    String name = controller.findCategoryNameIndex(choice.substring(choice.indexOf(" ") + 1),response);
                    response = controller.getResource("/v1/browse/categories/" + name + "/playlists");

                    if(response.statusCode() != 404) controller.parseJsonFeaturedPlaylists(response);
                    else System.out.println("Unknown category name.");

                }
            }
        }
    }

    void authorizeUser() throws IOException, InterruptedException {

        CountDownLatch countDownLatch = new CountDownLatch(1);

        System.out.println("https://accounts.spotify.com/authorize?" +
                "client_id=" + HTTPServer.CLIENT_ID +
                "&redirect_uri=" + HTTPServer.REDIRECTED_URI +
                "&response_type=code");

         new HTTPServer("/", HTTPServer.createHandler(), countDownLatch);

        HTTPServer.start();

        System.out.println("waiting for code...");

        countDownLatch.await();

        System.out.println("making http request for access_token...");
        Controller httpRequest = new Controller();

        System.out.println("response: ");
        HttpResponse<String> response = null;

        try {
            response = httpRequest.createRequestForTokenAndSendIt();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        assert response != null;
        new Controller().parseJsonToken(response);

        System.out.println("---SUCCESS---");
        isUserAuthorized = true;
    }
}
