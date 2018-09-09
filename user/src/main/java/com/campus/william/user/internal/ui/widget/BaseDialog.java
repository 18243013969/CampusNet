package com.campus.william.user.internal.ui.widget;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by wenge on 2018/9/2.
 */

public class BaseDialog extends Dialog {

    public BaseDialog(@NonNull Context context) {
        this(context, 0);
    }

    public BaseDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }



    @Override
    public void show() {
        super.show();
    }

    public void setGravity(){
        final Window window = getWindow();
        DisplayMetrics displayMetrics = new DisplayMetrics();
         window.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        final int fullWidth = displayMetrics.widthPixels;
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.gravity = Gravity.BOTTOM;
        layoutParams.width = fullWidth;
        layoutParams.y = 20;
        window.setAttributes(layoutParams);
    }

    public void setSize(int w , int h) {
        final Window window = getWindow();
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        if(w > 0) {
            layoutParams.width = w;
        }
        if(h > 0 ) {
            layoutParams.height = h;
        }
        window.setAttributes(layoutParams);
    }

    public void setSizeByAspectRatio(float aspectRatio) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindow().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        final int fullWidth = displayMetrics.widthPixels;
        setSize(fullWidth, (int) (fullWidth * aspectRatio));
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if(this.isShowing()) {
            BaseDialog.this.dismiss();
        }
    }
}
