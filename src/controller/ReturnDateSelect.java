package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;
import model.DateTime;
import model.RentalProperty;
import view.NewWindowForAlert;

public class ReturnDateSelect {
	@FXML
	private Button okButton;

	@FXML
	private DatePicker date;

	private static RentalProperty p;
	private Pane root;
	Stage stage = new Stage();

	@FXML
	public void initialize() {
	}

	public void show(RentalProperty p1) {
		try {
			p = p1;
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/RentDateSelect.fxml"));
			root = loader.load();
			Scene scene = new Scene(root);
			stage.setResizable(false);
			stage.setScene(scene);
			stage.show();

		}

		catch (IOException e) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle(e.getClass().getSimpleName());
			alert.setHeaderText("Error!");
			alert.setContentText(e.getMessage());
			alert.showAndWait();
		}

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

}
