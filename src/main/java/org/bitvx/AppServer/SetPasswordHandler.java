package org.bitvx.AppServer;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.nio.charset.StandardCharsets;

class SetPasswordHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws java.io.IOException {
	String requestURI = exchange.getRequestURI().toString();
	String[] requ1 = requestURI.split("=",-1);
	System.out.println("[" + requ1[1] + "]");
	exchange.getResponseHeaders.add("Content-Type", "text/html; charset=UTF-8");
	exchange.sendResponseHeaders(201, 0);
	exchange.getResponseBody().write("Done".getBytes(StandardCharsets.UTF_8));
	exchange.close();
    }
}
