package com.example.thandiwe.report;

import java.io.Serializable;

/**
 * Created by Thandiwe on 2017/07/27.
 */

public class Student implements Serializable{


    private long id;
    private String name;
    private String surname;
    private int mark1, mark2, mark3;


    public Student() {
    }

    public Student(long id, String name, String surname, int mark1, int mark2, int mark3) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.mark1 = mark1;
        this.mark2 = mark2;
        this.mark3 = mark3;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getMark1() {
        return mark1;
    }

    public void setMark1(int mark1) {
        this.mark1 = mark1;
    }

    public int getMark2() {
        return mark2;
    }

    public void setMark2(int mark2) {
        this.mark2 = mark2;
    }

    public int getMark3() {
        return mark3;
    }

    public void setMark3(int mark3) {
        this.mark3 = mark3;
    }

    @Override
    public String toString() {
        return "Student id:" + id + "\n" +
                "Name: "     + name + "\n" +
                "Surname: "  + surname + "\n"+
                "Mark1: "    + mark1 + "\n" +
                "Mark2: "    + mark2 + "\n" +
                "Mark3: "    + mark3;
    }
}
