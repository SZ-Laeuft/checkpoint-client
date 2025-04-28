module org.szlaeuft.checkpoint {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.eclipse.jetty.websocket.api;
    requires org.eclipse.jetty.server;
    requires org.eclipse.jetty.websocket.server;
    requires org.eclipse.jetty.websocket.servlet;


    opens org.szlaeuft.checkpoint to javafx.fxml;
    exports org.szlaeuft.checkpoint;
    exports org.szlaeuft.checkpoint.managers;
    opens org.szlaeuft.checkpoint.managers to javafx.fxml;
}