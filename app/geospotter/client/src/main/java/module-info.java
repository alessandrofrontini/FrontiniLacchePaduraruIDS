module com.camerino.ids.fps.client {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.camerino.ids.core;

    opens com.camerino.ids.fps.client to javafx.fxml;
    exports com.camerino.ids.fps.client;
}