package com.plu.huangxingli.androidlearningprocess.dagger2.subcomponent;

import com.plu.huangxingli.androidlearningprocess.Utils.PluLogUtil;

import javax.inject.Inject;

/**
 * Created by lily on 16-6-14.
 */
public class TeachTaskImp implements ITeacherLiTask{

    @Inject
    public TeachTaskImp() {
    }

    @Override
    public void teach() {
        PluLogUtil.eLog("-----teachTask teach---");
    }

    @Override
    public void makeSpeech() {
        PluLogUtil.eLog("----teacheTaskImp make Speech");
    }
}
