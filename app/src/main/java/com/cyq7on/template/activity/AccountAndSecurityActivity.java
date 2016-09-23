package com.cyq7on.template.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.cyq7on.template.R;
import com.cyq7on.template.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AccountAndSecurityActivity extends BaseActivity {


    @BindView(R.id.input_name)
    EditText inputName;
    @BindView(R.id.input_phone)
    EditText inputPhone;
    @BindView(R.id.input_address)
    EditText inputAddress;
    @BindView(R.id.input_email)
    EditText inputEmail;
    @BindView(R.id.modify_passwrod)
    LinearLayout modifyPasswrod;
    @BindView(R.id.sub_account_management)
    LinearLayout subAccountManagement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_and_security, R.string.account_and_security, MODE_BACK);
        ButterKnife.bind(this);

        initView();
        initData();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

    }


    @OnClick({R.id.modify_passwrod, R.id.sub_account_management})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.modify_passwrod:
                openActivity(ModifyPasswordActivity.class);
                break;
            case R.id.sub_account_management:
                break;
        }
    }
}
