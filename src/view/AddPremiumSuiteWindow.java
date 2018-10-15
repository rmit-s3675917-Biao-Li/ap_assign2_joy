package view;

import java.io.File;
import java.io.IOException;

import controller.AddPropertyException;
import controller.AddPropertyHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.DataStorage;

public class AddPremiumSuiteWindow {
	@FXML
	private AnchorPane streetNoTF;

	@FXML
	private TextArea DescritpionTA;

	@FXML
	private Button ComfirmBtn;

	@FXML
	private Button BackBtn;

	@FXML
	private TextField PropertyID;

	@FXML
	private TextField StreetNoTF;

	@FXML
	private TextField StreetNameTF;

	@FXML
	private TextField SuburbTF;

	@FXML
	private DatePicker lastMaintenanceDate;

	@FXML
	private Button UploadImageBtn;

	@FXML
	private Text path;

	Stage stage = new Stage();
	AnchorPane root;

	public void show() {

		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/AddPremiumSuiteWindow.fxml"));
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

	@FXML
	public void initialize() {

		ComfirmBtn.setOnAction(e -> handle());

	}

	public void handle() {
		try {
			AddPropertyHandler.addProperty(PropertyID.getText(), "Apartment", StreetNoTF.getText(),
					StreetNameTF.getText(), SuburbTF.getText(), "3", lastMaintenanceDate.getEditor().getText(),
					DescritpionTA.getText(), model.DataStorage.getSelectImage());
			new view.NewWindowForAlert("Property Successfully Added");
			File f = null;
			DataStorage.setSelectImage(f);
			initialize();
		} catch (AddPropertyException e) {
			System.out.println("Wrong Input");

		}
	}
}