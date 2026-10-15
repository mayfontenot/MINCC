package net.mayware.mincc;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


/**
 * Application Name: MINCC
 * Description: Open-source JavaFX application that uses Java Cryptography Architecture to deliver end-to-end encrypted, peer-to-peer chats without a centralized server's prying eyes.
 * Name Origin: Recursive acronym for MINCC Is Not a Centralized Chat, M was arbitrarily chosen because it is the first letter of the author's name
 * @author May Fontenot (may@mayware.net)
 */
public class App extends Application {
    @Override
    public void start(Stage stage) {
        var javaVersion = SystemInfo.javaVersion();
        var javafxVersion = SystemInfo.javafxVersion();

        var label = new Label("Hello, JavaFX " + javafxVersion + ", running on Java " + javaVersion + ".");
        var scene = new Scene(new StackPane(label), 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}