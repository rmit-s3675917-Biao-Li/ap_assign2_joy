package view;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

import javafx.application.Application;
import javafx.fxml.FXML;
//import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Alert.AlertType;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {

		model.DataStorage.initialization();

		try {

			Parent root = FXMLLoader.load(getClass().getResource("/view/HomeWindow.fxml"));
			Scene scene = new Scene(root);

			primaryStage.setTitle("Welcome to the FexiRentSystem");
			primaryStage.setScene(scene);
			primaryStage.setResizable(true);
			primaryStage.show();
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle(e.getClass().getSimpleName());
			alert.setHeaderText("Error!");
			alert.setContentText(e.getMessage());
			alert.showAndWait();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
