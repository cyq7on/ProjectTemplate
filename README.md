#一、工程配置
##1、打包配置
打开注释，自己完善一下：
```gradle
signingConfigs {
        release {
//            keyAlias 'xxx'
//            keyPassword 'xxx'
//            storeFile file('sign/xxx.jks')
//            storePassword 'xxx'
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
该方法用于处理Fragment
```java
	protected void showFragment(String hideTag,String showTag) {
        FragmentTransaction transaction = getSupportFragmentManager().
                beginTransaction();
        Fragment hideFragment =   getSupportFragmentManager().findFragmentByTag(hideTag);
        Fragment showFragment = getSupportFragmentManager().findFragmentByTag(showTag);
        transaction.hide(hideFragment);
        transaction.show(showFragment);
        transaction.commit();
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
附上几张主页面截图：
![这里写图片描述](http://img.blog.csdn.net/20160923165822074)
![这里写图片描述](http://img.blog.csdn.net/20160923165836293)
![这里写图片描述](http://img.blog.csdn.net/20160923165847090)
###感谢： WhyAwaysMe