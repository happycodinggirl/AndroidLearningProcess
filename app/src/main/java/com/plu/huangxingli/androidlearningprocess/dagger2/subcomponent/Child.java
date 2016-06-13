package com.plu.huangxingli.androidlearningprocess.dagger2.subcomponent;

import javax.inject.Inject;

/**
 * Created by lily on 16-5-25.
 */
public class Child {
    String firsetName;

    String secondName;

    @Inject
    public Child(@StringType(StringType.firstname)String firstname,@StringType(StringType.secondname) String secondname) {
        this.firsetName = firstname;
        this.secondName=secondname;
    }

    public String getFirstName() {
        return firsetName;
    }

    public String getSecondName() {
        return secondName;
    }
}
