package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.RentalProperty;
import view.NewWindowForAlert;
import view.ShowDetailWindow;

public class RentDateSelectController {
	@FXML
	private Button okButton;

	@FXML
	private TextField customerID;

	@FXML
	private DatePicker date;

	@FXML
	private TextField numOfDays;

	private RentalProperty p;

	@FXML
	public void initialize() {
	}

	
	
	@FXML
	public void OK(ActionEvent event) {
		try {
			if (customerID.equals("") | date.getEditor().getText().equals("") | numOfDays.equals("")) {
				throw new Exception();
			}
			p.rent(customerID.getText(), new model.DateTime(date.getEditor().getText()),
					Integer.parseInt(numOfDays.getText()));
			
			Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
			stage.close();
			ShowDetailWindow s = new ShowDetailWindow();
			s.show(p);
			
			new NewWindowForAlert("Book successfully");
		} catch (Exception e1) {
			new view.NewWindowForAlert("Input invalid");
		}
	}



	public void initalVariable(RentalProperty p) {
		this.p = p;
	}





}
