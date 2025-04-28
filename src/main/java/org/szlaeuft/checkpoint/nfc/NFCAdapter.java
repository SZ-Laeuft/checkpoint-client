package org.szlaeuft.checkpoint.nfc;

import javafx.application.Platform;

import java.net.InetSocketAddress;

public class NFCAdapter {

    private NFCServer webSocketServer;

    public NFCAdapter() {
        // Initialize the WebSocket server
        webSocketServer = new NFCServer(new InetSocketAddress("localhost", 8080));

        // Start the WebSocket server in a separate thread
        startWebSocketServer();
    }

    private void startWebSocketServer() {
        // Running the WebSocket server on a separate thread to avoid blocking the main UI thread
        webSocketServer.start();
    }
}
