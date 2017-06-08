package com.codefellows;

import static spark.Spark.*;

public class Application {

    private static int getPort() {
        return Integer.parseInt(System.getenv().get("PORT"));
    }

    public static void main(String[] args) {
        port(5000);

        get("/hello/:name", (request, response) -> {
            return "Hello: " + request.params(":name");
        });
    }
}