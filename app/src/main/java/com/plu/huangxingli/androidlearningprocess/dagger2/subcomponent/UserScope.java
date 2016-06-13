package com.plu.huangxingli.androidlearningprocess.dagger2.subcomponent;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by lily on 16-6-13.
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface UserScope {
}
