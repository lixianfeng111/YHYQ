package com.yhhl.yhyq.person;


import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.yhhl.yhyq.R;
import com.yhhl.yhyq.base.BaseActivity;
import com.yhhl.yhyq.base.BasePresenter;
import com.yhhl.yhyq.util.ImageUtil;
import com.yhhl.yhyq.util.ToastUtil;
import com.yhhl.yhyq.util.UmengShareUtils;

import static com.yhhl.yhyq.util.ImageUtil.createViewBitmap;

public class VCardActivity extends BaseActivity implements View.OnClickListener {
    private ImageView back;
    private TextView title;
    private RelativeLayout image_re;
    private RelativeLayout share;
    private RelativeLayout save_re;


    @Override
    public void initView() {
        title = findViewById(R.id.title);
        back = findViewById(R.id.back);
        image_re = findViewById(R.id.image_re);
        share = findViewById(R.id.share);
        save_re = findViewById(R.id.save_re);
        title.setText("电子名片");
    }

    @Override
    public void initListener() {
        share.setOnClickListener(this);
        save_re.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public int initLayoutId() {
        return R.layout.activity_v_card;
    }

    @Override
    public void initVariable() {

    }

    @Override
    public BasePresenter initPresenter() {
        return null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.share:
                Bitmap viewBitmap = createViewBitmap(image_re);
                UmengShareUtils.openShareForImage(this, viewBitmap, SHARE_MEDIA.WEIXIN, new UMShareListener() {
                    @Override
                    public void onStart(SHARE_MEDIA share_media) {

                    }

                    @Override
                    public void onResult(SHARE_MEDIA share_media) {
                        ToastUtil.showToast("分享成功");
                    }

                    @Override
                    public void onError(SHARE_MEDIA share_media, Throwable throwable) {
                        ToastUtil.showToast("分享失败");
                    }

                    @Override
                    public void onCancel(SHARE_MEDIA share_media) {
                        ToastUtil.showToast("取消分享");
                    }
                });
                break;
            case R.id.save_re:
                ImageUtil.saveImage(this,image_re);

                break;
        }
    }



}