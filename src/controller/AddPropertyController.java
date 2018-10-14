package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import view.AddApartmentWindow;
import view.AddPremiumSuiteWindow;

public class AddPropertyController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button addApartmentBtn;

    @FXML
    private Button addPremiumSuiteBtn;

    @FXML
    private Button homepageBtn;

    @FXML
   public void initialize() {
    	AddApartmentWindow addApartmentWindow= new AddApartmentWindow();
       addApartmentBtn.setOnAction(e->addApartmentWindow.show());
       
       AddPremiumSuiteWindow addPremiumSuiteWindow=new AddPremiumSuiteWindow();
       addPremiumSuiteBtn.setOnAction(e->addPremiumSuiteWindow.show());
       
       
    }
}