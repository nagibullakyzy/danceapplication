package sample;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;

public class ClientHandler extends Thread {
    private Socket socket = null;
    private ObjectOutputStream oos = null;
    private ObjectInputStream ois = null;
    private Connection conn = null;

    public ClientHandler() {
    }

    public ClientHandler(Socket socket, Connection conn) {
        this.socket = socket;
        this.conn = conn;

        try{
            oos = new ObjectOutputStream(socket.getOutputStream());
            ois = new ObjectInputStream(socket.getInputStream());
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }


    public void addUser(User user){
        try {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO users (id,role,name,login,password,tel,mymoney,mycard) VALUES(NULL,?,?,?,?,?,?,?)");
            ps.setString(1, user.getRole());
            ps.setString(2,user.getName());
            ps.setString(3,user.getLogin());
            ps.setString(4,user.getPassword());
            ps.setString(5,user.getTel());
            ps.setInt(6,user.getMymoney());
            ps.setInt(7,user.getMycard());

            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public void addDance(Dance dance){
        try {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO dances (id,name,price,num_stud,name_student) VALUES(NULL,?,?,?,?)");
            ps.setString(1, dance.getName());
            ps.setString(4,dance.getName_teacher());
            ps.setInt(2,dance.getPrice());
            ps.setInt(3,dance.getNum_stud());

            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public void removeUser(Long id){
        try {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM users where id = ?");
            ps.setLong(1, id);
            ps.executeUpdate();
            ps.close();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }


    public void checkCard(int card){
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT mycard FROM users");
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                oos.writeObject(new Box("ok"));
                System.out.println("gooooogo");
            }
            ps.close();

        }
        catch(SQLException | IOException e){
            e.printStackTrace();
        }
    }

    public void changePassword(Long id,String pass){
        try {
            PreparedStatement ps = conn.prepareStatement("UPDATE users SET password = ? WHERE id = ?");
            ps.setLong(2, id);
            ps.setString(1,pass);
            ps.executeUpdate();
            ps.close();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void editUser(User user){
        try {
            PreparedStatement ps = conn.prepareStatement("UPDATE users SET name = ?,role = ?,password = ?,tel = ? WHERE id = ?");
            ps.setLong(5, user.getId());
            ps.setString(1,user.getName());
            ps.setString(2,user.getRole());
            ps.setString(3,user.getPassword());
            ps.setString(4,user.getTel());
            ps.executeUpdate();
            ps.close();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void editDance(Dance dance){
        try {
            String query = "SELECT num_stud FROM dances WHERE id = ?";
            PreparedStatement pS = conn.prepareStatement(query);
            pS.setLong(1,dance.getId() );
            PreparedStatement ps = conn.prepareStatement("UPDATE dances SET num_stud=? WHERE id = ?");
            ResultSet rS = pS.executeQuery();
            if(rS.next()){
                int quan = rS.getInt("num_stud");
                ps.setLong(2,dance.getId() );
                ps.setInt(1,quan+1);
                ps.executeUpdate();
            }
            else{
                System.out.println("Error!");
            }
            ps.close();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }


    public void removeDance(Dance dance){
        try {
            String query = "SELECT num_stud FROM dances WHERE id = ?";
            PreparedStatement pS = conn.prepareStatement(query);
            pS.setLong(1,dance.getId() );
            PreparedStatement ps = conn.prepareStatement("UPDATE dances SET num_stud=? WHERE id = ?");
            ResultSet rS = pS.executeQuery();
            if(rS.next()){
                int quan = rS.getInt("num_stud");
                ps.setLong(2,dance.getId() );
                ps.setInt(1,quan-1);
                ps.executeUpdate();
            }
            else{
                System.out.println("Error!");
            }
            ps.close();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }





    public LinkedList<User> getAllUsers(){
        LinkedList<User> list = new LinkedList<>();

        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * from users");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Long id = rs.getLong("id");
                String role = rs.getString("role");
                String name = rs.getString("name");
                String login = rs.getString("login");
                String password = rs.getString("password");
                String tel = rs.getString("tel");
                int mymoney = rs.getInt("mymoney");
                int mycard = rs.getInt("mycard");
                list.add(new User(id,role,name,login,password,tel,mymoney,mycard));
            }
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }


    public ArrayList<Dance> getAllDances(){
        ArrayList<Dance> list = new ArrayList<>();

        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * from dances");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Long id = rs.getLong("id");
                String name = rs.getString("name");
                String teacher = rs.getString("name_student");
                int price = rs.getInt("price");
                int numOfStud = rs.getInt("num_stud");
                list.add(new Dance(id,name,price,numOfStud,teacher));
            }
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }




    public void run(){
        try {
             while(true){
                 Box box = (Box)ois.readObject();

                 if(box.getCode().equals("GetAllUsers")){
                     oos.writeObject(new Box("ok",getAllUsers()));
                 }

                 if(box.getCode().equals("remove_user")){
                     removeUser(box.getUser().getId());
                 }

                 if(box.getCode().equals("edit_user")){
                        editUser(box.getUser());
                        oos.writeObject(new Box("Done"));
                 }

                 if(box.getCode().equals("getAllDances")){
                 oos.writeObject(new Box("ok",getAllDances()));
                 }

                 if(box.getCode().equals("AddDance")){
                    addDance(box.getDance());
                 }

                 if(box.getCode().equals("check")){
                     checkCard(box.getUser().getMycard());
                 }

                 if(box.getCode().equals("Signup")){
                  editDance(box.getDance());
                 }
                 if(box.getCode().equals("otpisatsya")){
                     removeDance(box.getDance());
                 }






                 if(box.getCode().equals("Authorization")){
                     LinkedList<User> users = getAllUsers();
                     for(int i=0;i< users.size();i++){
                         String log = users.get(i).getLogin();
                         String pass = users.get(i).getPassword();
                         if(log.equals("aya") && pass.equals("123")){
                             if(box.getUser().getLogin().equals(log) && box.getUser().getPassword().equals(pass)){
                                 oos.writeObject(new Box("Admin",users.get(i)));
                                 break;
                             }
                         }
                         if(box.getUser().getLogin().equals(users.get(i).getLogin()) && box.getUser().getPassword().equals(users.get(i).getPassword())){
                             Box box2 = new Box("Accepted",users.get(i));
                             oos.writeObject(box2);
                         }
                     }
                 }

                 if(box.getCode().equals("Registration")){
                     addUser(box.getUser());
                 }

                 if(box.getCode().equals("Change")){
                     LinkedList<User> users = getAllUsers();
                     for(int i=0;i< users.size();i++){
                         if(box.getUser().getLogin().equals(users.get(i).getLogin()) && box.getUser().getPassword().equals(users.get(i).getPassword())){
                             //changePassword(box.getUser().getId(),box.getUser().getNewpassword());
                             Box box2 = new Box("Changed");
                             oos.writeObject(box2);
                         }
                     }
                 }




                 if(box.getCode().equals("Exit"))
                     break;
             }

        oos.close();
        ois.close();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
