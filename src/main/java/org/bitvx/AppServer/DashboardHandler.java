package org.bitvx.AppServer;

import java.io.File;
import java.io.IOException;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class DashboardHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        exchange.getResponseHeaders().set("Content-Type", "text/html; charset=UTF-8");
        exchange.sendResponseHeaders(200, 0);

        File fileOk = new File("./bitvx.ok");
        if (!fileOk.exists()) {
            fileOk.createNewFile();
        }

        exchange.getResponseBody().write("Lucky!".getBytes());
        exchange.getResponseBody().flush();
        exchange.close();
    }
}