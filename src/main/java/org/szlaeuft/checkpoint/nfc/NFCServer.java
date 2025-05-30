package org.szlaeuft.checkpoint.nfc;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;
import org.szlaeuft.checkpoint.helpers.MessageHelper;
import org.szlaeuft.checkpoint.helpers.NFCHelper;
import org.szlaeuft.checkpoint.managers.StateManager;

import java.net.InetSocketAddress;

public class NFCServer extends WebSocketServer {
    StateManager stateManager;

    public NFCServer(InetSocketAddress address) {
        super(address);
    }

    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) {
        System.out.println("New connection: " + conn.getRemoteSocketAddress());
    }

    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {
        System.out.println("Closed connection: " + conn.getRemoteSocketAddress());
    }

    @Override
    public void onMessage(WebSocket conn, String message) {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();

        Gson gson = builder.create();
        NFCHelper nfcstate = gson.fromJson(message, NFCHelper.class);
        System.out.println(nfcstate.toString());
        this.execute(nfcstate);
    }

    @Override
    public void onError(WebSocket conn, Exception ex) {
        ex.printStackTrace();
    }

    @Override
    public void onStart() {
        System.out.println("WebSocket server started successfully!");
    }

    public void startServer(StateManager sm) {
        new Thread(this).start();
        stateManager = sm;
    }

    public void setStateManager(StateManager stateManager) {
        this.stateManager = stateManager;
    }

    public void execute (NFCHelper nfc) {
        stateManager.setCurrentState(nfc.getState(), new MessageHelper(nfc));
    }
}
