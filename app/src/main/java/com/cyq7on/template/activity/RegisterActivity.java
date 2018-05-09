package com.cyq7on.template.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.cyq7on.template.R;
import com.cyq7on.template.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegisterActivity extends BaseActivity {

    @BindView(R.id.et_new_account)
    EditText mEtNewAccount;
    @BindView(R.id.et_password_first)
    EditText mEtPasswordFirst;
    @BindView(R.id.et_password_second)
    EditText mEtPasswordSecond;
    @BindView(R.id.btn_login)
    Button mBtnLogin;
    @BindView(R.id.agree_checkbox)
    CheckBox mAgreeCheckbox;
    @BindView(R.id.tv_user_agreement)
    TextView mTvUserAgreement;
    @BindView(R.id.tv_privacy_policy)
    TextView mTvPrivacyPolicy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist, R.string.register_or_forget, MODE_BACK);
        ButterKnife.bind(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

    }
}
