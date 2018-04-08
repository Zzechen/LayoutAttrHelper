### LayoutAttrHelper

1. 在布局中使用自定义属性，完成简单的Shape,Selector的设置，避免众多的文件堆在Drawable文件夹中，暂时支持：

    1. state_pressed 状态
    2. stroke_width,stroke_color 属性
    3. solid_color 属性
    4. ripple 特性
    5. radius 圆角

2. 在切换主题，暂时支持：

    1. 文字颜色
    2. 背景（drawable，color）


### 使用


#### Shape/Selector

1. 在布局中使用下列属性

    ```
    <attr name="helper_radius" format="dimension"/>
    <attr name="helper_ripple_enable" format="boolean"/>
    <attr name="helper_stroke_width" format="dimension"/>
    <attr name="helper_stroke_color" format="color"/>
    <attr name="helper_stroke_press_color" format="color"/>
    <attr name="helper_solid_color" format="color"/>
    <attr name="helper_solid_press_color" format="color"/>
    ```
    
    如在activity_main.xml中：
    
    ```
    <?xml version="1.0" encoding="utf-8"?>
    <LinearLayout
        xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:app="http://schemas.android.com/apk/res-auto"
        xmlns:tools="http://schemas.android.com/tools"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:gravity="center_horizontal"
        android:orientation="vertical"
        tools:ignore="MissingPrefix">
    
        <Button
            android:id="@+id/btn"
            android:layout_width="200dp"
            android:layout_height="wrap_content"
            android:layout_margin="10dp"
            android:text="Hello World!"
            android:textColor="#ffffff"
            app:helper_radius="30dp"
            app:helper_solid_color="#FF4081"
            app:helper_solid_press_color="@color/colorPrimary"
            />
    
    </LinearLayout>
    ```
    可使用`tools:ignore="MissingPrefix"`取消警告
    
2. 在layout所在Activity中调用`LayoutAttrHelper.compose`方法

    **该方法需要在super.onCreate()和setContentView()中间调用**
    
    ```
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            LayoutAttrHelper.compose(this);
            setContentView(R.layout.activity_main);
    }
    ```

#### 切换主题

1. 在工程中的attrs.xml文件中定义主题切换字段的名称，如

    ```
    <attr name="theme_text_bg" format="reference|color"/>
    <attr name="theme_text_color" format="reference|color"/>
    ```

2. 在主题（styles.xml）中使用上述字段，声明具体属性的值，如：

    ```
    <style name="AppTheme.Day" parent="AppTheme">
        <item name="theme_text_bg">@color/colorPrimary</item>
        <item name="theme_text_color">@color/colorAccent</item>
        <item name="theme_drawable_bg">@drawable/bg_day</item>
        <item name="theme_image_bg">@drawable/img_day</item>
    </style>
    <style name="AppTheme.Night" parent="AppTheme">
        <item name="theme_text_bg">#FF40D2</item>
        <item name="theme_text_color">@color/colorPrimary</item>
        <item name="theme_drawable_bg">@drawable/bg_night</item>
        <item name="theme_image_bg">@drawable/img_night</item>
    </style>
    
    ```
3. 在xml布局中使用`<attr name="helper_support_theme" format="boolean"/>`属性，标记该view支持主题切换，并使用`?attr/xxx`定义属性值，如：

    ```
    <LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
                  xmlns:app="http://schemas.android.com/apk/res-auto"
                  xmlns:tools="http://schemas.android.com/tools"
                  android:layout_width="match_parent"
                  android:layout_height="match_parent"
                  android:orientation="vertical"
                  tools:ignore="MissingPrefix">
    
        <TextView
            android:layout_width="match_parent"
            android:layout_height="50dp"
            android:background="?attr/theme_text_bg"
            android:gravity="center"
            android:text="color(background + text)"
            android:textColor="?attr/theme_text_color"
            app:helper_support_theme="true"
            />
    </LinearLayout>
    ```

4. 使用LayoutAttrHelper中compose方法的返回值，即ThemeHelper的setTheme方法，如：

    ```
    @Override
    public void setTheme(int resid) {
        super.setTheme(resid);
        if (mThemeHelper  != null){
            mThemeHelper.setTheme(resid);
        }
    }
    ```
    
    **Activity.setTheme可能会发生在Activity.onCreate之前，所以需要判空；调用ThemeHelper.setTheme之前，需要先调用Activity.setTheme**


### 原理

利用反射，替换LayoutInflater中的`mFactory2`变量，获取布局中View的标签属性

### 参考

1. [Colorful](https://github.com/hehonghui/Colorful)
2. [FlycoRoundView](https://github.com/H07000223/FlycoRoundView)

### 后续

1. 自定义标签
2. 主题切换(文件)