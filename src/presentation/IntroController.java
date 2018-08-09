package presentation;

import com.jfoenix.controls.JFXButton;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;

public class IntroController {

    @FXML
    private AnchorPane introRoot;
    @FXML
    private ImageView introImage;
    @FXML
    private GridPane textGrid;
    @FXML
    private Text introHeader;
    @FXML
    private Text introIntroduction;
    @FXML
    private Text introName;
    @FXML
    private JFXButton continueButton;

    public IntroController() {

    }

    @FXML
    private void initialize() {
        setRoot();
        setIntroImage();
        setGrid();
        setText();
    }

    private void setRoot(){
        introRoot.setMaxSize(900,900);
        introRoot.setPrefSize(900,900);
    }

    private void setIntroImage(){
        introImage.setFitHeight(900);
        introImage.setFitWidth(280);
        introImage.setLayoutX(60.0);
    }

    private void setGrid() {
        textGrid.setLayoutX(610.0);
        textGrid.setLayoutY(200.0);
        textGrid.setMaxWidth(280.0);
        textGrid.prefHeight(280.0);
        textGrid.setVgap(20.0);
        continueButton.setVisible(false);
        continueButton.setFocusTraversable(false);
    }

    private void setText() {
        displayAnimatedText(new Duration(1000));
    }

    private void displayAnimatedText(Duration interval) {
        String[] textStrings = {"Hello,", "MY NAME IS", "Saka"};
        Text[] texts = {introHeader, introIntroduction, introName};
        ArrayList<Timeline> timelines = new ArrayList<>();
        for (int i = 0; i < texts.length; i++) {
            Timeline timeline = new Timeline();
            timeline.getKeyFrames().add(stringFrame(textStrings[i], interval, texts[i].textProperty()));
            timeline.setCycleCount(textStrings[i].length());
            timelines.add(timeline);
        }
        timelines.get(0).setOnFinished(event -> timelines.get(1).play());
        timelines.get(1).setOnFinished(event -> timelines.get(2).play());
        timelines.get(2).setOnFinished(event ->
                continueButton.setVisible(true));
        Platform.runLater(timelines.get(0)::play);
    }

    private KeyFrame stringFrame(String label, Duration duration, StringProperty outputProperty) {
        double actualDuration = duration.toMillis() / (double) label.length();
        KeyFrame stringFrame = new KeyFrame(new Duration(actualDuration), new EventHandler<>() {
            int sublistSize = 1; //skipping empty

            @Override
            public void handle(ActionEvent event) {
                outputProperty.set(label.substring(0, sublistSize++));
            }
        });
        return stringFrame;
    }

    @FXML
    private void continueButtonEvent(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;
        if (event.getSource() == continueButton) {
            stage = (Stage) continueButton.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("tabs.fxml"));
            String stylesheetPath = this.getClass().getResource("assets/stylesheet.css").toExternalForm();
            Scene scene = new Scene(root);
            scene.getStylesheets().add(stylesheetPath);
            stage.setScene(scene);
            stage.show();
        }
    }

}

