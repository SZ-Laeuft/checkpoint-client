// HttpNFCServer.java
package org.szlaeuft.checkpoint.nfc;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.szlaeuft.checkpoint.helpers.MessageHelper;
import org.szlaeuft.checkpoint.helpers.NFCHelper;
import org.szlaeuft.checkpoint.managers.StateManager;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class NFCServer {
    private HttpServer server;
    private StateManager stateManager;

    public NFCServer(int port, StateManager stateManager) throws IOException {
        this.stateManager = stateManager;
        server = HttpServer.create(new InetSocketAddress(port), 0);
        server.createContext("/nfc", new NFCHandler());
        server.setExecutor(null); // creates a default executor
    }

    public void start() {
        server.start();
        System.out.println("HTTP server started on port " + server.getAddress().getPort());
    }

    class NFCHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            if (!"POST".equals(exchange.getRequestMethod())) {
                exchange.sendResponseHeaders(405, -1); // Method Not Allowed
                return;
            }

            InputStream inputStream = exchange.getRequestBody();
            byte[] bodyBytes = inputStream.readAllBytes();
            String body = new String(bodyBytes);
            System.out.println("Received NFC POST: " + body);

            Gson gson = new Gson();
            NFCHelper nfc = gson.fromJson(body, NFCHelper.class);
            stateManager.setCurrentState(nfc.getState(), new MessageHelper(nfc));

            String response = "NFC data processed";
            exchange.sendResponseHeaders(200, response.length());
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
}
