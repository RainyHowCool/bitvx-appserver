package org.bitvx.AppServer;

import java.nio.charset.StandardCharsets;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

class SetPasswordHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws java.io.IOException {
		String requestURI = exchange.getRequestURI().toString();
		String[] requ1 = requestURI.split("=",-1);
				requ1.hashCode()

		exchange.getResponseHeaders().add("Content-Type", "text/html; charset=UTF-8");
		exchange.getResponseHeaders().add("Set-Cookie", "password=")
		exchange.sendResponseHeaders(201, 0);
		exchange.getResponseBody().write("Done".getBytes(StandardCharsets.UTF_8));
		exchange.close();
    }
}
