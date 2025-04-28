package org.szlaeuft.checkpoint.nfc;

import javafx.application.Platform;
import org.szlaeuft.checkpoint.managers.StateManager;

import java.net.InetSocketAddress;

public class NFCAdapter {

    private NFCServer webSocketServer;

    public NFCAdapter(StateManager stateManager) {
        // Initialize the WebSocket server
        webSocketServer = new NFCServer(new InetSocketAddress("localhost", 8080));

        // Start the WebSocket server in a separate thread
        startWebSocketServer(stateManager);
    }

    private void startWebSocketServer(StateManager stateManager) {
        // Running the WebSocket server on a separate thread to avoid blocking the main UI thread
        webSocketServer.setStateManager(stateManager);
        webSocketServer.start();
    }
}
