package wj.company;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;

import java.io.IOException;

public class Main {

    @Parameter(names = {"-a","-access"}, description = "Part of url to authenticate user")
    public static String accessPoint = "https://accounts.spotify.com";

    @Parameter(names = {"-r","-resource"}, description = "Part url to manage Spotify resources")
    public static String resourceAPI = "https://api.spotify.com";

    public static void main(String[] args) throws IOException, InterruptedException {

        JCommander.newBuilder()
                .addObject(new Main())
                .build()
                .parse(args);

        new UserInterface().userInterface();
    }
}
