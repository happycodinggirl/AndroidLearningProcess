package com.plu.huangxingli.androidlearningprocess.dagger2.subcomponent;

import javax.inject.Qualifier;
import javax.inject.Scope;

/**
 * Created by lily on 16-6-13.
 */
@Qualifier
public @interface StringType {
    String type="";
    String firstname="firstname1";

    String secondname="secondname1";


   String value() default  type;

}
