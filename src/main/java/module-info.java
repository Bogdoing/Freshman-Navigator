module com.example.course2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.web;
    requires jdk.jsobject;

    opens com.example.course2.entity to javafx.base;
    exports com.example.course2.entity;
    opens com.example.course2 to javafx.fxml;
    exports com.example.course2;
    exports com.example.course2.dao;
    opens com.example.course2.dao to javafx.fxml;
}