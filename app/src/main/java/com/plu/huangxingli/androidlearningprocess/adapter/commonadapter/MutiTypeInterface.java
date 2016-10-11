package com.plu.huangxingli.androidlearningprocess.adapter.commonadapter;

/**baseAdapter的多种类型使用的外放接口
 * Created by lily on 15-12-8.
 */
public interface MutiTypeInterface<T> {
    int getLayoutId(int position, T t);

    int getViewTypeCount();

    int getItemViewType(int postion, T t);

}
