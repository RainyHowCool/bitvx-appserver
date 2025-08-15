package org.bitvx.AppServer.config;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class FirstConfigHandler implements HttpHandler {
	@Override
	public void handle(HttpExchange exchange) throws IOException {
		Headers respHeaders = exchange.getResponseHeaders();
		respHeaders.set("Content-Type", "text/html; charset=utf-8");
		byte[] buffer = new byte[1024];
		int bytesRead;
		exchange.sendResponseHeaders(200, 0);

		try (InputStream in = getClass().getResourceAsStream("/config.html")) {
			try (OutputStream out = exchange.getResponseBody()) {
				while ((bytesRead = in.read(buffer)) != -1) {
					out.write(buffer, 0, bytesRead);
				}
			out.flush();
			}
		}
	}
}
