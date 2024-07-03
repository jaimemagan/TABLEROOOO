module com.example.tpp {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.tpp to javafx.fxml;
    exports com.example.tpp;
}