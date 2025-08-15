package org.bitvx.AppServer.resource;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class CSSResourceHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        exchange.getResponseHeaders().set("Content-Type", "text/css; charset=UTF-8");
        exchange.sendResponseHeaders(200, 0);

        try {
            if (exchange.getRequestURI().toString().contains("..")) {
                throw new IOException(String.format("You said right because path '%s' is invaild!", exchange.getRequestURI().toString()));
            }
        } catch (IOException e) {
            exchange.getResponseBody().write(e.getMessage().getBytes(StandardCharsets.UTF_8));
            exchange.getResponseBody().flush();
            exchange.close();
        }

        InputStream stream = this.getClass().getResourceAsStream(exchange.getRequestURI().toString());
        byte[] css_all = stream.readAllBytes();
        exchange.getResponseBody().write(css_all);
        exchange.getResponseBody().flush();
        exchange.close();
    }

}