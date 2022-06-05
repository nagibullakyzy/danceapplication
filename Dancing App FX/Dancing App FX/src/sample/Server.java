package sample;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Server {
    private ServerSocket ss = null;
    private Connection conn = null;

    public Server(){
        try {
            ss = new ServerSocket(1997);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dsdb?useUnicode=true&serverTimezone=UTC", "root", "");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void listen(){
        while(true){
            try {
                System.out.println("waiting...");
                Socket socket = ss.accept();
                System.out.println("client connected: " + socket.getInetAddress().getHostAddress());
                ClientHandler ch = new ClientHandler(socket, conn);
                ch.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Server s = new Server();
        s.listen();
    }
}
