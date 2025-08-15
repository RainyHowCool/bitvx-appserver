package org.bitvx.AppServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bitvx.AppServer.resource.CSSResourceHandler;
import org.bitvx.AppServer.resource.JSResourceHandler;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

//TIP 要<b>运行</b>代码，请按 <shortcut actionId="Run"/> 或
// 点击装订区域中的 <icon src="AllIcons.Actions.Execute"/> 图标。
public class Main {
    private static int port;
    public static final Logger logger = Logger.getGlobal();
	public static boolean ALLOW_EXIT = false;
    public static void main(String[] args) throws IOException {
		port = 8088;
		FileHandler fileHandler = new FileHandler("appsvr.log");
		logger.addHandler(fileHandler);
		logger.setLevel(Level.ALL);
		HttpServer httpServer = HttpServer.create(new InetSocketAddress(port), 0);
		logger.info(String.format("Server started in port %d", port));

			// / is means for all
		if (ALLOW_EXIT) {
			httpServer.createContext("/i_just_wanna_exit", (HttpExchange exchange) -> {
				logger.warning("Server manually stopped by user!");
				System.exit(0);
			});
		}

		httpServer.createContext("/setPassword", new SetPasswordHandler());
		httpServer.createContext("/dashboard", new DashboardHandler());
		httpServer.createContext("/", new RedirectHandler());
		// Register HTML Resources Handler
		httpServer.createContext("/res/js", new JSResourceHandler());
		httpServer.createContext("/res/css", new CSSResourceHandler());
		httpServer.start();
    }

	int getPort() {
		return port;
	}
}
