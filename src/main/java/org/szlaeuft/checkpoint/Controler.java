package org.szlaeuft.checkpoint;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.util.Duration;
import org.szlaeuft.checkpoint.managers.DebugManager;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Enumeration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Controler {

    DebugManager dm = Application.dm;

    int da = 0;


    @FXML
    private void info(ActionEvent event) {
        String ip = "Unavailable";
        String hostname;
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            while (interfaces.hasMoreElements()) {
                NetworkInterface iface = interfaces.nextElement();

                try {
                    if (!iface.isUp() || iface.isLoopback() || iface.isVirtual()) continue;
                } catch (SocketException e) {
                    throw new RuntimeException(e);
                }

                String name = iface.getName().toLowerCase();
                if (!(name.startsWith("eth") || name.startsWith("en") || name.startsWith("eno"))) continue;

                Enumeration<InetAddress> addresses = iface.getInetAddresses();
                while (addresses.hasMoreElements()) {
                    InetAddress addr = addresses.nextElement();
                    if (addr instanceof Inet4Address && !addr.isLoopbackAddress()) {
                        ip = addr.getHostAddress();
                        break;
                    }
                }
                if (!ip.equals("Unavailable")) break;
            }
        } catch (SocketException e) {e.printStackTrace();}

        try {
            hostname = InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {throw new RuntimeException(e);}


        String time = "Unavailable";

        try {
            Process process = Runtime.getRuntime().exec("ping -c 1 192.168.68.68"); // Use "ping -n 1" on Windows
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            Pattern pattern = Pattern.compile("time=([\\d\\.]+) ms"); // Regex to match: time=23.4 ms

            while ((line = reader.readLine()) != null) {
                Matcher matcher = pattern.matcher(line);
                if (matcher.find()) {
                    time = matcher.group(1) + " ms";
                    break;
                }
            }
            process.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }

        String special = "";
        if(da == 5){
            special = "DING DING DING DING! DEBUG MODE ALLOWEDDDDD\n\n";
            debug_btn.setVisible(true);
            debug_btn.setDisable(false);
        }


        Alert alert = new Alert(AlertType.INFORMATION);
        alert.initOwner(dm.getStage());
        alert.setTitle("Info");
        alert.setHeaderText(null);
        alert.setContentText("IP: " + ip +"\n" +
                "Hostname: " + hostname + "\n" +
                "PTS: " + time + "\n\n" +
                special +
                "(c) eduard.gavrila@sz-ybbs.ac.at");
        alert.showAndWait();

        da++;
    }


    @FXML
    private void debug_toggle(ActionEvent event) {
        dm.toggleDebug();
    }

    @FXML
    private void debug_fullscreen_toggle(ActionEvent event) {
        dm.toggleFullscreen();
    }

    @FXML
    private void debug_state_toggle(ActionEvent event) {
        dm.toggleState();
    }

    @FXML
    private Menu menutitle;

    @FXML
    MenuItem debug_btn;

    @FXML
    public void initialize() {
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e ->
                menutitle.setText( "SZ-Läuft Checkpoint " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
        ),
                new KeyFrame(Duration.seconds(1))
        );
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();

        debug_btn.setVisible(false);
        debug_btn.setDisable(true);
    }



}
