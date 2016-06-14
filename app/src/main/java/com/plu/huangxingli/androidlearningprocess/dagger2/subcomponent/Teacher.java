package com.plu.huangxingli.androidlearningprocess.dagger2.subcomponent;

import javax.inject.Inject;

/**
 * Created by lily on 16-6-13.
 */
public class Teacher {

    public String name;

    public Lover lover;

    @Inject
    public Teacher(Lover lover) {
        this.lover=lover;
    }

    public String getName() {
        return name;
    }

    public Teacher setName(String name) {
        this.name = name;
        return this;
    }

    public Lover getLover() {
        return lover;
    }
}
