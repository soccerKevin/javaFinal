package com.codefellows;

import static spark.Spark.*;

public class ZooApi {

    private static int getPort() {
        return Integer.parseInt(System.getenv().get("PORT"));
    }

    public static void main(String[] args) {
        port(getPort());

        get("/hello/:name", (request, response) -> {
            return "Hello: " + request.params(":name");
        });
    }
}