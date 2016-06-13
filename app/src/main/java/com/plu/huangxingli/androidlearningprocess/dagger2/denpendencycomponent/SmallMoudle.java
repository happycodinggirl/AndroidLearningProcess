



package com.plu.huangxingli.androidlearningprocess.dagger2.denpendencycomponent;

import dagger.Module;
import dagger.Provides;




/**
 * Created by lily on 16-5-25.
 */




@Module
public class SmallMoudle {

    public SmallMoudle() {

    }

    @Provides
    public  Child provideChild(){
        return new Child(3);
    }
}




