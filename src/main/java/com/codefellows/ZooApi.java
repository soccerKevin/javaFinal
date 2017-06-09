package com.codefellows;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;

import static spark.Spark.get;
import static spark.Spark.port;

public class ZooApi {
    private static final Logger LOG = LoggerFactory.getLogger(ZooApi.class);

    private static int getPort() {
        return Integer.parseInt(System.getenv().get("PORT"));
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        int port = getPort();

        LOG.info("Starting API server on port " + port);
        port(port);

        get("/hello/:name", (request, response) -> {
            return "Hello: " + request.params(":name");
        });

        Connection con = ZooUtils.getConnection();
        ZooUtils.viewTable(con);

        con.close();
    }
}