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

public class RegisController extends Client {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField name_field;

    @FXML
    private TextField role_field;

    @FXML
    private TextField login_field;

    @FXML
    private TextField phone_field;

    @FXML
    private TextField card_field;

    @FXML
    private TextField password_field;

    @FXML
    private Button registrate_button;

    @FXML
    private Button exit_button;

    @FXML
    void initialize() {

        registrate_button.setOnAction(event -> {
            String role = role_field.getText();
            String name = name_field.getText();
            String login = login_field.getText();
            String password = password_field.getText();
            String phone = phone_field.getText();
            String cardd = card_field.getText();
            int card = Integer.parseInt(cardd);

            try {
                oos.writeObject(new Box("Registration",new User(null,role,name,login,password,phone,card)));
            } catch (IOException e) {
                e.printStackTrace();
            }


            registrate_button.getScene().getWindow().hide();
            FXMLLoader load = new FXMLLoader();
            load.setLocation(getClass().getResource("/sample/registration.fxml"));

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
            load.setLocation(getClass().getResource("/sample/sample.fxml"));


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
