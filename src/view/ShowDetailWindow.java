package view;

import java.io.IOException;
import java.lang.ModuleLayer.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import model.RentalProperty;

public class ShowDetailWindow {

	@FXML
	private ImageView imageView;

	@FXML
	private Text P_ID;

	@FXML
	private Text Type;

	@FXML
	private Text Address;

	@FXML
	private Text suburb;

	@FXML
	private Text numOfBed;

	@FXML
	private Text status;

	@FXML
	private TextFlow descriptionTA;

	@FXML
	private ListView<String> recordList;

	@FXML
	private Text R_ID;

	@FXML
	private Text Rdate;

	@FXML
	private Text ERdate;

	@FXML
	private Text ARdate;

	@FXML
	private Text Rfee;

	@FXML
	private Text Lfee;

	@FXML
	private Button bButton;

	@FXML
	private Button Rbutton;

	@FXML
	private Button Mbutton;

	@FXML
	private Button FMbutton;

	Stage stage = new Stage();
	BorderPane root;
	private static RentalProperty p;

	public void show(RentalProperty p1) {
		try {
			p = p1;
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ShowDetailWindow.fxml"));
			root = loader.load();
			Scene scene = new Scene(root);
			stage.setTitle("Choose Property Type");
			stage.setResizable(true);
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
	void initialize() {
		RentalProperty p = this.p;
		imageView.setImage(p.getImage());
		P_ID.setText(p.getPropertyId());
		Type.setText(p.getType());
		Address.setText(p.getStreetNum() + " " + p.getStreetName());
		suburb.setText(p.getSuburb());
		numOfBed.setText(String.valueOf(p.getNumBedroom()));
		status.setText(p.getPropertyStatue());
		descriptionTA.getChildren().add(new Text(p.getDescription()));

		if (p.getRecord()[0] != null) {
			recordList.getItems().addAll("Current Record");
		}

		for (int i = 1; i < p.getRecord().length; i++) {
			if (p.getRecord()[i] != null)
				recordList.getItems().add("Record " + i);
		}

		setAllButtonDisable();
		switch (p.getPropertyStatue()) {
		case "available":
			bButton.setDisable(false);
			Mbutton.setDisable(false);
			break;
		case "rented":
			Rbutton.setDisable(false);
			break;
		case "maintenance":
			Mbutton.setDisable(false);
			break;
		default:
			break;
		}
		
		bButton.setOnAction(e->{
			try {
				RentDateSelect r = new RentDateSelect();
				r.show(p);
				Stage stage = (Stage)((Button)e.getSource()).getScene().getWindow();
				stage.close();
			}
			catch (Exception e1) {
				
			}
		});

	}

	@FXML
	void showRecord() {
		String s = recordList.getSelectionModel().getSelectedItem();
		if (s.equals("Current Record")) {
			R_ID.setText(p.getRecord()[0].getRecordId());
			Rdate.setText(p.getRecord()[0].getRentDate().getFormattedDate());
			ERdate.setText(p.getRecord()[0].getErDate().getFormattedDate());
			ARdate.setText("");
			Rfee.setText("none");
			Lfee.setText("none");
		} else {
			s = s.replace("Record ", "");
			int i = Integer.parseInt(s);

			R_ID.setText(p.getRecord()[i].getRecordId());
			Rdate.setText(p.getRecord()[i].getRentDate().getFormattedDate());
			ERdate.setText(p.getRecord()[i].getErDate().getFormattedDate());
			ARdate.setText(p.getRecord()[i].getArDate().getFormattedDate());
			Rfee.setText(String.valueOf(p.getRecord()[i].getRentalFee()));
			Lfee.setText(String.valueOf(p.getRecord()[i].getLateFee()));
		}
	}

	void setAllButtonDisable() {
		bButton.setDisable(true);
		Rbutton.setDisable(true);
		Mbutton.setDisable(true);
		FMbutton.setDisable(true);
	}
}