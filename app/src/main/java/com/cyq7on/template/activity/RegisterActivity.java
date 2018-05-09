package com.cyq7on.template.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.cyq7on.template.R;
import com.cyq7on.template.base.BaseActivity;
import com.cyq7on.template.utils.SPUtil;
import com.orhanobut.logger.Logger;

import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.sms.BmobSMS;
import cn.bmob.sms.exception.BmobException;
import cn.bmob.sms.listener.RequestSMSCodeListener;
import cn.bmob.sms.listener.VerifySMSCodeListener;


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
    TextView btnGetCode;
    @BindView(R.id.btn_register)
    Button btnRegister;

    public static final String NAME = "name";
    public static final String PWD = "pwd";

    //8-16位数字字母组合
    private static final String regex = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,16}$";
    private static final String regexTel = "^1([358][0-9]|4[579]|66|7[0135678]|9[89])[0-9]{8}$";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist, R.string.register_or_forget, R.menu.menu_register);
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
                final String tel = mEtNewAccount.getText().toString();
                if (!tel.matches(regexTel)) {
                    showToastShort("请重新输入手机号");
                    return;
                }
                final Timer timer = new Timer();
                /*BmobSMS.requestSMSCode(tel, "cyq7on", new QueryListener<Integer>() {

                    @Override
                    public void done(Integer integer, BmobException e) {
                        if (e == null) {
                            Logger.d("bmob", "短信id：" + integer);//用于查询本次短信发送详情
                        }else {
                            showToastShort("验证码获取失败");
                            timer.cancel();
                            btnGetCode.setText(R.string.register_dynamic_pwd_get_again);
                        }
                    }
                });*/
                BmobSMS.requestSMSCode(getApplicationContext(), tel, "cyq7on", new RequestSMSCodeListener() {

                    @Override
                    public void done(Integer smsId, BmobException e) {
                        if (e == null) {
                            Logger.d("bmob", "短信id：" + smsId);//用于查询本次短信发送详情
                        } else {
                            showToastShort("验证码获取失败");
                            timer.cancel();
                            btnGetCode.setText(R.string.register_dynamic_pwd_get_again);
                        }
                    }
                });
                btnGetCode.setText("60s");
                TimerTask task = new TimerTask() {
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                String text = btnGetCode.getText().toString();
                                int i = Integer.parseInt(text.substring(0, text.length() - 1));
                                btnGetCode.setText(String.format(Locale.CHINA, "%ds", --i));
                                if (i == 0) {
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
                String dynamicPwd = etDynamicPwd.getText().toString();
                if (TextUtils.isEmpty(dynamicPwd)) {
                    showToastShort("请填写验证码");
                    return;
                }
                final String firstPwd = mEtPasswordFirst.getText().toString();
                if (!firstPwd.matches(regex)) {
                    showToastShort("密码输入不合法");
                    return;
                }
                String secondPwd = mEtPasswordSecond.getText().toString();
                if (!firstPwd.equals(secondPwd)) {
                    showToastShort("两次密码输入不一致");
                    return;
                }
                final String account = mEtNewAccount.getText().toString();
                BmobSMS.verifySmsCode(getApplicationContext(), account, dynamicPwd, new VerifySMSCodeListener() {

                    @Override
                    public void done(BmobException e) {
                        if (e == null) {//短信验证码已验证成功
                            Logger.d("bmob", "验证通过");
                            SPUtil.putAndApply(getApplicationContext(),NAME,account);
                            SPUtil.putAndApply(getApplicationContext(),PWD,firstPwd);
                            finish();
                            showToastShort("注册成功");
                        } else {
                            showToastShort("验证码错误");
                            Logger.d("bmob", "验证失败：code =" + e.getErrorCode() + ",msg = " + e.getLocalizedMessage());
                        }
                    }
                });
                break;
        }
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        finish();
        return true;
    }
}
