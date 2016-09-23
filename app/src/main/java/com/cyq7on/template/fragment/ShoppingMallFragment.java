package com.cyq7on.template.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.rey.material.widget.ProgressView;
import com.cyq7on.template.R;
import com.cyq7on.template.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Description: webView加载网页
 * @author: cyq7on
 * @date: 2016/8/1 9:48
 * @version: V1.0
 */
public class ShoppingMallFragment extends BaseFragment {
    @BindView(R.id.webview)
    WebView webView;
    @BindView(R.id.loadingview)
    ProgressView loadingView;
    protected WebSettings webViewSettings;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.webview_base, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    protected void initView() {
        setUpToolbarTitle(R.string.shopping_mall);


        loadingView.setVisibility(View.GONE);
        webViewSettings = webView.getSettings();
        webViewSettings.setJavaScriptEnabled(true);
        webViewSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        //设置可在大视野范围内上下左右拖动，并且可以任意比例缩放
        webViewSettings.setUseWideViewPort(true);
        //设置默认加载的可视范围是大视野范围
        webViewSettings.setLoadWithOverviewMode(true);

        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }
        });
    }

    @Override
    protected void initData() {
        webView.loadUrl("https://www.taobao.com/");
    }
}
