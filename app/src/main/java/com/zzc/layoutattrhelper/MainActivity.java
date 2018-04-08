package com.zzc.layoutattrhelper;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends BaseActivity {

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    public void click(View view) {
        switch (view.getId()) {
            case R.id.tv_normal:
                startActivity(new Intent(this, ShapeActivity.class));
                break;
            case R.id.tv_selector:
                startActivity(new Intent(this, SelectorActivity.class));
                break;
            case R.id.tv_theme_res:
                startActivity(new Intent(this, ThemeResActivity.class));
                break;
            case R.id.tv_theme_file:
                startActivity(new Intent(this, ThemeFileActivity.class));
                break;
            default:
                break;
        }
    }
}
