package sample;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;

public class Box implements Serializable {
    private String code;
    private User user;
    private Dance dance;
    private LinkedList<User> users;
    private ArrayList<Dance> dances = null;

    public Box(String code, User user) {
        this.code = code;
        this.user = user;
    }

    public Box(String code, LinkedList<User> users) {
        this.code = code;
        this.users = users;
    }

    public Box(String code, Dance dance) {
        this.code = code;
        this.dance = dance;
    }

    public Box(String code, ArrayList<Dance> dances) {
        this.code = code;
        this.dances = dances;
    }

    public LinkedList<User> getUsers() {
        return users;
    }

    public void setUsers(LinkedList<User> users) {
        this.users = users;
    }

    public Box(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public Dance getDance() {
        return dance;
    }

    public void setDance(Dance dance) {
        this.dance = dance;
    }

    public ArrayList<Dance> getDances() {
        return dances;
    }

    public void setDances(ArrayList<Dance> dances) {
        this.dances = dances;
    }
}
