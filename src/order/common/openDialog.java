package order.common;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public class openDialog {

    public openDialog() {
    }

    public void openD(String pathFXML, String title, boolean resize) throws IOException {
        Stage editDialog = new Stage();
        editDialog.setTitle(title);
        editDialog.setResizable(resize);
        editDialog.initModality(Modality.WINDOW_MODAL);
        editDialog.setAlwaysOnTop(true);
        Parent root = FXMLLoader.load(getClass().getResource(pathFXML));
        Scene scene = new Scene(root);
        editDialog.setScene(scene);
        editDialog.showAndWait();
    }
}
