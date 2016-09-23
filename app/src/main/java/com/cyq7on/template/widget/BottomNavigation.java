package com.cyq7on.template.widget;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cyq7on.template.R;
import com.cyq7on.template.base.BaseFragment;

import java.util.ArrayList;

/**
 * Created by Long
 * on 2016/7/19.
 */
public class BottomNavigation extends LinearLayout implements View.OnClickListener {

    private View selectView;
    private int tabsCount;
    private onItemClick mOnItemClick;
    private ArrayList<BottomNavigationItem> bottomNavigationItems;

    public BottomNavigation(Context context) {
        super(context);
        initView();
    }

    public BottomNavigation(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public BottomNavigation(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        setOrientation(HORIZONTAL);
    }

    public void initData(ArrayList<BottomNavigationItem> bottomNavigationItems, onItemClick onItemClick) {
        if(bottomNavigationItems != null && bottomNavigationItems.size() > 0) {
            this.bottomNavigationItems = bottomNavigationItems;
            tabsCount = bottomNavigationItems.size();
            mOnItemClick = onItemClick;
            LayoutParams params = new LayoutParams(0, LayoutParams.WRAP_CONTENT);
            params.weight = 1;
            params.gravity = Gravity.CENTER;
            for (int i = 0; i < bottomNavigationItems.size(); i++) {
                TabView tabView = new TabView(getContext());
                tabView.setTag(bottomNavigationItems.get(i));
                tabView.initData(bottomNavigationItems.get(i));
                tabView.setOnClickListener(this);
                addView(tabView, params);
            }
        }
    }

    public void setCurrentTab(int i) {
        if( i < tabsCount && i >= 0) {
            View view = getChildAt(i);
            onClick(view);
        }
    }

    public void setBadge(String badge, int position) {
        if(position >= 0 && position < tabsCount && bottomNavigationItems != null) {
            BottomNavigationItem bottomNavigationItem = bottomNavigationItems.get(position);
            bottomNavigationItem.setBadge(badge);
            TabView tabView = (TabView) getChildAt(position);
            tabView.initData(bottomNavigationItem);
        }
    }

    @Override
    public void onClick(View v) {
        if(selectView != v) {
            v.setSelected(true);
            mOnItemClick.onTabClick((BottomNavigationItem) v.getTag());
            if(selectView != null) {
                selectView.setSelected(false);
            }
            selectView = v;
        }
    }

    public interface onItemClick {
        void onTabClick(BottomNavigationItem bottomNavigationItem);
    }

    class TabView extends FrameLayout {

        private ImageView mTabImg;
        private TextView mTabLabel;
        private TextView mTabBadge;

        public TabView(Context context) {
            super(context);
            initView();
        }

        public TabView(Context context, AttributeSet attrs) {
            super(context, attrs);
            initView();
        }

        public TabView(Context context, AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
            initView();
        }

        private void initView() {
            LayoutInflater.from(getContext()).inflate(R.layout.bottom_navigation_item, this, true);
            mTabImg = (ImageView) findViewById(R.id.bottom_navigation_item_icon);
            mTabLabel = (TextView) findViewById(R.id.bottom_navigation_item_title);
            mTabBadge = (TextView) findViewById(R.id.bottom_navigation_notification);
        }

        public void initData(BottomNavigationItem bottomNavigationItem) {
            mTabImg.setBackgroundResource(bottomNavigationItem.imgResId);
            mTabLabel.setText(bottomNavigationItem.labelResId);
            if(!TextUtils.isEmpty(bottomNavigationItem.getBadge())) {
                mTabBadge.setVisibility(VISIBLE);
                mTabBadge.setText(bottomNavigationItem.getBadge() + "");
            } else {
                mTabBadge.setVisibility(GONE);
            }
        }

    }

    public static class BottomNavigationItem {
        private int imgResId;
        private int labelResId;
        private String badge;
        private Class<? extends BaseFragment> targetFragmentClz;

        public BottomNavigationItem(int imgResId, int labelResId) {
            this.imgResId = imgResId;
            this.labelResId = labelResId;
        }


        public BottomNavigationItem(int imgResId, int labelResId, Class<? extends BaseFragment> targetFragmentClz) {
            this.imgResId = imgResId;
            this.labelResId = labelResId;
            this.targetFragmentClz = targetFragmentClz;
        }


        public BottomNavigationItem(int imgResId, int labelResId, Class<? extends BaseFragment> targetFragmentClz, String badge) {
            this.imgResId = imgResId;
            this.labelResId = labelResId;
            this.targetFragmentClz = targetFragmentClz;
            this.badge = badge;
        }

        public int getImgResId() {
            return imgResId;
        }

        public int getLabelResId() {
            return labelResId;
        }


        public Class<? extends BaseFragment> getTargetFragmentClz() {
            return targetFragmentClz;
        }

        public String getBadge() {
            return badge;
        }

        public void setBadge(String badge) {
            this.badge = badge;
        }
    }
}