package controller;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class AddPremiumSuiteController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane streetNoTF;

    @FXML
    private TextField StreetNoTF;

    @FXML
    private TextField StreetNameTF;

    @FXML
    private TextField SuburbTF;

    @FXML
    private ComboBox<?> BedNumCB;

    @FXML
    private Button UploadImageBtn;

    @FXML
    private TextArea DescritpionTA;

    @FXML
    private Button ComfirmBtn;

    @FXML
    private Button BackBtn;

    @FXML
    private TextField StreetNoTF1;

    @FXML
    private Text path;

    @FXML
    private DatePicker lastMaintenanceDate;

    @FXML
    void initialize() {
        

    }
}
