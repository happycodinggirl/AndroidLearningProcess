package com.plu.huangxingli.androidlearningprocess.dagger2.subcomponent;

import javax.inject.Inject;

/**
 * Created by lily on 16-6-14.
 */
public class TeacherLiTask extends TeachTask<ITeacherLiTask> {

    @Inject
    public TeacherLiTask(ITeacherLiTask task) {  //此处注意ITeacherLiTask必须在另外一个地方通过provide的方式来提供
        super(task);
    }
}
