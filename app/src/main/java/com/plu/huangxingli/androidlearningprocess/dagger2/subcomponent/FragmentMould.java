package com.plu.huangxingli.androidlearningprocess.dagger2.subcomponent;

import dagger.Module;
import dagger.Provides;

/**
 * Created by lily on 16-6-13.
 */

@FragmentScope
@Module
public class FragmentMould {

    @Provides
    @FragmentScope
    ITeacherLiTask provideITeachTask(TeachTaskImp teachTaskImp){
        return teachTaskImp;
    }



}
