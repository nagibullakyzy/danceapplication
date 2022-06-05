package sample;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class EditUsersController extends Client {
    private ObservableList<User> list = FXCollections.observableArrayList();
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField name_field;

    @FXML
    private TextField phone_field;

    @FXML
    private TextField password_field;


    @FXML
    private Button exit_button;

    @FXML
    private TableView<User> table;

    @FXML
    private TableColumn<User, Long> id_c;

    @FXML
    private TableColumn<User, String> role_c;

    @FXML
    private TableColumn<User, String> name_c;

    @FXML
    private TableColumn<User, String> password_c;

    @FXML
    private TableColumn<User, String> tel_c;

    @FXML
    private Label label;


    @FXML
    private Button ok_button;

    @FXML
    private Button edit_button;


    @FXML
    private TextField role_field;



    @FXML
    void initialize() throws IOException, ClassNotFoundException {
        label.setVisible(false);
        edit_button.setOnAction(event -> {
            User user = table.getSelectionModel().getSelectedItem();
            if(user == null) {
                label.setText("You must choose a user!");
                label.setVisible(true);
            }
            else {
                edit_button.setVisible(false);
                name_field.setText(user.getName());
                password_field.setText(user.getPassword());
                phone_field.setText(user.getTel());
                role_field.setText(user.getRole());
            }
        });

        ok_button.setOnAction(event -> {
            User user = table.getSelectionModel().getSelectedItem();
            String name = name_field.getText();
            String phone = phone_field.getText();
            String pass = password_field.getText();
            String role = role_field.getText();


            if (name.isEmpty() ||  phone.isEmpty() || pass.isEmpty()) {
                label.setText("You must fill in all fields");
                label.setVisible(true);
            } else {
                ok_button.getScene().getWindow().hide();
                try {
                    oos.writeObject(new Box("edit_user", new User(user.getId(),role,name,pass,phone)));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    Box s = (Box)ois.readObject();
                    if (s.getCode().equals("Done")) {
                        FXMLLoader load = new FXMLLoader();
                        load.setLocation(getClass().getResource("/sample/edit_users.fxml"));

                        try {
                            load.load();
                        } catch (IOException e) {
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


        id_c.setCellValueFactory(new PropertyValueFactory<User,Long>("id"));
        role_c.setCellValueFactory(new PropertyValueFactory<User,String>("Role"));
        name_c.setCellValueFactory(new PropertyValueFactory<User,String>("Name"));
        password_c.setCellValueFactory(new PropertyValueFactory<User,String>("Password"));
        tel_c.setCellValueFactory(new PropertyValueFactory<User,String>("tel"));


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
