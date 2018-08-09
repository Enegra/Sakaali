package presentation;

import com.jfoenix.controls.JFXTabPane;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Side;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.TextAlignment;

public class TabController {

    @FXML
    private JFXTabPane tabContainer;

    @FXML
    private Tab equipmentTab;

    @FXML
    private Tab buildTab;

    private double tabWidth = 100.0;
    public static int lastSelectedTabIndex = 0;

    public TabController(){

    }

    @FXML
    private void initialize(){
        setTabContainer();
    }

    private void setTabContainer(){
        tabContainer.setSide(Side.LEFT);
        tabContainer.setTabMinWidth(tabWidth);
        tabContainer.setTabMaxWidth(tabWidth);
        tabContainer.setTabMinHeight(tabWidth);
        tabContainer.setTabMaxHeight(tabWidth);
        tabContainer.setRotateGraphic(true);
        configureTab(equipmentTab, "Equipment", "presentation/assets/cogsmall.png");
        configureTab(buildTab, "Build", "presentation/assets/cogsmall.png");
    }

    private void configureTab(Tab tab, String title, String iconPath) {
        double imageWidth = 40.0;
        ImageView imageView = new ImageView(new Image(iconPath));
        imageView.setFitHeight(imageWidth);
        imageView.setFitWidth(imageWidth);
        Label label = new Label(title);
        label.setMaxWidth(tabWidth - 20);
        label.setPadding(new Insets(5,0,0,0));
        label.setStyle("-fx-text-fill: black; -fx-font-size: 10pt; -fx-font-weight: normal;");
        label.setTextAlignment(TextAlignment.CENTER);
        BorderPane tabPane = new BorderPane();
        tabPane.setRotate(90.0);
        tabPane.setMaxWidth(tabWidth);
        tabPane.setCenter(imageView);
        tabPane.setBottom(label);
        tab.setGraphic(tabPane);

    }

}
