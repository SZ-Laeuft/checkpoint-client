// NFCAdapter.java
package org.szlaeuft.checkpoint.nfc;

import org.szlaeuft.checkpoint.managers.StateManager;

public class NFCAdapter {

    private NFCServer httpServer;

    public NFCAdapter(StateManager stateManager) {
        try {
            httpServer = new NFCServer(8080, stateManager);
            startHttpServer();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void startHttpServer() {
        new Thread(() -> httpServer.start()).start();
    }
}
