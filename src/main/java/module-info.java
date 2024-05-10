module com.cashcash {
    requires javafx.controls;
    requires javafx.fxml;
    requires  java.sql;
    requires transitive javafx.graphics;
    requires spring.security.crypto;
    requires itextpdf;

    opens com.cashcash.controllers to javafx.fxml;
    opens com.cashcash.entities to javafx.base;
    opens com.cashcash.repositories to javafx.base;
    exports com.cashcash;
}