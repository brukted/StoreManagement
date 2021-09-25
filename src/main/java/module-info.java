module com.group_10.storemanagement {
    requires javafx.controls;
    requires javafx.fxml;

    requires java.sql;

    opens com.group_10.storemanagement to javafx.fxml;
    exports com.group_10.storemanagement;
}