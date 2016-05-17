package com.plu.huangxingli.androidlearningprocess.pulltoRefresh;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.plu.huangxingli.androidlearningprocess.R;
import com.plu.huangxingli.androidlearningprocess.fragment.InnerFragment;

/**
 * Created by lenovo on 2016/5/17.
 */
public class EggView extends View {

    static final int STATE_INIT=0;
    static final int STATE_ON=2;
    static final int STATE_MOVING =3;

    int currentState=STATE_INIT;

    private Bitmap mFullEgg;
    private Bitmap mEggTop;
    private Bitmap mEggMoving1;
    private Bitmap mEggMoving2;
    private Bitmap mEggMoving3;

    private int currentOffset;

    private Bitmap[] movingBitmap=new Bitmap[3];

    private int currentWavingIndex=0;

    private Paint mPaint;


    int maxOffset=300;

    public EggView(Context context) {
        super(context);
        init();
    }

    public EggView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public EggView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        mFullEgg= BitmapFactory.decodeResource(getResources(), R.drawable.egg0);
        mEggTop=BitmapFactory.decodeResource(getResources(),R.drawable.egg2);
        mEggMoving1=BitmapFactory.decodeResource(getResources(),R.drawable.egg3);
        mEggMoving2=BitmapFactory.decodeResource(getResources(),R.drawable.egg4);
        mEggMoving3=BitmapFactory.decodeResource(getResources(),R.drawable.egg5);
        movingBitmap[0]=mEggMoving1;
        movingBitmap[1]=mEggMoving2;
        movingBitmap[2]=mEggMoving3;
        mPaint=new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        switch (currentState){
            case STATE_INIT:
                canvas.drawBitmap(mFullEgg,getWidth()/2-mFullEgg.getWidth()/2,getHeight()/2-mFullEgg.getHeight()/2,mPaint);
                break;
            case STATE_ON://wave动画，小龙翅膀在闪动
                Bitmap drawingBp=movingBitmap[currentState];
                canvas.drawBitmap(drawingBp,getWidth()/2-drawingBp.getWidth()/2,getHeight()/2-drawingBp.getHeight()/2,mPaint);

                currentWavingIndex=(currentWavingIndex+1)%movingBitmap.length;
                postInvalidateDelayed(150);
                break;
            case STATE_MOVING:
                canvas.drawBitmap(mEggTop,getWidth()/2-mEggTop.getWidth()/2,getHeight()/2-mEggTop.getHeight()/2-currentOffset/2,mPaint);
                break;
        }
    }

    public void movePosition(int offset){
        currentState=offset;
        if (offset>=0){
            if (offset>maxOffset/2){
                currentState=STATE_MOVING;
            }else {
                currentState = STATE_INIT;
            }
        }else{
            currentState=STATE_MOVING;
        }
        invalidate();
    }
}
