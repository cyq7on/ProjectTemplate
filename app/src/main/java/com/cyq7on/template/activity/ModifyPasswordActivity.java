package com.cyq7on.template.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.cyq7on.template.R;
import com.cyq7on.template.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ModifyPasswordActivity extends BaseActivity {

    @BindView(R.id.my_email)
    EditText myEmail;
    @BindView(R.id.new_password)
    EditText newPassword;
    @BindView(R.id.new_password_again)
    EditText newPasswordAgain;
    @BindView(R.id.confirm_modify_password)
    Button confirmModifyPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_password, R.string.modify_password, MODE_BACK);
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
}
