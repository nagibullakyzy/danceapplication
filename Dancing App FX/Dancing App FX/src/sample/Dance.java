package sample;

import java.io.Serializable;

public class Dance implements Serializable {
   private Long id;
   private String name;
   private int price;
   private int num_stud;
   private String name_teacher;



    public Dance(Long id, String name, int price, int num_stud, String name_teacher) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.num_stud = num_stud;
        this.name_teacher = name_teacher;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getNum_stud() {
        return num_stud;
    }

    public void setNum_stud(int num_stud) {
        this.num_stud = num_stud;
    }

    public String getName_teacher() {
        return name_teacher;
    }

    public void setName_teacher(String name_teacher) {
        this.name_teacher = name_teacher;
    }
}
