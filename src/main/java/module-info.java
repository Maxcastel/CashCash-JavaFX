module com.cashcash {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.cashcash to javafx.fxml;
    exports com.cashcash;
}
