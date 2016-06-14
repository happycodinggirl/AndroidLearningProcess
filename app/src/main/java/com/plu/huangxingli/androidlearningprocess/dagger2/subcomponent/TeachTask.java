package com.plu.huangxingli.androidlearningprocess.dagger2.subcomponent;

/**
 * Created by lily on 16-6-14.
 */
public class TeachTask<T extends ITask> {

    T mTask;

    public TeachTask(T task) {
        this.mTask = task;
    }
}
