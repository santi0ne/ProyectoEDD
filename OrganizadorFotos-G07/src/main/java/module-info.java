module ec.edu.espol.util {
    requires javafx.controls;
    requires javafx.fxml;

    opens ec.edu.espol.util to javafx.fxml;
    exports ec.edu.espol.util;
}

