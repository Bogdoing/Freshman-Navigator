module com.example.course2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.example.course2.entity to javafx.base;
    exports com.example.course2.entity;
    opens com.example.course2 to javafx.fxml;
    exports com.example.course2;
}