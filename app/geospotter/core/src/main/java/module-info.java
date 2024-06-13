module com.camerino.ids.core {
    requires org.hibernate.orm.core;
    requires jakarta.persistence;
    requires java.desktop;
    requires com.fasterxml.jackson.annotation;
    exports com.camerino.ids.core.data.azioni;
    exports com.camerino.ids.core.data.contenuti;
    exports com.camerino.ids.core.data.segnalazioni;
    exports com.camerino.ids.core.data.utenti;
    exports com.camerino.ids.core.data.utils;
    exports com.camerino.ids.core.persistence;
}
