### LayoutAttrHelper

在布局中使用自定义属性，完成简单的Shape,Selector的设置，避免众多的文件堆在Drawable文件夹中，暂时支持：

1. state_pressed 状态
2. stroke_width,stroke_color 属性
3. solid_color 属性
4. ripple 特性
5. radius 圆角

#### 使用方法

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

运行即可


#### 原理

利用反射，替换LayoutInflater中的`mFactory2`变量，获取布局中View的标签属性，通过指定属性为View设置合适的background


#### 后续

1. 自定义标签
2. 主题切换
3. 加载占位（TextView）