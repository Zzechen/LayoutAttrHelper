package com.zzc.layoutattrhelper;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

/**
 * auth zzc
 * date 2018/4/8
 * desc ${desc}
 */

public class ThemeResActivity extends BaseActivity {

    @Override
    protected void initView(Bundle savedInstanceState) {
        RecyclerView rv = findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(new ThemeAdapter());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_theme_res;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_theme, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.theme_day:
                setTheme(R.style.AppTheme_Day);
                return true;
            case R.id.theme_night:
                setTheme(R.style.AppTheme_Night);
                return true;
            default:
                return false;
        }
    }
}
