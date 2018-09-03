package presentation;

import com.jfoenix.controls.JFXTabPane;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Side;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;

import java.io.IOException;
import java.net.URL;

public class TabController {

    @FXML
    private JFXTabPane tabContainer;

    @FXML
    private Tab equipmentTab;

    @FXML
    private Tab buildTab;

    @FXML
    private Tab trainingTab;

    @FXML
    private Tab masteryTab;

    @FXML
    private AnchorPane equipmentPane;

    @FXML
    private AnchorPane buildPane;

    @FXML
    private AnchorPane trainingPane;

    @FXML
    private AnchorPane masteryPane;

    private double tabWidth = 100.0;
    public static int lastSelectedTabIndex = 0;

    public TabController() {

    }

    @FXML
    private void initialize() {
        setTabContainer();
        equipmentTab.setStyle("-fx-background-image: url(\"presentation/assets/selectionbackground.png\")");
    }

    private void setTabContainer() {
        tabContainer.setSide(Side.LEFT);
        tabContainer.setTabMinWidth(tabWidth);
        tabContainer.setTabMaxWidth(tabWidth);
        tabContainer.setTabMinHeight(tabWidth);
        tabContainer.setTabMaxHeight(tabWidth);
        tabContainer.setRotateGraphic(true);
        setTab(equipmentTab, "Equipment", "presentation/assets/cogsmall.png");
        setTab(buildTab, "Build", "presentation/assets/cogsmall.png");
        setTab(trainingTab, "Training", "presentation/assets/cogsmall.png");
        setTab(masteryTab, "Masteries", "presentation/assets/cogsmall.png");

        setAnchorPane(equipmentPane, "equipment_tab.fxml");
    }

    private void setTab(Tab tab, String title, String iconPath) {
        double imageWidth = 40.0;
        ImageView imageView = new ImageView(new Image(iconPath));
        imageView.setFitHeight(imageWidth);
        imageView.setFitWidth(imageWidth);
        BorderPane tabPane = new BorderPane();
        tabPane.setRotate(90.0);
        tabPane.setMaxWidth(tabWidth);
        tabPane.setCenter(imageView);
        tabPane.setBottom(setLabel(title));
        tab.setGraphic(tabPane);
        tab.setOnSelectionChanged(replaceBackgroundColorHandler);
    }

    private Label setLabel(String title) {
        Label label = new Label(title);
        label.setMaxWidth(tabWidth - 20);
        label.setPadding(new Insets(5, 0, 0, 0));
        label.setStyle("-fx-text-fill: white; -fx-font-size: 10pt; -fx-font-weight: bold;");
        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(1.5);
        dropShadow.setOffsetX(1.0);
        dropShadow.setOffsetY(1.0);
        dropShadow.setColor(Color.rgb(0, 0, 0, 0.8));
        label.setTextAlignment(TextAlignment.CENTER);
        return label;
    }

    private EventHandler<Event> replaceBackgroundColorHandler = event -> {
        lastSelectedTabIndex = tabContainer.getSelectionModel().getSelectedIndex();
        Tab currentTab = (Tab) event.getTarget();
        if (currentTab.isSelected()) {
            currentTab.setStyle("-fx-background-image: url(\"presentation/assets/selectionbackground.png\");");
        } else {
            currentTab.setStyle("-fx-background-color: -fx-default-tab-color;");
        }
    };

    private void setAnchorPane(AnchorPane pane, String resourceName) {
        URL resourceURL = this.getClass().getResource(resourceName);
        if (pane != null && resourceURL != null) {
            try {
                Parent contentView = FXMLLoader.load(resourceURL);
                pane.getChildren().add(contentView);
                AnchorPane.setTopAnchor(contentView, 0.0);
                AnchorPane.setBottomAnchor(contentView, 0.0);
                AnchorPane.setLeftAnchor(contentView, 0.0);
                AnchorPane.setRightAnchor(contentView, 0.0);
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
    }

}
