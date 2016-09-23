#一、工程配置
##1、打包配置
app目录keystore文件夹下是keystore文件，打包已经由gradle脚本配置好：
```gradle
signingConfigs {
        release {
            keyAlias 'ceo'
            keyPassword 'tydic001'
            storeFile file('keystore/tydic.keystore')
            storePassword 'tydic001'
        }
    }

buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'
            signingConfig signingConfigs.release
        }
    }
```
打包时直接选择release模式运行即可：
![Build Variant](http://img.blog.csdn.net/20160815114656460)
##2、依赖配置
```gradle
compile 'com.android.support:appcompat-v7:23.4.0'
compile 'com.jakewharton:butterknife:8.2.1'
apt 'com.jakewharton:butterknife-compiler:8.2.1'
compile 'com.android.support:recyclerview-v7:23.4.0'
compile 'com.android.support:design:23.4.0'
compile 'com.github.rey5137:material:1.2.4'
compile 'com.orhanobut:logger:1.15'
compile 'com.github.CymChad:BaseRecyclerViewAdapterHelper:v1.9.3'
compile 'com.google.code.gson:gson:2.7'
compile 'com.squareup.retrofit2:retrofit:2.1.0'
compile 'com.squareup.retrofit2:converter-gson:2.0.2'
compile 'com.snappydb:snappydb-lib:0.5.2'
compile 'com.esotericsoftware.kryo:kryo:2.24.0'
compile 'com.luffykou:android-common-utils:1.1.3'
```
下面来一一解释

- compile 'com.android.support:appcompat-v7:23.4.0'
- compile 'com.android.support:recyclerview-v7:23.4.0'
- compile 'com.android.support:design:23.4.0'
Google提供的支持库

- compile 'com.jakewharton:butterknife:8.2.1'
apt 'com.jakewharton:butterknife-compiler:8.2.1'
Jakewharton大神的butterknife，省去findViewById等方法，简洁高效

- compile 'com.github.rey5137:material:1.2.4'
material风格的各种基本控件

- compile 'com.orhanobut:logger:1.15'
一个强大的日志工具

- compile 'com.github.CymChad:BaseRecyclerViewAdapterHelper:v1.9.3'
一个封装得十分优秀的RecyclerView Adapter的帮助类

- compile 'com.google.code.gson:gson:2.7'
Gson

- compile 'com.squareup.retrofit2:retrofit:2.1.0'
compile 'com.squareup.retrofit2:converter-gson:2.0.2'
Retrofit，type-safe HTTP client for Android and Java 

- compile 'com.snappydb:snappydb-lib:0.5.2'
compile 'com.esotericsoftware.kryo:kryo:2.24.0'
一个操作简单的键值对数据库

- compile 'com.luffykou:android-common-utils:1.1.3'
一个非常全的工具类，包含了Android开发的方方面面

以上库都非常容易在github上找到，不清楚用法或者想学习源码的，可以自行去搜索。
#二、代码封装
##1、BaseActivity
数据和视图的初始化可分别以下方法中实现
```java
protected abstract void initData() ;

protected abstract void initView() ;
```
重载了几个setContentView()方法，可以方便的对标题和菜单项进行设置
```java
// 普通的setContentView
    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
    }

    // 带有title的setContentView
    protected void setContentView(@LayoutRes int layoutResID, @StringRes int titleResID) {
        setContentView(layoutResID, titleResID, -1, MODE_NORMAL);
    }

    // 带有title和返回的setContentView
    protected void setContentView(@LayoutRes int layoutResID, int titleResID, String mode) {
        if(mode == MODE_BACK) {
            setContentView(layoutResID,titleResID,-1,MODE_BACK);
        }
    }

    // 带有title和menu的setContentView
    protected void setContentView(@LayoutRes int layoutResID, @StringRes int titleResID, int mendID) {
        setContentView(layoutResID, titleResID, mendID, MODE_MENU);
    }

    protected void setContentView(@LayoutRes int layoutResID, @StringRes int titleResID, int menuID, String mode) {
        super.setContentView(layoutResID);
        setUpToolbar(titleResID,menuID,mode);
    }
```
当然，也可以单独配置toolbar
```java
	protected void setUpToolbar(@StringRes int titleResID, int menuID, String mode) {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbarTitle = (TextView) findViewById(R.id.toolbar_title);
        setUpToolbarTitle(titleResID);
        if(mode.equals(MODE_NORMAL)) {
        } else if(mode.equals(MODE_BACK)) {
            mToolbar.setNavigationIcon(R.drawable.btn_back);
            mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        } else if(mode.equals(MODE_MENU)) {
            setUpMenu(menuID);
        } else if(mode.equals(MODE_ALL)) {
            mToolbar.setNavigationIcon(R.drawable.btn_back);
            mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
            setUpMenu(menuID);
        }
    }

    protected void setUpToolbar(String title, int menuID, String mode) {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbarTitle = (TextView) findViewById(R.id.toolbar_title);
        setUpToolbarTitle(title);
        if(mode.equals(MODE_NORMAL)) {
        } else if(mode.equals(MODE_BACK)) {
            mToolbar.setNavigationIcon(R.drawable.btn_back);
            mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        } else if(mode.equals(MODE_MENU)) {
            setUpMenu(menuID);
        } else if(mode.equals(MODE_ALL)) {
            mToolbar.setNavigationIcon(R.drawable.btn_back);
            mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
            setUpMenu(menuID);
        }
    }
```
单独配置标题
```java
	protected void setUpToolbarTitle(@StringRes int titleResID) {
        if(mToolbarTitle != null && titleResID > 0) {
            mToolbarTitle.setText(titleResID);
        }
    }

    protected void setUpToolbarTitle(String title) {
        if(mToolbarTitle != null && title != null) {
            mToolbarTitle.setText(title);
        }
    }
```
单独配置菜单
```java
	protected void setUpMenu(int menuId) {
        if(mToolbar != null) {
            mToolbar.getMenu().clear();
            if(menuId > 0) {
                mToolbar.inflateMenu(menuId);
                mToolbar.setOnMenuItemClickListener(this);
            }
        }
    }
```
重写onMenuItemClick(MenuItem item)方法可以对菜单的选择事件进行处理。

该方法处理fragment
```java
	protected void replace(int containerViewId, Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(containerViewId, fragment);
        fragmentTransaction.commitAllowingStateLoss();
    }
```

该方法用于不传递数据时，跳转Activity
```java
	protected void openActivity(Class<?> cls) {
        Intent intent = new Intent(this, cls);
        startActivity(intent);
    }
```
以下两个方法对弹吐司进行了简单的封装
```java
	protected void showToastShort(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

    protected void showToastLong(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_LONG).show();
    }
```
BaseFragment的封装类似，下文就不再赘述。
##3、控件封装
widget下是两个自定义控件，BottomNavigation是底部导航栏，使用方法在MainActivity里展示得非常清楚，而BottomFragment并不是一个Fragment，只是一个从屏幕下方弹起的弹窗，效果如下：
![BottomFragment](http://img.blog.csdn.net/20160815152948962)
而其中的数据源就是BottomType类型的，包含图片和文字描述
```java
	public class BottomType {
    public int imgResId;
    public int stringResId;

    public BottomType(int imgResId, int stringResId) {
        this.imgResId = imgResId;
        this.stringResId = stringResId;
    }

    public int getImgResId() {
        return imgResId;
    }

    public void setImgResId(int imgResId) {
        this.imgResId = imgResId;
    }

    public int getStringResId() {
        return stringResId;
    }

    public void setStringResId(int stringResId) {
        this.stringResId = stringResId;
    }
}
```