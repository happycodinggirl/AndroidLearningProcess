package com.plu.huangxingli.androidlearningprocess.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.widget.LinearLayout;

import com.plu.huangxingli.androidlearningprocess.Utils.PluLogUtil;

/**
 * Created by lily on 16-2-26.
 */
public class TopLayout extends LinearLayout {

    int windowWidth,windowHeight;

    public TopLayout(Context context) {
        super(context);
        init(context);
    }

    public TopLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public TopLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context){
       /* DisplayMetrics displayMetrics=getResources().getDisplayMetrics();
        windowWidth=displayMetrics.widthPixels;
        windowHeight=displayMetrics.heightPixels;*/

    }


}
