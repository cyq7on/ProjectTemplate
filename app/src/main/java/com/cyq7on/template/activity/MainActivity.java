package com.cyq7on.template.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.cyq7on.template.R;
import com.cyq7on.template.base.BaseActivity;
import com.cyq7on.template.base.BaseFragment;
import com.cyq7on.template.fragment.AlarmInformationFragment;
import com.cyq7on.template.fragment.HomeFragment;
import com.cyq7on.template.fragment.MineFragment;
import com.cyq7on.template.fragment.ShoppingMallFragment;
import com.cyq7on.template.widget.BottomNavigation;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends BaseActivity implements BottomNavigation.onItemClick {


    @BindView(R.id.bottom_navigation)
    BottomNavigation mBottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    @Override
    protected void initView() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        BaseFragment homeFragment = new HomeFragment();
        BaseFragment shoppingMallFragment = new ShoppingMallFragment();
        BaseFragment alarmInformationFragment = new AlarmInformationFragment();
        BaseFragment mineFragment = new MineFragment();
        transaction.add(R.id.main_fragment_container, homeFragment,
                HomeFragment.class.getSimpleName());
        transaction.add(R.id.main_fragment_container, shoppingMallFragment,
                ShoppingMallFragment.class.getSimpleName());
        transaction.add(R.id.main_fragment_container, alarmInformationFragment,
                AlarmInformationFragment.class.getSimpleName());
        transaction.add(R.id.main_fragment_container, mineFragment,
                MineFragment.class.getSimpleName());
        transaction.hide(shoppingMallFragment);
        transaction.hide(alarmInformationFragment);
        transaction.hide(mineFragment);
        transaction.commit();
        ArrayList<BottomNavigation.BottomNavigationItem> items = new ArrayList<>();
        items.add(new BottomNavigation.BottomNavigationItem(R.drawable.icon_device_manager, R.string.home, HomeFragment.class));
        items.add(new BottomNavigation.BottomNavigationItem(R.drawable.icon_shopping_mall, R.string.shopping_mall, ShoppingMallFragment.class));
        items.add(new BottomNavigation.BottomNavigationItem(R.drawable.icon_alarm_information, R.string.information, AlarmInformationFragment.class));
        items.add(new BottomNavigation.BottomNavigationItem(R.drawable.icon_my, R.string.mine, MineFragment.class));
        mBottomNavigation.initData(items, this);
        mBottomNavigation.setCurrentTab(0);
    }

    @Override
    protected void initData() {
        mBottomNavigation.setBadge("1", 3);
    }

    @Override
    public void onTabClick(BottomNavigation.BottomNavigationItem bottomNavigationItem) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        Fragment targetFragment;
        switch (bottomNavigationItem.getLabelResId()) {
            case R.string.home:
                targetFragment = getSupportFragmentManager().
                        findFragmentByTag(HomeFragment.class.getSimpleName());
                //只有第一次homeFragment会为空，目前还没想到更优雅的处理方式
                if (targetFragment == null || targetFragment.isVisible()) {
                    return;
                }
                break;
            case R.string.shopping_mall:
                targetFragment = getSupportFragmentManager().
                        findFragmentByTag(ShoppingMallFragment.class.getSimpleName());
                if (targetFragment.isVisible()) {
                    return;
                }
                break;
            case R.string.information:
                targetFragment = getSupportFragmentManager().
                        findFragmentByTag(AlarmInformationFragment.class.getSimpleName());
                if (targetFragment.isVisible()) {
                    return;
                }
                break;
            case R.string.mine:
                targetFragment = getSupportFragmentManager().
                        findFragmentByTag(MineFragment.class.getSimpleName());
                if (targetFragment.isVisible()) {
                    return;
                }
                break;
            default:
                targetFragment = getSupportFragmentManager().
                        findFragmentByTag(HomeFragment.class.getSimpleName());
                if (targetFragment.isVisible()) {
                    return;
                }
                break;
        }
        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        for (Fragment fragment : fragments) {
            if(fragment.isVisible()){
                transaction.hide(fragment);
            }
        }
        transaction.show(targetFragment);
        transaction.commit();
    }
}
