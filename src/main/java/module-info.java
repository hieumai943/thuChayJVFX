module com.example.hocjvfx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.hocjvfx to javafx.fxml;
    exports com.example.hocjvfx;
    exports MAIN;
    opens MAIN to javafx.fxml;
}