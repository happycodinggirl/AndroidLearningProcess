package com.plu.huangxingli.androidlearningprocess.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.plu.huangxingli.androidlearningprocess.R;
import com.plu.huangxingli.androidlearningprocess.Utils.PluLogUtil;


/**
 * Created by lily on 16-5-12.
 */
public class LoadingView extends View {

    private int currentState;

    private Paint mPaint;

    private int num=0;

    private Bitmap mUpBitmap,mDownBitmap;
    public LoadingView(Context context) {
        super(context);
        init();
    }

    public LoadingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        mPaint=new Paint();
        mUpBitmap= BitmapFactory.decodeResource(getResources(), R.drawable.egg3);
        mDownBitmap=BitmapFactory.decodeResource(getResources(), R.drawable.egg5);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        num++;
        Log.v("DRAW","----ONDRAW ___________"+num);
        switch (currentState){
            case DrawState.UP:
                canvas.drawBitmap(mUpBitmap,getWidth()/2-mUpBitmap.getWidth()/2,getHeight()/2-mUpBitmap.getHeight()/2,mPaint);
                currentState= DrawState.DOWN;
                break;
            case DrawState.DOWN:
                canvas.drawBitmap(mDownBitmap,getWidth()/2-mDownBitmap.getWidth()/2,getHeight()/2-mDownBitmap.getHeight()/2,mPaint);
                currentState= DrawState.UP;
                break;
        }
        postInvalidateDelayed(200);
    }



    static class DrawState{
        static final int UP=0;
        static final int DOWN=1;
    }
}
