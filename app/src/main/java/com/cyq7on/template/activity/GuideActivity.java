package com.cyq7on.template.activity;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.cyq7on.template.R;
import com.cyq7on.template.base.BaseActivity;
import com.cyq7on.template.utils.SPUtil;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GuideActivity extends BaseActivity {

    @BindView(R.id.vpGuide)
    ViewPager vpGuide;
    @BindView(R.id.ll)
    LinearLayout ll;
    @BindView(R.id.redPoint)
    ImageView redPoint;
    @BindView(R.id.guideBtn)
    Button guideBtn;
    private final int[] images = {R.drawable.art_canteen_intro1,
            R.drawable.art_canteen_intro2, R.drawable.art_canteen_intro3};
    private List<View> list;
    private int pointWidth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        ButterKnife.bind(this);
        initView();
        initData();
    }


    @Override
    protected void initData() {
        vpGuide.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //计算当前小红点的左边距
                int leftMargin = (int) (pointWidth * positionOffset + position * pointWidth);
                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) redPoint.getLayoutParams();
                params.leftMargin = leftMargin;
                redPoint.setLayoutParams(params);
            }

            @Override
            public void onPageSelected(int position) {
                if (images.length - 1 == position) {
                    guideBtn.setVisibility(View.VISIBLE);
                } else {
                    guideBtn.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        vpGuide.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return images.length;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {

                container.addView(list.get(position));
                return list.get(position);

            }


            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {

                container.removeView(list.get(position));
            }
        });

        //页面绘制结束之后，计算两个圆点的间距
        //视图树
        ll.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            //layout方法执行结束（位置确定）
            public void onGlobalLayout() {

                //移除监听
                ll.getViewTreeObserver().removeGlobalOnLayoutListener(this);

                //获取两个圆点的间距
                pointWidth = ll.getChildAt(1).getLeft()
                        - ll.getChildAt(0).getLeft();
            }
        });
    }

    @Override
    protected void initView() {
        list = new ArrayList<>();
        for (int i = 0; i < images.length; i++) {
            ImageView imageview = new ImageView(this);
            imageview.setBackgroundResource(images[i]);
            list.add(imageview);

            ImageView imagePoint = new ImageView(this);
            imagePoint.setImageResource(R.drawable.guide_point_normal);

            // 找到布局的参数设置布局
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            params.leftMargin = 15;
            // 从第二个圆点开始设置左边距
            if (i != 0) {
                imagePoint.setLayoutParams(params);
                imagePoint.setEnabled(false); // 除第一个外设置不可用改变指示器颜色
            }
            ll.addView(imagePoint);
        }

    }

    @OnClick(R.id.guideBtn)
    public void onClick() {
        openActivity(LoginActivity.class);
        int versionCode = getIntent().getIntExtra(WelcomeActivity.LOCAL_VERSION_CODE,0);
        SPUtil.putAndApply(this,WelcomeActivity.LOCAL_VERSION_CODE,versionCode);
        Logger.d(versionCode);
    }
}
