package controller;

import exception.AddPropertyException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AddPremiumSuiteController {
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

	@FXML
	public void initialize() {

		ComfirmBtn.setOnAction(e -> {
			handle();
			Stage stage = (Stage) ((Button) e.getSource()).getScene().getWindow();
			stage.close();
		});
		UploadImageBtn.setOnAction(new controller.UploadImageHandler());
		BackBtn.setOnAction(e -> {
			Stage stage = (Stage) ((Button) e.getSource()).getScene().getWindow();
			stage.close();
		});

	}

	public void handle() {
		try {
			AddPropertyHandler.addProperty(PropertyID.getText(), "Premium Suite", StreetNoTF.getText(),
					StreetNameTF.getText(), SuburbTF.getText(), "3", lastMaintenanceDate.getEditor().getText(),
					DescritpionTA.getText(), model.DataStorage.getSelectImage());
			new view.NewWindowForAlert("Property Successfully Added");
		} catch (AddPropertyException e) {
			System.out.println("Wrong Input");

		}
	}
}
