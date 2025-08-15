package org.bitvx.AppServer;

import java.io.File;
import java.io.IOException;

import org.bitvx.AppServer.config.FirstConfigHandler;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class RedirectHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        File fileOk = new File("./bitvx.ok");
        if (!fileOk.exists()) {
            Headers respHeaders = exchange.getResponseHeaders();
            respHeaders.set("Content-Type", "text/html; charset=utf-8");
            new FirstConfigHandler().handle(exchange);
        }
    }
}
