package com.plu.huangxingli.androidlearningprocess.dagger2.subcomponent;

import javax.inject.Inject;

/**
 * Created by lily on 16-6-14.
 */
public class Lover {
    String name;

    @Inject
    public Lover() {

    }

    public String getName() {
        return name;
    }
}
