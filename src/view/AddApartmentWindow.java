package view;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AddApartmentWindow {

	Stage stage = new Stage();
	AnchorPane root;

	public void show() {

		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/AddApartmentWindow.fxml"));
			root = loader.load();
			Scene scene = new Scene(root);
			stage.setTitle("Add Apartment");
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
}
