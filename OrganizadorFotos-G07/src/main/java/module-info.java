module ec.edu.espol.util {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires java.desktop;
    

    opens ec.edu.espol.util to javafx.fxml;
    opens ec.edu.espol.classes;
    exports ec.edu.espol.util;
}

