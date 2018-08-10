package presentation;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private int sizeX = 900;
    private int sizeY = 900;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("intro.fxml"));
        primaryStage.setTitle("Saka in a jar");
        Scene startScene = new Scene(root, sizeX, sizeY);
        String stylesheetPath = this.getClass().getResource("assets/stylesheet.css").toExternalForm();
        startScene.getStylesheets().add(stylesheetPath);
        primaryStage.setScene(startScene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
