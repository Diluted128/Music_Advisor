package wj.company;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Controller {

    HttpClient httpClient;

    Controller() {
        httpClient = HttpClient.newBuilder().build();
    }

     HttpResponse<String> createRequestForTokenAndSendIt() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(Main.accessPoint + "/api/token"))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .POST(HttpRequest.BodyPublishers.ofString("grant_type=authorization_code" +
                        "&code=" + HTTPServer.GRANT +
                        "&redirect_uri=" + HTTPServer.REDIRECTED_URI +
                        "&client_id=" + HTTPServer.CLIENT_ID +
                        "&client_secret=" + HTTPServer.CLIENT_SECRET))
                .build();

        return httpClient.send(request, HttpResponse.BodyHandlers.ofString());

    }

    HttpResponse<String> getResource(String path) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .header("Authorization", "Bearer " + HTTPServer.TOKEN)
                .uri(URI.create(Main.resourceAPI + path))
                .GET()
                .build();

        return httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    }

    void parseJsonToken(HttpResponse<String> response){
        JsonObject json = JsonParser.parseString(response.body()).getAsJsonObject();
        HTTPServer.TOKEN = json.get("access_token").getAsString();
        System.out.println(HTTPServer.TOKEN);
    }

    void parseJsonNewAlbums(HttpResponse<String> response) {

        String artists = "";

        JsonArray JsonAlbums = JsonParser.parseString(response.body()).getAsJsonObject()
                .get("albums").getAsJsonObject()
                .get("items").getAsJsonArray();

        for (JsonElement a : JsonAlbums) {

            System.out.println("\n" + a.getAsJsonObject().get("name").getAsString());

            JsonArray array = a.getAsJsonObject().get("artists").getAsJsonArray();
            for (JsonElement b : array) {
                artists += b.getAsJsonObject().get("name").getAsString() + ", ";
            }
            System.out.println("[" + artists.substring(0,artists.length() - 2) + "]");

            System.out.println(a.getAsJsonObject().get("external_urls").getAsJsonObject()
                    .get("spotify").getAsString());

            artists = "";
        }
    }

    void parseJsonFeaturedPlaylists(HttpResponse<String> response){

        JsonArray items = JsonParser.parseString(response.body()).getAsJsonObject()
                .get("playlists").getAsJsonObject()
                .get("items").getAsJsonArray();

        for (JsonElement a : items){
            System.out.println("\n" + a.getAsJsonObject().get("name").getAsString());
            System.out.println(a.getAsJsonObject()
                    .get("external_urls").getAsJsonObject()
                    .get("spotify").getAsString() + "\n");
        }
    }

    void parseJsonCategories(HttpResponse<String> response){

        JsonArray items = JsonParser.parseString(response.body()).getAsJsonObject()
                .get("categories").getAsJsonObject()
                .get("items").getAsJsonArray();

        for(JsonElement a : items){
            System.out.println(a.getAsJsonObject().get("name").getAsString());
        }
    }

    String findCategoryNameIndex(String categoryName, HttpResponse<String> response){
        JsonArray items = JsonParser.parseString(response.body()).getAsJsonObject()
                .get("categories").getAsJsonObject()
                .get("items").getAsJsonArray();

        for(JsonElement a : items){
            if(a.getAsJsonObject().get("name").getAsString().equals(categoryName)){
                return a.getAsJsonObject().get("id").getAsString();
            }
        }
        return "-";
    }
}
