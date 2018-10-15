package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class UploadImageHandler implements EventHandler<ActionEvent> {


	@Override
	public void handle(ActionEvent event) {
		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setTitle("Upload Image");
		FileChooser imageFC = new FileChooser();
		File f = imageFC.showOpenDialog(stage);
		if (f != null) {
			
				uploadFiles(f);
				view.AddApartmentWindow.setT(new Text(f.getAbsolutePath()));

		}
	}
	
	public void uploadFiles(File file) {
		int n = 0;
		try {
			FileInputStream fis = new FileInputStream(file.getAbsolutePath());
			model.DataStorage.setSelectImage(new File("Files/" + file.getName()));
			FileOutputStream fos = new FileOutputStream("Files/" + file.getName());

			while ((n = fis.read()) != -1) {
				fos.write(n);
			}
			fis.close();
			fos.close();

		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
