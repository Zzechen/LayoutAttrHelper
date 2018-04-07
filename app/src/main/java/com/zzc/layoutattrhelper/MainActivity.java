package com.zzc.layoutattrhelper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.zzc.library.LayoutAttrHelper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutAttrHelper.compose(this);
        setContentView(R.layout.activity_main);
    }

    private static final String TAG = MainActivity.class.toString();

    public void click(View view) {
        Log.e(TAG, "click: ");
    }
}
