package com.zzc.library;

import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

/**
 * auth zzc
 * date 2018/4/4
 * desc ${desc}
 */

class DrawableCreater {
    private static final String TAG = DrawableCreater.class.toString();

    private DrawableCreater() {
    }

    static void setDrawable(AttributeSet attrs, View view) {
        if (attrs == null || view == null) return;
        TypedArray typedArray = view.getContext().obtainStyledAttributes(attrs, R.styleable.HelperView);
        DrawableBuilder db = new DrawableBuilder();
        if (typedArray.hasValue(R.styleable.HelperView_helper_radius)) {
            db.radius(typedArray.getDimension(R.styleable.HelperView_helper_radius, 0));
        }

        if (typedArray.hasValue(R.styleable.HelperView_helper_ripple_enable)) {
            db.ripperEnable(typedArray.getBoolean(R.styleable.HelperView_helper_ripple_enable, false));
        }

        if (typedArray.hasValue(R.styleable.HelperView_helper_stroke_width)) {
            db.strokeWidth(typedArray.getDimension(R.styleable.HelperView_helper_stroke_width, 0));
        }

        if (typedArray.hasValue(R.styleable.HelperView_helper_stroke_color)) {
            db.strokeColor(typedArray.getColor(R.styleable.HelperView_helper_stroke_color, DrawableBuilder.INVALID));
        }

        if (typedArray.hasValue(R.styleable.HelperView_helper_stroke_press_color)) {
            db.strokePressColor(typedArray.getColor(R.styleable.HelperView_helper_stroke_press_color, DrawableBuilder.INVALID));
        }

        if (typedArray.hasValue(R.styleable.HelperView_helper_solid_color)) {
            db.solidColor(typedArray.getColor(R.styleable.HelperView_helper_solid_color, DrawableBuilder.INVALID));
        }

        if (typedArray.hasValue(R.styleable.HelperView_helper_solid_press_color)) {
            db.solidPressColor(typedArray.getColor(R.styleable.HelperView_helper_solid_press_color, DrawableBuilder.INVALID));
        }

        typedArray.recycle();
        Drawable d = db.build();
        if (d != null) {
            if (Build.VERSION.SDK_INT >= 16) {
                view.setBackground(d);
            } else {
                view.setBackgroundDrawable(d);
            }
        }
    }
}