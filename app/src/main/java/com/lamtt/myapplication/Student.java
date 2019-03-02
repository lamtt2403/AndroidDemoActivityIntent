package com.lamtt.myapplication;

import java.io.Serializable;

public class Student implements Serializable {

    private String msv;

    private String name;

    public Student() {
    }


    public Student(String msv, String name) {
        this.msv = msv;
        this.name = name;
    }


    public String getMsv() {
        return msv;
    }

    public void setMsv(String msv) {
        this.msv = msv;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return msv + "__" + name;
    }
}
