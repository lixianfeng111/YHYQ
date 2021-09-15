package com.yhhl.yhyq;

import butterknife.BindView;
import android.content.Intent;
import android.net.Uri;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.yhhl.yhyq.base.BaseActivity;
import com.yhhl.yhyq.base.BasePresenter;

public class UrlActivity extends BaseActivity {

    @BindView(R.id.webView)
    WebView webView;
    String url="http://114.251.113.1:34668/citybase/explorer/index.html?pmtsUrl=http" +
            "%3A%2F%2F114.251.113.1%3A34668%2FFreedoMetaSvc%2Fservice%2Fmeta%2Fpmts%2F1.1.0%" +
            "2FPMTSCapabilities.json%3Ftoken%3DqwbZJfCq7Ls-ijOLj2nAtISMfSQUhuu4v_YZi7_vosI46Pg" +
            "TEgZlxw7L83qTVeYr7IPE96EQtE31yoQM4SonskvHMabbnv519dhwGkvJ1i1aZJWiV2Opc6yENFVdmlZivXZ" +
            "6c2k5es7uZc4WxHroQFt4kotZ7giem0q0kVBpNe5_nH4XoKjyrwwS-D68oAE7XDYPgm5I4SvhqjtvEgpu1N4r" +
            "qdNzC50ZLmGrT2wnoQAf56VAZ1KyMpAFaZ8uyiT37S_4GwmtZt7ya8vRiLJ5xg%3D%3D%26id%3Dd829db9c" +
            "-5a6f-4541-89aa-c33dcfa4f8a1";
    @Override
    public void initView() {
//        Uri uri = Uri.parse("http://10.0.17.31:34668/citybase/explorer/index.html?pmtsUrl=http%3A%2F%2F10.0.17.31%3A34668%2FFreedoMetaSvc%2Fservice%2Fmeta%2Fpmts%2Fdataset%2F1.1.0%2FPMTSCapabilities.json%3Ftoken%3DHvp9jxly9q_nlMzphwKY-414PuUuEKQbxb4f5zGSWdjR9a5YJr86Lz3SWvXq9x9D3fTp4VqIkmR56F8yS8T7zqelEznxdOCPQLel6M-VdrkappkPZEqpSri0dxo6t5ztWmEzHwG4vpuCGbFRkrvnZ4W_DqxcN61pJ_OlwkM-c4Xp9420ne_0h7n-rQqMjNBoS6EKVYcf7f-SyfQF2wTjYO4mBVVbZyS_5PYJfZXKVw_f0USR7p4K8Qj0L8e6l2MaPKP_pSSI0nm214RnIXmuFxZbX_5QlYDgB6PGlg0asF_LS90MgX0VFTUUlHklUMYNGHDd9HdMUNTHRWnnz7AJjw%3D%3D%26id%3Ddb2d77f7-622f-4132-811a-2c7608355db7");    //设置跳转的网站
//        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
//        startActivity(intent);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        webView.getSettings().setJavaScriptEnabled(true);

        webView.setWebViewClient(new WebViewClient());

        webView.loadUrl(url);
    }

    @Override
    public int initLayoutId() {
        return R.layout.activity_url;
    }

    @Override
    public void initVariable() {

    }

    @Override
    public BasePresenter initPresenter() {
        return null;
    }
}