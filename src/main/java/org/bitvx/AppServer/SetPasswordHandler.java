package org.bitvx.AppServer;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

class SetPasswordHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws java.io.IOException {
	exchange.sendResponseHeaders(201, 0);
	String requestURI = exchange.getRequestURI().toString();
	String[] requ1 = requestURI.split("=",-1);
	System.out.println("[" + requ1[1] + "]");
    }
}
