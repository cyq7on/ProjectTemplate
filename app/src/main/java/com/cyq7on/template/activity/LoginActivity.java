package com.cyq7on.template.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.cyq7on.template.R;
import com.cyq7on.template.base.BaseActivity;
import com.cyq7on.template.utils.SPUtil;
import com.rey.material.app.Dialog;
import com.rey.material.app.DialogFragment;
import com.rey.material.app.SimpleDialog;
import com.rey.material.widget.ProgressView;
import com.snappydb.DB;
import com.snappydb.DBFactory;
import com.snappydb.SnappydbException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends BaseActivity {

    @BindView(R.id.loadingview)
    ProgressView loadingView;
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
    private DB snappydb;
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
        try {
            snappydb = DBFactory.open(this, "template");
        } catch (SnappydbException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void initView() {

    }

    @OnClick(R.id.btn_login)
    public void login() {
        SPUtil.putAndApply(this,IS_LOGIN,true);
        loadingView.setVisibility(View.VISIBLE);
    }

    @OnClick({R.id.tv_forget_password, R.id.tv_regist})
    public void onClick(View view) {
        if(view.getId() == R.id.tv_forget_password) {
            Dialog.Builder builder = new SimpleDialog.Builder(){

                @Override
                protected void onBuildDone(Dialog dialog) {
                    dialog.layoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                }

                @Override
                public void onPositiveActionClicked(DialogFragment fragment) {
                    View forgetAccount = fragment.getDialog().findViewById(R.id.et_forget_accounts);
                    // find accounts
                    super.onPositiveActionClicked(fragment);
                }

                @Override
                public void onNegativeActionClicked(DialogFragment fragment) {
                    super.onNegativeActionClicked(fragment);
                }
            };

            builder .positiveAction(getString(R.string.confirm))
                    .negativeAction(getString(R.string.cancel))
                    .contentView(R.layout.dialog_input);
            DialogFragment fragment = DialogFragment.newInstance(builder);
            fragment.show(getSupportFragmentManager(), null);
        } else if(view.getId() == R.id.tv_regist) {
            openActivity(RegisterActivity.class);
        }
    }


}

