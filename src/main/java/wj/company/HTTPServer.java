package wj.company;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.CountDownLatch;

public class HTTPServer {

    public final static String CLIENT_ID = "1ac1274ae76e4beba32f969999b1392d";
    public final static String CLIENT_SECRET = "4a4d9ebb5ddd43a1a231104b1173b619";
    public final static String PORT = "8080";
    public final static String REDIRECTED_URI = "http://localhost:8080";

    public static String GRANT = null;
    public static String TOKEN = null;

    private static HttpServer httpServer = null;
    private static CountDownLatch countDownLatch;

    HTTPServer(String context, HttpHandler httpHandler, CountDownLatch countDownLatch) throws IOException {
        HTTPServer.countDownLatch = countDownLatch;
        httpServer = HttpServer.create(new InetSocketAddress(8080), 0);
        httpServer.createContext(context, httpHandler);

        httpServer.setExecutor(null);
    }

    public static void start(){
        httpServer.start();
    }

    public static void stop(){
        countDownLatch.countDown();
        httpServer.stop(1);
    }

    public static HttpHandler createHandler() {
        return exchange -> {
            String authorizationMessage;
            int operationCode;
            String mess = exchange.getRequestURI().getQuery();
            System.out.println(mess);
            if(mess!= null && mess.contains("code")){
                System.out.println("code received");
                GRANT = mess.substring(5);
                authorizationMessage = "Got the code. Return back to your program.";
                operationCode = 200;
                sendResponse(exchange,operationCode,authorizationMessage);
                stop();
            }
            else{
                authorizationMessage = "Authorization code not found. Try again.";
                operationCode = 400;
                sendResponse(exchange,operationCode,authorizationMessage);
            }
        };
    }
    public static void sendResponse(HttpExchange exchange, int operationCode, String message) throws IOException {
        System.out.println("V: ");
        exchange.sendResponseHeaders(operationCode, message.length());
        exchange.getResponseBody().write(message.getBytes());
        exchange.getResponseBody().close();
    }
}
