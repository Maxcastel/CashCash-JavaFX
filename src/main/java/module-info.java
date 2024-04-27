module com.cashcash {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive java.sql;

    opens com.cashcash.controllers to javafx.fxml;
    exports com.cashcash;
}
