package com.plu.huangxingli.androidlearningprocess.view;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.FloatEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.plu.huangxingli.androidlearningprocess.R;
import com.plu.huangxingli.androidlearningprocess.Utils.PluLogUtil;

/**
 * Created by lily on 16-3-23.普通礼物
 */
public class CommonGifView extends RelativeLayout {

    ImageView mImgGift;
    TextView mTvGifCount;


    int mGiftCount;
    int giftWidth;
    private View giftOuterView;

    private int showedCount = 1;
    private int gifCountWidth;
    private int giftImageWidth;


    public CommonGifView(Context context) {
        super(context);
        init(context);
    }

    public CommonGifView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CommonGifView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public int getGiftCount() {
        return mGiftCount;
    }

    public void setGiftCount(int mGiftCount) {
        this.mGiftCount = mGiftCount;
    }

    private void init(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        giftOuterView = inflater.inflate(R.layout.common_gift_layout, this, true);
        mImgGift = (ImageView) giftOuterView.findViewById(R.id.imageGift);
        mTvGifCount = (StrokeTextView) giftOuterView.findViewById(R.id.tv_gift_count);
        giftOuterView.post(new Runnable() {
            @Override
            public void run() {
                giftWidth = giftOuterView.getWidth();
            }
        });

        mTvGifCount.post(new Runnable() {
            @Override
            public void run() {
                gifCountWidth = mTvGifCount.getWidth();
            }
        });

        mImgGift.post(new Runnable() {
            @Override
            public void run() {
                giftImageWidth = mImgGift.getWidth();
            }
        });

    }

    /*
    显示并展示礼物动画
     */
    public void startAnimate(int giftCount) {
        final TranslateAnimation translateAnimation = new TranslateAnimation(-giftWidth, 0, 0, 0);

        translateAnimation.setDuration(1500);
        translateAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                giftOuterView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {

                imageGifAnimate();
                handGif();

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        setVisibility(VISIBLE);
        giftOuterView.startAnimation(translateAnimation);

    }

    /**
     * 具体礼物动画
     */

    private void imageGifAnimate() {
        mImgGift.setVisibility(VISIBLE);
        TranslateAnimation gifCountTranslation = new TranslateAnimation(-giftImageWidth, 0, 0, 0);
        gifCountTranslation.setDuration(1500);
        mImgGift.startAnimation(gifCountTranslation);
    }

    /*
    礼物递增动画显示
     */

    private void handGif() {

        final ValueAnimator valueAnimator = ValueAnimator.ofObject(new FloatEvaluator(), 1f, 2f, 1);

        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                Log.v("PLU", "  ONAnimationUpdate value is " + value);
                //mTvGifCount.setTextSize(value);
                mTvGifCount.setText("X" + showedCount);
                mTvGifCount.setScaleX(value);
                mTvGifCount.setScaleY(value);
            }
        });

        valueAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {

                AnimatorSet animatorSet = new AnimatorSet();
                ObjectAnimator transalteAnimator = ObjectAnimator.ofFloat(giftOuterView, "y", giftOuterView.getY(), giftOuterView.getY() - 300);
                ObjectAnimator alphaAnimator = ObjectAnimator.ofFloat(giftOuterView, "alpha", 1.f, 0f);
                animatorSet.play(transalteAnimator).with(alphaAnimator);
                animatorSet.setDuration(2000);
                animatorSet.start();
                PluLogUtil.log("-----onAnimationEnd");
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                showedCount++;
            }
        });

        valueAnimator.setRepeatCount(getGiftCount());
        valueAnimator.setDuration(500);
        valueAnimator.start();

    }

    /**
     * 动画资源释放
     */
    public void release(){
        mImgGift.clearAnimation();
        mTvGifCount.clearAnimation();
        giftOuterView.clearAnimation();
    }
}