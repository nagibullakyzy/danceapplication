package sample;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddDanceController extends Client {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField name_field;

    @FXML
    private TextField teacher_field;

    @FXML
    private TextField numOfStud_field;

    @FXML
    private TextField price_field;

    @FXML
    private Button add_button;

    @FXML
    private Button exit_button;

    @FXML
    void initialize() {

        add_button.setOnAction(event -> {
            add_button.getScene().getWindow().hide();
            String name = name_field.getText();
            String teacher = teacher_field.getText();
            int price = Integer.parseInt(price_field.getText());
            int numOfStud = Integer.parseInt(numOfStud_field.getText());

            try {
                oos.writeObject(new Box("AddDance",new Dance(null,name,price,numOfStud,teacher)));
            } catch (IOException e) {
                e.printStackTrace();
            }

            FXMLLoader load = new FXMLLoader();
            load.setLocation(getClass().getResource("/sample/addDance.fxml"));

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


        exit_button.setOnAction(event -> {
            exit_button.getScene().getWindow().hide();
            FXMLLoader load = new FXMLLoader();
            load.setLocation(getClass().getResource("/sample/admin.fxml"));

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
