package org.szlaeuft.checkpoint.nfc;

import javafx.application.Platform;

import java.net.InetSocketAddress;

public class NFCAdapter {

    private NFCServer nfcServer;

    public NFCAdapter() {
        // Initialize the Jetty WebSocket server
        nfcServer = new NFCServer(new InetSocketAddress("localhost", 6969));

        // Start the WebSocket server in a separate thread
        startWebSocketServer();
    }

    private void startWebSocketServer() {
        // Running the WebSocket server on a separate thread to avoid blocking the main UI thread
        new Thread(() -> {
            nfcServer.startServer();
        }).start();
    }
}
