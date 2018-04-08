package com.zzc.library.theme;

import android.content.res.TypedArray;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

import com.zzc.library.R;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * auth zzc
 * date 2018/4/8
 * desc ${desc}
 */

public class ThemeHelper {
    private static final String TAG = ThemeHelper.class.toString();
    private boolean hasView;
    private int themeId;

    private Map<View, Integer> bgMap;
    private Map<TextView, Integer> textColorMap;


    public ThemeHelper() {
        hasView = false;
        bgMap = new HashMap<>();
        textColorMap = new HashMap<>();
    }

    public void parseAttr(AttributeSet attrs, View view) {
        if (attrs == null || view == null) {
            return;
        }
        TypedArray typedArray = view.getContext().obtainStyledAttributes(attrs, R.styleable.ThemeView);
        if (typedArray.getIndexCount() == 0) {
            return;
        }

        boolean has = typedArray.getBoolean(R.styleable.ThemeView_helper_support_theme, false);
        hasView = has | hasView;

        if (view instanceof TextView) {
            int attributeCount = attrs.getAttributeCount();
            for (int i = 0; i < attributeCount; i++) {
                String attributeName = attrs.getAttributeName(i);
                String attributeValue = attrs.getAttributeValue(i);
                switch (attributeName) {
                    case "textColor":
                        int colorId = Integer.valueOf(attributeValue.replace("?", ""));
                        textColorMap.put((TextView) view, colorId);
                        break;
                    case "background":
                        int bgId = Integer.valueOf(attributeValue.replace("?", ""));
                        bgMap.put(view, bgId);
                        break;
                }
            }
        }
    }

    /**
     * for theme id
     *
     * @param themeId
     */
    public void setTheme(int themeId) {
        if (this.themeId == themeId) return;
        Set<View> bgViews = bgMap.keySet();
        for (View view : bgViews) {
            Integer id = bgMap.get(view);
            TypedValue typedValue = new TypedValue();
            view.getContext().getTheme().resolveAttribute(id, typedValue, true);
            int data = typedValue.data;
            if (data != 0) {
                Drawable background = view.getBackground();
                // TODO: 2018/4/8 other drawable
                if (background instanceof GradientDrawable || background instanceof BitmapDrawable) {
                    TypedArray drawableArray = view.getContext().getTheme().obtainStyledAttributes(themeId, new int[]{id});
                    int resourceId = drawableArray.getResourceId(0, 0);
                    Drawable drawable = view.getResources().getDrawable(resourceId);
                    drawableArray.recycle();
                    view.setBackgroundDrawable(drawable);
                } else if (background instanceof ColorDrawable) {
                    view.setBackgroundColor(data);
                } else {
                    Drawable drawable;
                    if (Build.VERSION.SDK_INT >= 21) {
                        drawable = view.getResources().getDrawable(data, view.getContext().getTheme());
                    } else {
                        drawable = view.getResources().getDrawable(data);
                    }
                    view.setBackgroundDrawable(drawable);
                }
            }
        }
        Set<TextView> textColorViews = textColorMap.keySet();
        for (TextView view : textColorViews) {
            Integer id = textColorMap.get(view);
            TypedValue tv = new TypedValue();
            view.getContext().getTheme().resolveAttribute(id, tv, true);
            int data = tv.data;
            if (data != 0) {
                view.setTextColor(data);
            }
        }
        this.themeId = themeId;
    }


    /**
     * for file
     *
     * @param file
     */
    public void setTheme(String file) {

    }
}
