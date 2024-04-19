module com.camerino.ids.fps.geospotter.client {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.camerino.ids.fps.geospotter.client to javafx.fxml;
    exports com.camerino.ids.fps.geospotter.client;
}