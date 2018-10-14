package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import view.AddPropertyWindow;
import view.Main;
import view.ShowDetailWindow;

public class HomeController {
	
	  @FXML
	    private MenuBar menubar;

	    @FXML
	    private MenuItem saveButton;

	    @FXML
	    private MenuItem ImportButton;

	    @FXML
	    private MenuItem ExportButton;

	    @FXML
	    private MenuItem AddButton;

	    @FXML
	    private ComboBox<String> propertyType;

	    @FXML
	    private ComboBox<?> suburb;

	    @FXML
	    private ComboBox<String> status;

	    @FXML
	    private ComboBox<String> bedroomNo;

	    @FXML
	    private FlowPane flowpane;
	    
	    @FXML
	    private TextField searchText;

    @FXML
    public void initialize() {
    	propertyType.getItems().setAll("All","Apartment","Premiun Suite");
    	status.getItems().setAll("All", "Available", "Rented", "Maintaining");
    	bedroomNo.getItems().setAll("All", "1", "2", "3");
        
    	AddButton.setOnAction(e->{
    		view.AddPropertyWindow n = new view.AddPropertyWindow();
    		n.show();
    	});
    	
    }
    


}
