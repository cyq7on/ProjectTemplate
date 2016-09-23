package com.cyq7on.template.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.cyq7on.template.R;
import com.cyq7on.template.adapter.HomeAdapter;
import com.cyq7on.template.base.BaseFragment;
import com.cyq7on.template.bean.BottomType;
import com.cyq7on.template.bean.HomeInfo;
import com.cyq7on.template.net.ApiStores;
import com.cyq7on.template.net.AppClient;
import com.cyq7on.template.widget.BottomFragment;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * @Description:
 * @author: cyq7on
 * @date: 2016/8/1 10:29
 * @version: V1.0
 */
public class HomeFragment extends BaseFragment {

    @BindView(R.id.rv_list)
    RecyclerView rvList;
    @BindView(R.id.swiperefreshlayout)
    SwipeRefreshLayout swipeRefreshLayout;

    private HomeAdapter adapter;
    private List<HomeInfo.ResultBean.DataBean> list;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    protected void initView() {
        setUpToolbarTitle(R.string.home_page);
        setUpToolbarMenu(R.menu.device_manager_menu);

        swipeRefreshLayout.setColorSchemeColors(Color.GREEN,
                Color.BLUE,Color.YELLOW);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getData("");
            }
        });
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(true);
            }
        });
        rvList.setHasFixedSize(true);
        rvList.setLayoutManager(new LinearLayoutManager(context));
    }

    @Override
    protected void initData() {
        list = new ArrayList<>();
        adapter = new HomeAdapter(list);
        adapter.openLoadMore(6,true);
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                rvList.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        list.addAll(list.subList(0,9));
                        adapter.notifyDataChangedAfterLoadMore(true);
                    }
                },1000);
            }
        });
        rvList.postDelayed(new Runnable() {
            @Override
            public void run() {
                adapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
            }
        },2000);
        adapter.isFirstOnly(false);
        rvList.setAdapter(adapter);
        getData("keji");
    }

    private void getData(String type) {
        Map<String, String> params = new LinkedHashMap<>();
        params.put("type", type);
        params.put("key", "bbbf6ce68763abcbf0c0071d6f692a9c");
        Retrofit retrofit = AppClient.getRetrofit();
        ApiStores apiStores = retrofit.create(ApiStores.class);
        Call<HomeInfo> call = apiStores.getHomeInfo(params);
        call.enqueue(new Callback<HomeInfo>() {
            @Override
            public void onResponse(Call<HomeInfo> call, Response<HomeInfo> response) {
                HomeInfo homeInfo = response.body();
                if(homeInfo == null){
                    return;
                }
                if(list.size() > 0){
                    list.clear();
                }
                list.addAll(homeInfo.result.data);
                adapter.notifyDataSetChanged();
                swipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<HomeInfo> call, Throwable t) {
                Logger.e(t.getMessage());
                showToastShort(t.getMessage());
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        if (item.getItemId() == R.id.add_device_manager) {
            ArrayList<BottomType> bottomTypes = new ArrayList<>();
            BottomType del = new BottomType(R.drawable.btn_delete, R.string.delete);
            BottomType reName = new BottomType(R.drawable.btn_rename, R.string.rename);
            BottomType alarmHistory = new BottomType(R.drawable.btn_alarm_history, R.string.alarm_history);
            BottomType notificationMode = new BottomType(R.drawable.btn_notification_mode, R.string.notification_mode);
            BottomType pair = new BottomType(R.drawable.btn_pair, R.string.pair_mode);
            BottomType recovery = new BottomType(R.drawable.btn_recovery, R.string.restore_factory_settings);
            BottomType scene = new BottomType(R.drawable.btn_scene, R.string.scene_mode);
            BottomType time = new BottomType(R.drawable.btn_time, R.string.time);
            BottomType top = new BottomType(R.drawable.btn_top, R.string.top);
            BottomType visitingInformation = new BottomType(R.drawable.btn_visiting_information, R.string.visiting_information);
            BottomType volume = new BottomType(R.drawable.btn_volume, R.string.volume);

            bottomTypes.add(del);
            bottomTypes.add(reName);
            bottomTypes.add(alarmHistory);
            bottomTypes.add(notificationMode);
            bottomTypes.add(recovery);
            bottomTypes.add(scene);
            bottomTypes.add(time);
            bottomTypes.add(top);
            bottomTypes.add(visitingInformation);
            bottomTypes.add(volume);
            BottomFragment bottomFragment = new BottomFragment(context);
            bottomFragment.initData(bottomTypes);
            bottomFragment.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    showToastShort(position + "");
                }
            });
        }
        return super.onMenuItemClick(item);
    }
}
