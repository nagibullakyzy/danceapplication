package sample;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import javax.swing.text.html.ImageView;

public class ContactsController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button exit_button;

    @FXML
    private Label label;



    @FXML
    void initialize() {



        exit_button.setOnAction(event -> {
            exit_button.getScene().getWindow().hide();
            FXMLLoader load = new FXMLLoader();
            load.setLocation(getClass().getResource("/sample/student.fxml"));
            try{
                load.load();
            }
            catch(IOException e){
                e.printStackTrace();
            }
            Parent roots = load.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(roots));
            stage.show();
        });
    }
}
