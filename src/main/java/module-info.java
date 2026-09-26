module com.example.parcial1linguaplus {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.parcial1linguaplus to javafx.fxml;
    exports com.example.parcial1linguaplus;
}