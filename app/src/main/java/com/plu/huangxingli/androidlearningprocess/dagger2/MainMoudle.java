package com.plu.huangxingli.androidlearningprocess.dagger2;

import com.plu.huangxingli.androidlearningprocess.app.App;
import com.plu.huangxingli.androidlearningprocess.dagger2.Student;

import dagger.Module;
import dagger.Provides;

/**
 * Created by lily on 16-5-25.
 */
@Module
public class MainMoudle {

    public MainMoudle(App app) {

    }

    /*@Provides
    public com.plu.huangxingli.androidlearningprocess.dagger2.Student provideRealString(){
            return new Student("lily");
    }*/
}
