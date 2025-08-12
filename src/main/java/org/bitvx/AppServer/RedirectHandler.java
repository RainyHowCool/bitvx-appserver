package org.bitvx.AppServer;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.bitvx.AppServer.config.FirstConfigHandler;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

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
