package sample;

import java.io.Serializable;

public class User implements Serializable {
    private Long id;
    private String role;
    private String name;
    private String login;
    private String password;
    private String tel;
    private int mymoney = 100000;
    private int mycard;

    public User(int mycard) {
        this.mycard = mycard;
    }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public User(Long id, String role, String name, String password, String tel) {
        this.id = id;
        this.role = role;
        this.name = name;
        this.password = password;
        this.tel = tel;
    }

    public User(Long id, String role, String name, String login, String password, String tel, int mycard) {
        this.id = id;
        this.role = role;
        this.name = name;
        this.login = login;
        this.password = password;
        this.tel = tel;
        this.mycard = mycard;
    }

    public User(Long id, String role, String name, String login, String password, String tel, int mymoney, int mycard) {
        this.id = id;
        this.role = role;
        this.name = name;
        this.login = login;
        this.password = password;
        this.tel = tel;
        this.mymoney = mymoney;
        this.mycard = mycard;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public int getMymoney() {
        return mymoney;
    }

    public void setMymoney(int mymoney) {
        this.mymoney = mymoney;
    }

    public int getMycard() {
        return mycard;
    }

    public void setMycard(int mycard) {
        this.mycard = mycard;
    }
}
