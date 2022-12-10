module com.example.cs242calc {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.cs242calc to javafx.fxml;
    exports com.example.cs242calc;
}