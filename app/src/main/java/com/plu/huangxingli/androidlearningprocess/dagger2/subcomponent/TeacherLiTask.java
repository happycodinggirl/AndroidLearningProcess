package com.plu.huangxingli.androidlearningprocess.dagger2.subcomponent;

import javax.inject.Inject;

/**
 * Created by lily on 16-6-14.
 */
public class TeacherLiTask extends TeachTask<ITeacherLiTask> {

    @Inject
    public TeacherLiTask(ITeacherLiTask task) {
        super(task);
    }
}
