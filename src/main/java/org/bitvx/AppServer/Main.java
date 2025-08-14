package org.bitvx.AppServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bitvx.AppServer.resource.CSSResourceHeader;
import org.bitvx.AppServer.resource.JSResourceHeader;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

//TIP 要<b>运行</b>代码，请按 <shortcut actionId="Run"/> 或
// 点击装订区域中的 <icon src="AllIcons.Actions.Execute"/> 图标。
public class Main {
    public static final int port = 8088;
    public static Logger logger = Logger.getGlobal();
    public static void main(String[] args) throws IOException {
		FileHandler fileHandler = new FileHandler("appsvr.log");
		logger.addHandler(fileHandler);
		logger.setLevel(Level.ALL);
		HttpServer httpServer = HttpServer.create(new InetSocketAddress(port), 0);
		logger.info("Server started in port 8088");

			// / is means for all
		httpServer.createContext("/i_just_wanna_exit", (HttpExchange exchange) -> {
			logger.warning("Server manually stopped by user!");
			System.exit(0);
		});

		httpServer.createContext("/setPassword", new SetPasswordHandler());
		httpServer.createContext("/", new RedirectHandler());
		// Register HTML Resources Handler
		httpServer.createContext("/res/js", new JSResourceHeader());
		httpServer.createContext("/res/css", new CSSResourceHeader());
		httpServer.start();
    }
}
