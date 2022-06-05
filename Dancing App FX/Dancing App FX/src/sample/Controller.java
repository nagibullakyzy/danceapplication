package sample;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class Controller extends Client {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField login_field;

    @FXML
    private PasswordField password_field;

    @FXML
    private Button login_button;

    @FXML
    private Button registration_button;

    @FXML
    void initialize(MouseEvent event) {

    }

    @FXML
    void initialize() {

        registration_button.setOnAction(event -> {
            registration_button.getScene().getWindow().hide();
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



        login_button.setOnAction(event -> {
            String login = login_field.getText();
            String pass = password_field.getText();
            if(login.isEmpty() || pass.isEmpty()){

            }
            else{
                try {
                    oos.writeObject(new Box("Authorization",new User(login,pass)));
                } catch (IOException e) {
                    e.printStackTrace();
                }

                try {
                    Box box = (Box)ois.readObject();
                    if(box.getCode().equals("Admin")){
                        login_button.getScene().getWindow().hide();
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
                    }


                    if(box.getCode().equals("Accepted")){
                        login_button.getScene().getWindow().hide();
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
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

            }

        });


    }
}
