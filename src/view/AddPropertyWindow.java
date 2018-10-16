package view;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AddPropertyWindow {

	private static Stage stage = new Stage();
	AnchorPane root;

	@FXML
	private Button addApartmentBtn;

	@FXML
	private Button addPremiumSuiteBtn;

	@FXML
	private Button homepageBtn;

	private static HomeController homeController;

	@FXML
	public void initialize() {
		AddApartmentWindow addApartmentWindow = new AddApartmentWindow();
		addApartmentBtn.setOnAction(e -> addApartmentWindow.show());

		AddPremiumSuiteWindow addPremiumSuiteWindow = new AddPremiumSuiteWindow();
		addPremiumSuiteBtn.setOnAction(e -> addPremiumSuiteWindow.show());

		homepageBtn.setOnAction(e -> {
			Stage stage = (Stage) ((Button) e.getSource()).getScene().getWindow();
			stage.close();
			homeController.change();
		});
		
		stage.setOnCloseRequest(e->{
			homeController.change();
			stage.close();
		});
	}

	public void show(HomeController homeController1) {
		homeController = homeController1;
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/AddPropertyWindow.fxml"));
			root = loader.load();
			Scene scene = new Scene(root);
			stage.setTitle("Choose Property Type");
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