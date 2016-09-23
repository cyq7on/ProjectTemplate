package com.cyq7on.template.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.cyq7on.template.R;
import com.cyq7on.template.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AboutActivity extends BaseActivity {

    @BindView(R.id.help_and_feedback)
    LinearLayout helpAndFeedback;
    @BindView(R.id.online_instruction)
    LinearLayout onlineInstruction;
    @BindView(R.id.about_us)
    LinearLayout aboutUs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about, R.string.about, MODE_BACK);
        ButterKnife.bind(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

    }

    @OnClick({R.id.help_and_feedback, R.id.online_instruction, R.id.about_us})
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.help_and_feedback:

                break;
            case R.id.online_instruction:

                break;
            case R.id.about_us:

                break;
        }
    }
}
