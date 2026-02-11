package nut;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 * This class serves as an alternative entry point for the JavaFX application.
 * This class is particularly useful when running the application as a JAR file.
 * It delegates to the Main class to start the application.
 */
public class Launcher {
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
