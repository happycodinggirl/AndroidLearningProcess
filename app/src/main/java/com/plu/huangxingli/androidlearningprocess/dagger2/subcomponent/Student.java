package com.plu.huangxingli.androidlearningprocess.dagger2.subcomponent;

/**
 * Created by lily on 16-5-25.
 */
public class Student {


    public Student(String name) {
        this.name = name;
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
