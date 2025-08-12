package org.bitvx.AppServer;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.logging.Logger;
import java.util.logging.FileHandler;
import java.util.logging.Level;

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
	httpServer.createContext("/i_just_wanna_exit", new HttpHandler() {
		@Override
		public void handle(HttpExchange exchange) throws IOException {
		    logger.warning("Server manually stopped by user!");
		    System.exit(0);
		}
	});

	httpServer.createContext("/setPassword", new SetPasswordHandler());
        httpServer.createContext("/", new RedirectHandler());
        httpServer.start();
    }
}
