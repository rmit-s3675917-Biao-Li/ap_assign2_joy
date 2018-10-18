package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;
import model.DateTime;
import model.RentalProperty;
import view.NewWindowForAlert;
import view.ShowDetailWindow;

public class ReturnDateSelectController {
	@FXML
	private Button okButton;

	@FXML
	private DatePicker date;

	private RentalProperty p;
	
	@FXML
	public void initialize() {
	}


	@FXML
	public void OK(ActionEvent event) {
		try {
			if (date.getEditor().getText().equals("")) {
				throw new Exception();
			}
			p.returnProperty(new DateTime(date.getEditor().getText()));

			Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
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
