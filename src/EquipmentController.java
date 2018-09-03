import javafx.fxml.FXML;
import javafx.scene.image.ImageView;


public class EquipmentController {

    @FXML
    private ImageView heroImage;

    public EquipmentController(){

    }

    @FXML
    private void initialize() {
        setHeroImage();
    }

    private void setHeroImage(){
        heroImage.setFitHeight(900);
        heroImage.setFitWidth(280);
        heroImage.setLayoutX(250.0);
    }

}
