package com.cyq7on.template.activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;

import com.cyq7on.template.R;
import com.cyq7on.template.base.BaseActivity;
import com.cyq7on.template.utils.SPUtil;

public class WelcomeActivity extends BaseActivity {
    public static final String LOCAL_VERSION_CODE = "localVersionCode";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        new Handler().postDelayed(new Runnable() {
            private Context context = getApplicationContext();
            @Override
            public void run() {
                int localVersionCode = (int) SPUtil.get(context,LOCAL_VERSION_CODE,0);
                boolean isLogin = (boolean) SPUtil.get(context,LoginActivity.IS_LOGIN,false);
                int versionCode = getVersionCode(context);
                Intent intent;
                if(versionCode > localVersionCode){
                    intent = new Intent(context,GuideActivity.class);
                    intent.putExtra(LOCAL_VERSION_CODE,versionCode);
                    startActivity(intent);
                }else if (isLogin){
                    openActivity(MainActivity.class);
                }else {
                    openActivity(LoginActivity.class);
                }
                finish();
            }
        },1500);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

    }

    /**
     * get App versionCode
     *
     * @param context
     * @return
     */
    private int getVersionCode(Context context) {
        PackageManager packageManager = context.getPackageManager();
        PackageInfo packageInfo;
        int versionCode = 0;
        try {
            packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            versionCode = packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionCode;
    }
}
