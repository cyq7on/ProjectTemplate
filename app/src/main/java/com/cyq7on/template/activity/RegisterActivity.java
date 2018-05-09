package com.cyq7on.template.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.cyq7on.template.R;
import com.cyq7on.template.base.BaseActivity;

import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends BaseActivity {

    @BindView(R.id.et_new_account)
    EditText mEtNewAccount;
    @BindView(R.id.et_password_first)
    EditText mEtPasswordFirst;
    @BindView(R.id.et_password_second)
    EditText mEtPasswordSecond;
    @BindView(R.id.et_dynamic_pwd)
    EditText etDynamicPwd;
    @BindView(R.id.btnGetCode)
    Button btnGetCode;
    @BindView(R.id.btn_register)
    Button btnRegister;

    //8-16位数字字母组合
    private String regex = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,16}$";

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

    @OnClick({R.id.btnGetCode, R.id.btn_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnGetCode:
                btnGetCode.setText("60s");
                final Timer timer = new Timer();
                TimerTask task = new TimerTask() {
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                String text = btnGetCode.getText().toString();
                                int i = Integer.parseInt(text.substring(0, text.length() - 1));
                                btnGetCode.setText(String.format(Locale.CHINA,"%ds",--i));
                                if(i == 0){
                                    timer.cancel();
                                    btnGetCode.setText(R.string.register_dynamic_pwd_get_again);
                                }
                            }
                        });
                    }
                };
                timer.schedule(task, 0, 1000);

                break;
            case R.id.btn_register:
                break;
        }
    }
}
