module com.camerino.ids.fps.client {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.camerino.ids.fps.client to javafx.fxml;
    exports com.camerino.ids.fps.client;


}