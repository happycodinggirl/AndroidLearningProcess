



package com.plu.huangxingli.androidlearningprocess.dagger2.subcomponent;

import dagger.Module;
import dagger.Provides;




/**
 * Created by lily on 16-5-25.
 */




@Module
public class SmallMoudle {

    public SmallMoudle() {

    }

   /* @Provides
    public  Child provideChild(){
        return new Child("");
    }*/


    @StringType(StringType.firstname)
    @Provides
    public String firstName(){
        return "lily";
    }

    @StringType(StringType.secondname)
    @Provides
    public String secondName(){
        return "Hwang";
    }





}




