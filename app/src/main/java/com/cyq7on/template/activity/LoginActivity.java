package com.cyq7on.template.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
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

    @OnClick(R.id.btn_login)
    public void login() {
        SPUtil.putAndApply(this,IS_LOGIN,true);
    }

    @OnClick({R.id.tv_forget_password, R.id.tv_regist})
    public void onClick(View view) {
        if(view.getId() == R.id.tv_forget_password) {

        } else if(view.getId() == R.id.tv_regist) {
            openActivity(RegisterActivity.class);
        }
    }


}

