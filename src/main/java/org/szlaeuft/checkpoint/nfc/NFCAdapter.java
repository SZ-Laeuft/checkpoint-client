package org.szlaeuft.checkpoint.nfc;

import javafx.application.Platform;

import java.net.InetSocketAddress;

public class NFCAdapter {

    private NFCServer nfcserver;

    public NFCAdapter() {
        // Initialize the WebSocket server
        nfcserver = new NFCServer(new InetSocketAddress("localhost", 6969));
        // Start the WebSocket server in a separate thread
        startWebSocketServer();
    }

    private void startWebSocketServer() {
        // Running the WebSocket server on a separate thread to avoid blocking the main UI thread
        new Thread(() -> {
            nfcserver.startServer();
        }).start();
    }
}
