
package com.plu.huangxingli.androidlearningprocess.dagger2.subcomponent;

import com.plu.huangxingli.androidlearningprocess.app.App;
import com.plu.huangxingli.androidlearningprocess.dagger2.subcomponent.Student;

import dagger.Module;
import dagger.Provides;


/**
 * Created by lily on 16-5-25.
 */

@Module
public class MainMoudle {

    public MainMoudle() {

    }

    @Provides
    public com.plu.huangxingli.androidlearningprocess.dagger2.subcomponent.Student provideRealString(){
            return new Student("lily");
    }
}

