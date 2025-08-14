package org.bitvx.AppServer.resource;

import java.io.IOException;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class JSResourceHeader implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        exchange.getResponseHeaders().set("Content-Type", "text/js; charset=UTF-8");
        exchange.sendResponseHeaders(200, 0);
    }

}