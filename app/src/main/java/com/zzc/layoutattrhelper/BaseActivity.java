package com.zzc.layoutattrhelper;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.zzc.library.LayoutAttrHelper;
import com.zzc.library.theme.ThemeHelper;

/**
 * auth zzc
 * date 2018/4/8
 * desc ${desc}
 */

public abstract class BaseActivity extends AppCompatActivity {

    protected ThemeHelper mThemeHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mThemeHelper = LayoutAttrHelper.compose(this);
        setContentView(getLayoutId());
        initView(savedInstanceState);
    }

    @Override
    public void setTheme(int resid) {
        super.setTheme(resid);
        if (mThemeHelper  != null){
            mThemeHelper.setTheme(resid);
        }
    }

    public void setTheme(String file) {
        mThemeHelper.setTheme(file);
    }

    protected abstract void initView(Bundle savedInstanceState);

    @LayoutRes
    protected abstract int getLayoutId();
}
