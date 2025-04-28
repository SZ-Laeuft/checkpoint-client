package org.szlaeuft.checkpoint.nfc;

import hu.whiterabbit.rc522forpi4j.model.card.Card;
import hu.whiterabbit.rc522forpi4j.rc522.RC522Client;
import hu.whiterabbit.rc522forpi4j.rc522.RC522ClientImpl;
import javafx.application.Platform;
import org.szlaeuft.checkpoint.helpers.MessageHelper;
import org.szlaeuft.checkpoint.managers.StateManager;

import java.util.Arrays;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class NFCTest {
    byte[] lastcard = null;

    private ScheduledExecutorService scheduler;

    public void start(StateManager sm) {
        System.out.println("Starting to read card data");
        scheduler = Executors.newSingleThreadScheduledExecutor();

        scheduler.scheduleAtFixedRate(() -> {
            final RC522Client rc522Client = RC522ClientImpl.createInstance();

            byte[] card = rc522Client.readCardTag();

            if (card != null && card != this.lastcard) {
                this.lastcard = card;

                System.out.println("Card data: " + card + "\n");

                sm.setCurrentState("success", new MessageHelper(Arrays.toString(card)));

            }
        }, 0, 500, TimeUnit.MILLISECONDS);
    }

    public void stop() {
        if (scheduler != null && !scheduler.isShutdown()) {
            scheduler.shutdown();
        }
    }

    public void test(StateManager sm) {
        final RC522Client rc522Client = RC522ClientImpl.createInstance();

        System.out.println("Starting to read card data");

        byte[] lastcard = null;

        try {
            while (true) {
                byte[] card = rc522Client.readCardTag();

                if (card != null && card != lastcard) {
                    lastcard = card;

                    sm.setCurrentState("success", new MessageHelper(Arrays.toString(card)));
                    System.out.println("Card data: " + card + "\n");
                    Thread.sleep(490);
                }

                Thread.sleep(10);
            }
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}
