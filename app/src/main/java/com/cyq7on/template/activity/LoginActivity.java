package com.cyq7on.template.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.cyq7on.template.R;
import com.cyq7on.template.base.BaseActivity;
import com.cyq7on.template.utils.SPUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends BaseActivity {

    @BindView(R.id.et_user_name)
    EditText etUserName;
    @BindView(R.id.et_user_password)
    EditText etUserPassword;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.tv_forget_password)
    TextView tvForgetPassword;
    @BindView(R.id.tv_regist)
    TextView tvRegister;
    public static final String IS_LOGIN = "isLogin";
    private String name;
    private String pwd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        initData();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void onStart() {
        super.onStart();
        name = (String) SPUtil.get(getApplicationContext(), RegisterActivity.NAME,"");
        pwd = (String) SPUtil.get(getApplicationContext(), RegisterActivity.PWD,"");
        if(TextUtils.isEmpty(name) || TextUtils.isEmpty(pwd)){
            return;
        }
        etUserName.setText(name);
        etUserPassword.setText(pwd);
    }

    @OnClick(R.id.btn_login)
    public void login() {
        SPUtil.putAndApply(this, IS_LOGIN, true);
        String name = etUserName.getText().toString();
        String pwd = etUserPassword.getText().toString();
        if(TextUtils.isEmpty(name)){
            showToastShort("请输入账号");
            return;
        }
        if(TextUtils.isEmpty(pwd)){
            showToastShort("请输入密码");
            return;
        }
        if(this.name.equals(name) && this.pwd.equals(pwd)){
//            openActivity(MainActivity.class);
            showToastShort("登录成功");
        }else {
            showToastShort("登录失败");
        }
    }

    @OnClick({R.id.tv_forget_password, R.id.tv_regist})
    public void onClick(View view) {
        if (view.getId() == R.id.tv_forget_password) {
            openActivity(RegisterActivity.class);
        } else if (view.getId() == R.id.tv_regist) {
            openActivity(RegisterActivity.class);
        }
    }


}

