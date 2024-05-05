module com.cashcash {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive java.sql;
    requires transitive javafx.graphics;

    opens com.cashcash.controllers to javafx.fxml;
    exports com.cashcash;
}