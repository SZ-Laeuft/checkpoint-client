package org.szlaeuft.checkpoint.nfc;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.websocket.server.WebSocketHandler;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.WebSocketListener;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;

import java.net.InetSocketAddress;

public class NFCServer {

    private Server server;

    public NFCServer(InetSocketAddress address) {
        // Create a Jetty server with a WebSocket handler
        server = new Server(address.getPort());

        WebSocketHandler wsHandler = new WebSocketHandler() {
            @Override
            public void configure(WebSocketServletFactory factory) {
                // Register WebSocket listener (handle connections)
                factory.register(MyWebSocketListener.class);
            }
        };

        server.setHandler(wsHandler);
    }

    public void onOpen(Session session) {
        System.out.println("New connection: " + session.getRemoteAddress());
    }

    public void onClose(Session session, int statusCode, String reason) {
        System.out.println("Closed connection: " + session.getRemoteAddress());
    }

    public void onMessage(Session session, String message) {
        System.out.println("Message from client: " + message);
    }

    public void onError(Session session, Throwable throwable) {
        throwable.printStackTrace();
    }

    public void onStart() {
        System.out.println("WebSocket server started successfully!");
    }

    public void startServer() {
        try {
            // Start the Jetty WebSocket server
            server.start();
            onStart();
            server.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // WebSocket listener class for handling WebSocket events
    public static class MyWebSocketListener implements WebSocketListener {

        private Session session;

        @Override
        public void onWebSocketConnect(Session session) {
            this.session = session;
            System.out.println("WebSocket connected: " + session.getRemoteAddress());
        }

        @Override
        public void onWebSocketText(String message) {
            System.out.println("Received message: " + message);
            // Optionally send a response back to the client
            try {
                session.getRemote().sendString("Echo: " + message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onWebSocketBinary(byte[] payload, int offset, int length) {
            // Handle binary messages (if needed)
        }

        @Override
        public void onWebSocketClose(int statusCode, String reason) {
            System.out.println("WebSocket closed: " + reason);
        }

        @Override
        public void onWebSocketError(Throwable cause) {
            cause.printStackTrace();
        }
    }
}
