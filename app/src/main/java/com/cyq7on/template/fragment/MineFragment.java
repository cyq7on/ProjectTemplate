package com.cyq7on.template.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.cyq7on.template.R;
import com.cyq7on.template.activity.AboutActivity;
import com.cyq7on.template.activity.AccountAndSecurityActivity;
import com.cyq7on.template.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by HelloWorld on 2016/7/20.
 */
public class MineFragment extends BaseFragment {

    @BindView(R.id.account_and_security)
    LinearLayout accountAndSecurity;
    @BindView(R.id.setting)
    LinearLayout setting;
    @BindView(R.id.scene_mode)
    LinearLayout sceneMode;
    @BindView(R.id.about)
    LinearLayout about;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    protected void initView() {
        setUpToolbarTitle(R.string.mine);
    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.account_and_security, R.id.setting, R.id.scene_mode, R.id.about})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.account_and_security:
                openActivity(AccountAndSecurityActivity.class);
                break;
            case R.id.setting:

                break;
            case R.id.scene_mode:
                break;
            case R.id.about:
                openActivity(AboutActivity.class);
                break;
        }
    }
}
