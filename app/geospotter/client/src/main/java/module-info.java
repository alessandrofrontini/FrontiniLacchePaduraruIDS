module com.camerino.ids.fps.client {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.camerino.ids.fps.client to javafx.fxml;
    exports com.camerino.ids.fps.client;
    exports com.camerino.ids.fps.client.visual;
    opens com.camerino.ids.fps.client.visual to javafx.fxml;
    exports com.camerino.ids.fps.client.utils;
    opens com.camerino.ids.fps.client.utils to javafx.fxml;

    requires com.camerino.ids.core;
    requires java.net.http;
    requires com.fasterxml.jackson.databind;
    requires java.sql;
    requires org.hibernate.orm.core;
}