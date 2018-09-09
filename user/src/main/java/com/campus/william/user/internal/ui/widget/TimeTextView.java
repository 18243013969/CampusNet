package com.campus.william.user.internal.ui.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;

public class TimeTextView extends android.support.v7.widget.AppCompatTextView {
    private Drawable mDefaultBackgound;
    private Drawable mTimeBackgound;
    private int mTime;
    private int mTimeCount;
    private String mDefaultString;

    public TimeTextView(Context context) {
        this(context, null);
    }

    public TimeTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TimeTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mDefaultString = String.valueOf((getText() == null ? "" : getText()));
        mDefaultBackgound = getBackground();
    }


    public void setTimeBackground(Drawable background) {
        mTimeBackgound = background;

    }

    @Override
    public void setBackground(Drawable background) {
        super.setBackground(background);
    }

    public void setTimeBackgroundRes(@DrawableRes int resid) {
        Drawable d = null;
        if (resid != 0) {
            d = ContextCompat.getDrawable(getContext(), resid);
        }
        setTimeBackground(d);
    }

    /**
     * @param time 计时时间，单位秒
     **/
    public void setTime(int time) {
        mTime = time;
    }

    public int getTime() {
        return mTime;
    }

    public void start() {
        mTimeCount = getTime();
        if (mTimeCount > 0) {
            this.setEnabled(false);
            this.post(new Runnable() {
                @Override
                public void run() {
                    timeSitting();
                    setTimeBackgroundAtStart();
                    postTime();
                }
            });
        }
    }

    private void postTime() {
        this.postDelayed(new Runnable() {
            @Override
            public void run() {
                timeSitting();
                --mTimeCount;
                Log.d("timeCount", " " + mTimeCount);
                if (mTimeCount <= 0) {
                    stop();
                } else {
                    postTime();
                }
            }
        }, 1000);
    }

    private void stop() {
        mTimeCount = 0;
        //还原
        final String text = mDefaultString;
        if (text != null) {
            this.setText(text);
        } else {
            this.setText("");
        }
        if (mDefaultBackgound != null) {
            this.setBackground(mDefaultBackgound);
        }
        this.setEnabled(true);

    }

    private void timeSitting() {
        final String text = String.valueOf(mTimeCount) + "S";
        this.setText(text);

    }

    private void setTimeBackgroundAtStart() {
        if (mTimeBackgound != null) {
            if (this.getBackground() != mTimeBackgound) {
                this.setBackground(mTimeBackgound);
            }
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        stop();
    }
}
