package com.zzc.library;

import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.RippleDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;

/**
 * auth zzc
 * date 2018/4/4
 * desc ${desc}
 */

public class DrawableBuilder {
    static final int INVALID = -1;

    static final int HAS_NORMAL = 1;
    static final int HAS_PRESS = 1 << 1;
    static final int IS_RIPPLE = 1 << 2;

    private float radiusPx;
    private float strokeWidthPx;
    private int strokeColor;
    private int strokePressColor;
    private int solidColor;
    private int solidPressColor;

    private GradientDrawable mNormalBg;
    private GradientDrawable mPressedBg;

    private int mFlag;
    private boolean hasAttr;

    public DrawableBuilder() {
        radiusPx = 0;
        strokeWidthPx = 0;
        strokeColor = INVALID;
        strokePressColor = INVALID;
        solidColor = INVALID;
        solidPressColor = INVALID;
        mFlag = 0;
    }


    public DrawableBuilder radius(float dimen) {
        hasAttr = true;
        this.radiusPx = dimen;
        return this;
    }

    public DrawableBuilder ripperEnable(boolean enable) {
        mFlag |= IS_RIPPLE;
        hasAttr = true;
        return this;
    }

    public DrawableBuilder strokeWidth(float dimen) {
        this.strokeWidthPx = dimen;
        hasAttr = true;
        return this;
    }

    public DrawableBuilder strokeColor(int color) {
        mFlag |= HAS_NORMAL;
        hasAttr = true;
        this.strokeColor = color;
        return this;
    }

    public DrawableBuilder strokePressColor(int color) {
        mFlag |= HAS_PRESS;
        hasAttr = true;
        this.strokePressColor = color;
        return this;
    }

    public DrawableBuilder solidColor(int color) {
        mFlag |= HAS_NORMAL;
        hasAttr = true;
        this.solidColor = color;
        return this;
    }

    public DrawableBuilder solidPressColor(int color) {
        mFlag |= HAS_PRESS;
        hasAttr = true;
        this.solidPressColor = color;
        return this;
    }

    public Drawable build() {
        if (!hasAttr) return null;

        if (((mFlag & IS_RIPPLE) != 0) && Build.VERSION.SDK_INT >= 21) {
            setNormal();
            if (mNormalBg == null) return null;
            return new RippleDrawable(
                    new ColorStateList(
                            new int[][]{
                                    new int[]{android.R.attr.state_pressed},
                                    new int[]{},
                            },
                            new int[]{
                                    solidPressColor == INVALID ? strokePressColor : solidPressColor,
                                    solidColor == INVALID ? strokeColor : strokePressColor
                            }), mNormalBg, null);
        } else {
            StateListDrawable drawable = new StateListDrawable();
            setNormal();
            setPress();
            if (mNormalBg != null) {
                drawable.addState(new int[]{-android.R.attr.state_pressed}, mNormalBg);
            }
            if (mPressedBg != null) {
                drawable.addState(new int[]{android.R.attr.state_pressed}, mPressedBg);
            }
            return drawable;
        }
    }

    private void setPress() {
        if ((mFlag & HAS_PRESS) == 0) return;
        mPressedBg = new GradientDrawable();
        if (radiusPx != 0) {
            mPressedBg.setCornerRadius(radiusPx);
        }

        if (strokeWidthPx != 0 && strokePressColor != INVALID) {
            mPressedBg.setStroke((int) strokeWidthPx, strokePressColor);
        }
        if (solidPressColor != INVALID) {
            mPressedBg.setColor(solidPressColor);
        }
    }

    private void setNormal() {
        if ((mFlag & HAS_NORMAL) == 0) return;
        mNormalBg = new GradientDrawable();
        if (radiusPx != 0) {
            mNormalBg.setCornerRadius(radiusPx);
        }

        if (strokeWidthPx != 0 && strokeColor != INVALID) {
            mNormalBg.setStroke((int) strokeWidthPx, strokeColor);
        }

        if (solidColor != INVALID) {
            mNormalBg.setColor(solidColor);
        }
    }
}
