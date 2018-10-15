package view;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import model.DataStorage;

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
	private ComboBox<String> suburb;

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
		setMenu();
		setFilter();
		changed();
	}

	private void setMenu() {
		ImportButton.setOnAction(e -> {
			try {
				controller.FileController.importFile();
				changed();

			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});

		ExportButton.setOnAction(e -> {
			try {
				controller.FileController.exportFile();

			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		});

		AddButton.setOnAction(e -> {
			view.AddPropertyWindow n = new view.AddPropertyWindow();
			n.show();
		});
	}

	private void setFilter() {
		ObservableList<String> list1 = FXCollections.observableArrayList("All", "Apartment", "Premiun Suite");
		ObservableList<String> list2 = FXCollections.observableArrayList("All", "Available", "Rented", "Maintaining");
		ObservableList<String> list3 = FXCollections.observableArrayList("All", "1", "2", "3");
		ObservableList<String> list4 = FXCollections.observableArrayList();
		list4.add("All");
		for (int i = 0; i < DataStorage.getRP().size(); i++) {
			if (!list4.contains(DataStorage.getRP().get(i).getSuburb()))
				list4.add(DataStorage.getRP().get(i).getSuburb());
		}

		propertyType.setItems(list1);
		status.setItems(list2);
		bedroomNo.setItems(list3);
		suburb.setItems(list4);

		propertyType.setValue("All");
		bedroomNo.setValue("All");
		status.setValue("All");
		suburb.setValue("All");

		// Add listener to all the comboBox which will change the showing list if one of
		// the filter being changed
	}

	public void addOnePropertytoContent(model.RentalProperty p) {
//		one ProperyViewHbox shows one Property's information,
//		ProperyViewHbox = ImageView + ProperyDetailsVbox.
		HBox ProperyViewHbox = new HBox();

		// ProperyViewHbox
		ProperyViewHbox.setMaxWidth(700);
		ProperyViewHbox.setMinWidth(700);
		ProperyViewHbox.setSpacing(10);
		ProperyViewHbox.setPadding(new Insets(20, 5, 20, 5));

		// ImageView
		ImageView iv = new ImageView(p.getImage());
		iv.setFitHeight(300);
		iv.setFitWidth(300);

		// ProperyDetailsVbox
		VBox ProperyDetailsVbox = new VBox();
		ProperyDetailsVbox.setSpacing(10);
		ProperyDetailsVbox.setPadding(new Insets(0, 5, 5, 5));
		Text PropertyIDText = new Text("Property ID: " + p.getPropertyId());
		PropertyIDText.setFont(Font.font("Verdana", 20));
		PropertyIDText.setFill(Color.CORNFLOWERBLUE);
		Text PropertyAddressText = new Text(
				"Address: " + p.getStreetNum() + " " + p.getStreetName() + " " + p.getSuburb());
		Text descText = new Text("Description: \n" + p.getDescription());

		Button b = new Button("More Details");
		b.setOnAction(e -> {
			view.ShowDetailWindow n = new view.ShowDetailWindow();
			n.show(p);
			System.out.println("Showing property details");
		});

		ProperyDetailsVbox.getChildren().addAll(PropertyIDText, PropertyAddressText, descText, b);
		ProperyViewHbox.getChildren().addAll(iv, ProperyDetailsVbox);
		flowpane.getChildren().add(ProperyViewHbox);
	}

	private void clear() {
		flowpane.getChildren().clear();
	}

	public ComboBox<String> getPropertyType() {
		return propertyType;
	}

	public ComboBox<String> getSuburb() {
		return suburb;
	}

	public ComboBox<String> getStatus() {
		return status;
	}

	public ComboBox<String> getBedroomNo() {
		return bedroomNo;
	}

	@FXML
	public void changed() {
		ArrayList<model.RentalProperty> PL = model.DataStorage.getRP();
		int[] mark = new int[PL.size()];
		int bednum;
		if (getBedroomNo().getValue() != "All")
			bednum = Integer.parseInt((String) getBedroomNo().getValue());
		else
			bednum = -1;
		String Type = (String) getPropertyType().getValue();
		String Status = (String) getStatus().getValue();
		String Suburb = (String) getSuburb().getValue();
		for (int i = 0; i < mark.length; i++) {
			mark[i] = 0;
		}

		for (int i = 0; i < PL.size(); i++) {
			if ((PL.get(i).getType().equals(Type) || Type.equals("All"))
					&& (PL.get(i).getPropertyStatue().equals(Status) || Status.equals("All"))
					&& (PL.get(i).getSuburb().equals(Suburb) || Suburb.equals("All"))
					&& ((PL.get(i).getNumBedroom() == bednum) || bednum == -1))
				mark[i] = 1;

		}

		clear();

		int i = 0;
		while (i < PL.size()) {
			if (mark[i] == 1) {
				model.RentalProperty p = PL.get(i);
				addOnePropertytoContent(p);
			}

			i++;
		}
	}

}
