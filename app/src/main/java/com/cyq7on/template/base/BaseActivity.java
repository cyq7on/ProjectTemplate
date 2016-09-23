package com.cyq7on.template.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.cyq7on.template.R;


/**
 * Created by Long
 * on 2016/7/16.
 */
public abstract class BaseActivity extends AppCompatActivity implements Toolbar.OnMenuItemClickListener {


    protected Toolbar mToolbar;
    protected TextView mToolbarTitle;
    protected final static String MODE_NORMAL = "MODE_NORMAL";
    protected final static String MODE_BACK = "MODE_BACK";
    protected final static String MODE_MENU = "MODE_MENU";
    protected final static String MODE_ALL = "MODE_ALL";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    protected abstract void initData() ;

    protected abstract void initView() ;


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

    protected void setUpMenu(int menuId) {
        if(mToolbar != null) {
            mToolbar.getMenu().clear();
            if(menuId > 0) {
                mToolbar.inflateMenu(menuId);
                mToolbar.setOnMenuItemClickListener(this);
            }
        }
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        return false;
    }


    protected void openActivity(Class<?> cls) {
        Intent intent = new Intent(this, cls);
        startActivity(intent);
    }

    protected void showFragment(String hideTag,String showTag) {
        FragmentTransaction transaction = getSupportFragmentManager().
                beginTransaction();
        Fragment hideFragment = getSupportFragmentManager().findFragmentByTag(hideTag);
        Fragment showFragment = getSupportFragmentManager().findFragmentByTag(showTag);
        transaction.hide(hideFragment);
        transaction.show(showFragment);
        transaction.commit();
    }

    protected void showToastShort(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

    protected void showToastLong(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_LONG).show();
    }
}

