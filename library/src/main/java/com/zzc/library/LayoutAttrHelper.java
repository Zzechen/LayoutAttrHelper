package com.zzc.library;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.zzc.library.drawable.DrawableCreater;
import com.zzc.library.theme.ThemeHelper;
import com.zzc.library.theme.ResourcesLoader;

import java.lang.reflect.Field;

/**
 * auth zzc
 * date 2018/4/4
 * desc ${desc}
 */

public class LayoutAttrHelper {
    private static final String TAG = LayoutAttrHelper.class.toString();

    private LayoutAttrHelper() {
    }

    public static void initForFile(Context context){
        ResourcesLoader.setContext(context);
    }

    public static ThemeHelper compose(Activity activity) {
        ThemeHelper themeHelper = new ThemeHelper();
        LayoutInflater layoutInflater = activity.getLayoutInflater();
        final LayoutInflater.Factory2 factory2 = layoutInflater.getFactory2();
        if (factory2 == null) {
            Log.e(TAG, "factory2 is null in " + activity.getClass().getName() + ",are you call this between super.onCreate() and setContentView()");
            return themeHelper;
        }

        Factory2Wrapper factory2Wrapper = new Factory2Wrapper(factory2, themeHelper);
        /**
         * {@link android.support.v4.view.LayoutInflaterCompat#forceSetFactory2(LayoutInflater, LayoutInflater.Factory2)}
         */
        try {
            Field mFactory2Field = LayoutInflater.class.getDeclaredField("mFactory2");
            mFactory2Field.setAccessible(true);
            mFactory2Field.set(layoutInflater, factory2Wrapper);
        } catch (NoSuchFieldException e) {
            Log.e(TAG, "forceSetFactory2 Could not find field 'mFactory2' on class "
                    + LayoutInflater.class.getName()
                    + "; inflation may have unexpected results.", e);
        } catch (IllegalAccessException e) {
            Log.e(TAG, "forceSetFactory2 could not set the Factory2 on LayoutInflater "
                    + layoutInflater + "; inflation may have unexpected results.", e);
        }
        return themeHelper;
    }

    static class Factory2Wrapper implements LayoutInflater.Factory2 {
        private LayoutInflater.Factory2 origin;
        private ThemeHelper themeHelper;

        public Factory2Wrapper(LayoutInflater.Factory2 origin, ThemeHelper themeHelper) {
            this.origin = origin;
            this.themeHelper = themeHelper;
        }

        @Override
        public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
            View view = origin.onCreateView(parent, name, context, attrs);
            DrawableCreater.setDrawable(attrs, view);
            themeHelper.parseAttr(attrs, view);
            return view;
        }

        @Override
        public View onCreateView(String name, Context context, AttributeSet attrs) {
            View view = origin.onCreateView(name, context, attrs);
            DrawableCreater.setDrawable(attrs, view);
            themeHelper.parseAttr(attrs, view);
            return view;
        }
    }
}
