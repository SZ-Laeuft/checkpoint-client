module org.szlaeuft.checkpoint {
    requires javafx.controls;
    requires javafx.fxml;
    requires Java.WebSocket;


    opens org.szlaeuft.checkpoint to javafx.fxml;
    exports org.szlaeuft.checkpoint;
    exports org.szlaeuft.checkpoint.managers;
    opens org.szlaeuft.checkpoint.managers to javafx.fxml;
}