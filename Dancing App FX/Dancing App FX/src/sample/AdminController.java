package sample;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class AdminController extends Client {
    private ObservableList<User> list = FXCollections.observableArrayList();
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button delus_button;

    @FXML
    private Button exit_button;

    @FXML
    private Button edus_button;

    @FXML
    private Button edcon_button;

    @FXML
    private TableView<User> table;

    @FXML
    private TableColumn<User,Long> id_c;

    @FXML
    private TableColumn<User, String> role_c;

    @FXML
    private TableColumn<User, String> name_c;

    @FXML
    private TableColumn<User, String> login_c;

    @FXML
    private TableColumn<User, String> password_c;

    @FXML
    private TableColumn<User, String> tel_c;

    @FXML
    private TableColumn<User, Integer> card_c;

    @FXML
    private Label label;

    @FXML
    private Button dances_button;

    @FXML
    private Button AddDance_button;

    @FXML
    void initialize() throws IOException, ClassNotFoundException {
        label.setVisible(false);


        id_c.setCellValueFactory(new PropertyValueFactory<User,Long>("id"));
        role_c.setCellValueFactory(new PropertyValueFactory<User,String>("Role"));
        name_c.setCellValueFactory(new PropertyValueFactory<User,String>("Name"));
        login_c.setCellValueFactory(new PropertyValueFactory<User,String>("Login"));
        password_c.setCellValueFactory(new PropertyValueFactory<User,String>("Password"));
        tel_c.setCellValueFactory(new PropertyValueFactory<User,String>("tel"));
        card_c.setCellValueFactory(new PropertyValueFactory<User,Integer>("mycard"));


        oos.writeObject(new Box("GetAllUsers"));
        Box box = (Box)ois.readObject();
        LinkedList<User> li = box.getUsers();

        for(int i=0;i<li.size();i++){
            String role = li.get(i).getRole();
            String name = li.get(i).getName();
            String login = li.get(i).getLogin();
            String password = li.get(i).getPassword();
            String tel = li.get(i).getTel();
            int card = li.get(i).getMycard();
            Long id = li.get(i).getId();
            list.add(new User(id,role,name,login,password,tel,card));
        }

        table.setItems(list);



delus_button.setOnAction(event -> {

    User user = table.getSelectionModel().getSelectedItem();
    if(user != null){
        try {
            oos.writeObject(new Box("remove_user",user));
        } catch (IOException e) {
            e.printStackTrace();

        }
        delus_button.getScene().getWindow().hide();
        FXMLLoader l = new FXMLLoader();
        l.setLocation(getClass().getResource("/sample/admin.fxml"));
        try {
            l.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = l.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }
    else{
        label.setText("You must choose a user!");
        label.setVisible(true);
    }
});


        AddDance_button.setOnAction(event -> {
            AddDance_button.getScene().getWindow().hide();
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


        edus_button.setOnAction(event -> {
            edus_button.getScene().getWindow().hide();
            FXMLLoader load = new FXMLLoader();
            load.setLocation(getClass().getResource("/sample/edit_users.fxml"));
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




