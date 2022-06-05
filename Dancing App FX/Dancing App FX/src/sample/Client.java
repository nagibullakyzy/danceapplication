package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client extends Application {
    protected static ObjectOutputStream oos = null;
    protected static ObjectInputStream ois = null;

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 1997);
            Scanner in = new Scanner(System.in);

            oos = new ObjectOutputStream(socket.getOutputStream());
            ois = new ObjectInputStream(socket.getInputStream());

            launch(args);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/sample/sample.fxml"));
        primaryStage.setTitle("Dancing School App");
        primaryStage.setScene(new Scene(root, 800, 500));
        primaryStage.show();
    }
}
