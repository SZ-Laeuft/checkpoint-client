module org.szlaeuft.checkpoint {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.java_websocket;
    requires com.google.gson;
    requires jdk.httpserver;

    opens org.szlaeuft.checkpoint to javafx.fxml;
    exports org.szlaeuft.checkpoint;

    exports org.szlaeuft.checkpoint.managers;
    opens org.szlaeuft.checkpoint.managers to javafx.fxml, com.google.gson; // Combined opens directive for both modules

    exports org.szlaeuft.checkpoint.helpers to com.google.gson;
}
