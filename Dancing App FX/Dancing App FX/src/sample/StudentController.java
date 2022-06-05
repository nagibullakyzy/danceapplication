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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import static sample.Client.oos;

public class StudentController extends Client {
    private ObservableList<Dance> list = FXCollections.observableArrayList();
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button seeAllDances_button;

    @FXML
    private Button exit_button;

    @FXML
    private Button signup_button;

    @FXML
    private Button aboutus_button;

    @FXML
    private TableView<Dance> table;

    @FXML
    private TableColumn<Dance, Long> id_c;

    @FXML
    private TableColumn<Dance, String> name_c;

    @FXML
    private TableColumn<Dance, Integer> price_c;

    @FXML
    private TableColumn<Dance, Integer> numstud_c;

    @FXML
    private TableColumn<Dance, String> teacher_c;

    @FXML
    private Button ok_button;

    @FXML
    private TextField card_field;

    @FXML
    void initialize() throws IOException, ClassNotFoundException {
   ok_button.setVisible(false);
card_field.setVisible(false);

        id_c.setCellValueFactory(new PropertyValueFactory<Dance,Long>("id"));
        name_c.setCellValueFactory(new PropertyValueFactory<Dance,String>("name"));
        price_c.setCellValueFactory(new PropertyValueFactory<Dance,Integer>("price"));
        numstud_c.setCellValueFactory(new PropertyValueFactory<Dance,Integer>("num_stud"));
        teacher_c.setCellValueFactory(new PropertyValueFactory<Dance,String>("name_teacher"));


        oos.writeObject(new Box("getAllDances"));
        Box box = (Box)ois.readObject();
        ArrayList<Dance> li = box.getDances();

        for(int i=0;i<li.size();i++){
            String name = li.get(i).getName();
            Long id = li.get(i).getId();
            int price = li.get(i).getPrice();
            int numofstud = li.get(i).getNum_stud();
            String teacher = li.get(i).getName_teacher();
            list.add(new Dance(id,name,price,numofstud,teacher));
        }

        table.setItems(list);


        signup_button.setOnAction(event -> {

            ok_button.setVisible(true);
            card_field.setVisible(true);
            signup_button.setVisible(false);

        });


        ok_button.setOnAction(event -> {
            Dance dance = table.getSelectionModel().getSelectedItem();
            int card = Integer.parseInt(card_field.getText());
            try {
                oos.writeObject(new Box("check",new User(card)));
                Box b = (Box)ois.readObject();
                if(b.getCode().equals("ok")){
                    oos.writeObject(new Box("Signup",dance));
                }

            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            ok_button.getScene().getWindow().hide();
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


        aboutus_button.setOnAction(event -> {
            aboutus_button.getScene().getWindow().hide();
            FXMLLoader load = new FXMLLoader();
            load.setLocation(getClass().getResource("/sample/contacts.fxml"));

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


        seeAllDances_button.setOnAction(event -> {
            Dance dance = table.getSelectionModel().getSelectedItem();
            try {
                oos.writeObject(new Box("otpisatsya",dance));
            } catch (IOException e) {
                e.printStackTrace();
            }

            seeAllDances_button.getScene().getWindow().hide();
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
