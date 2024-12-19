module org.example.airlinesystem {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires java.sql;
    opens org.example.airlinesystem.Controller to javafx.base;

    opens org.example.airlinesystem to javafx.fxml;
    exports org.example.airlinesystem;
    exports org.example.airlinesystem.dao;
    opens org.example.airlinesystem.dao to javafx.fxml;
    exports org.example;
    opens org.example to javafx.fxml;
}