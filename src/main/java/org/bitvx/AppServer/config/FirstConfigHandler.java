package org.bitvx.AppServer.config;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

public class FirstConfigHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
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
