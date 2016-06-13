
package com.plu.huangxingli.androidlearningprocess.dagger2.denpendencycomponent;

import com.plu.huangxingli.androidlearningprocess.app.App;

import dagger.Module;
import dagger.Provides;


/**
 * Created by lily on 16-5-25.
 */

@Module
public class MainMoudle {

    public MainMoudle(App app) {

    }

    @Provides
    public Student provideRealString(){
            return new Student("lily");
    }
}

