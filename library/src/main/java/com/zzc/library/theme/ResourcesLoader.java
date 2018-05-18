package com.zzc.library.theme;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import java.io.File;
import java.lang.reflect.Method;

/**
 * auth zzc
 * date 2018/4/8
 * desc ${desc}
 */

public class ResourcesLoader {
    static Context sContext;
    private static Resources sResources;
    private static String sPackageName;
    private static boolean isFile;

    public static void setContext(Context context) {
        sContext = context.getApplicationContext();
    }

    public static boolean load(File file) {
        if (file == null || !file.exists()) {
            return isFile = false;
        }
        PackageManager packageManager = sContext.getPackageManager();
        PackageInfo archiveInfo = packageManager.getPackageArchiveInfo(file.getAbsolutePath(), PackageManager.GET_ACTIVITIES);
        sPackageName = archiveInfo.packageName;
        try {
            AssetManager assetManager = AssetManager.class.newInstance();
            Method addAssetPath = assetManager.getClass().getMethod("addAssetPath", String.class);
            addAssetPath.invoke(assetManager, file.getAbsolutePath());
            Resources superRes = sContext.getResources();
            sResources = new Resources(assetManager, superRes.getDisplayMetrics(), superRes.getConfiguration());
            return isFile = true;
        } catch (Exception e) {
            return isFile = false;
        }
    }

    static Drawable getDrawable(int resId) {
        return null;
    }

    static ColorStateList getColor(int resId) {
        return null;
    }


}
