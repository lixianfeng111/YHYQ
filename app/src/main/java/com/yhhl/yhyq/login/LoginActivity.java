package com.yhhl.yhyq.login;

import android.app.Dialog;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.yhhl.yhyq.MainActivity;
import com.yhhl.yhyq.R;
import com.yhhl.yhyq.base.BaseActivity;
import com.yhhl.yhyq.base.Constant;
import com.yhhl.yhyq.dialog.DialogUtil;
import com.yhhl.yhyq.login.bean.LoginBean;
import com.yhhl.yhyq.login.presenter.LoginPresenter;
import com.yhhl.yhyq.login.view.LoginView;
import com.yhhl.yhyq.net.LogUtil;
import com.yhhl.yhyq.util.SpzUtils;
import com.yhhl.yhyq.util.ToastUtil;
import com.yhhl.yhyq.util.ToolUtils;

import java.util.List;

import javax.crypto.Cipher;

import androidx.core.content.ContextCompat;
import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginView {

    @BindView(R.id.finger_Login)
    TextView finger_Login;
    private TextView login;
    private EditText user;
    private EditText password;
    private Dialog dialog;
    private String name;
    private String ps;
    private Cipher cipher;
    private FingerprintDialogFragment dialogFragment;

    @Override
    public void initView() {
        name = SpzUtils.getString("login_name");
        ps = SpzUtils.getString("login_ps");
        LogUtil.d(SpzUtils.getString("access_token"));

//        if (!login_name.isEmpty()){
//            startActivity(new Intent(LoginActivity.this, MainActivity.class));
//            finish();
//        }
        user = findViewById(R.id.user);
        password = findViewById(R.id.password);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {

    }

    @Override
    public int initLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initVariable() {

    }

    @Override
    public LoginPresenter initPresenter() {
        return new LoginPresenter(this);
    }

    @Override
    public void showLoading() {
        if (dialog==null){
            dialog = DialogUtil.showLoading(this);
        }else {
            dialog.show();
        }

    }

    @Override
    public void hideLoading() {
        dialog.hide();
    }

    @Override
    public void onDataSuccess(LoginBean data) {
        SpzUtils.putString("login_name",name);
        SpzUtils.putString("login_ps",ps);
        SpzUtils.putBoolean("is_login",true);
        SpzUtils.putInt("user_id",data.getUser_id());
        SpzUtils.putString("user_name",data.getUser_name());
        SpzUtils.putString("access_token",data.getAccess_token());
        SpzUtils.putBoolean("is_login",true);
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
        finish();
    }

    @Override
    public void onDataFailed(String msg) {
        ToastUtil.showToast(msg);
    }

    @Override
    public void onDataList(List<LoginBean> list) {

    }


    @OnClick({R.id.login, R.id.finger_Login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login:
                name = user.getText().toString();
                ps = password.getText().toString();
                if (name.isEmpty()|| ps.isEmpty()){
                    ToastUtil.showToast("用户名或密码为空");
                }else {
                    miBasePresenter.login(name, ps);
                }
                break;
            case R.id.finger_Login:
                if (name.isEmpty()){
                    ToastUtil.showToast("第一次请使用账号密码登录");
                }else {
                    fingerLogin();
                }
                break;
        }
    }

    private void fingerLogin() {
        if (ToolUtils.supportFingerprint(this)) {
            /**
             * 生成一个对称加密的key
             */
            ToolUtils.initKey();

            /**
             * 生成一个Cipher对象
             */
            cipher = ToolUtils.initCipher();
            LogUtil.d(cipher+"");

            showFingerPrintDialog(cipher);
        }
    }

    private void showFingerPrintDialog(Cipher cipher) {
        dialogFragment = new FingerprintDialogFragment();
        dialogFragment.setCipher(cipher);
        dialogFragment.show(getSupportFragmentManager(), "fingerprint");

        dialogFragment.setOnFingerprintSetting(new FingerprintDialogFragment.OnFingerprintSetting() {
            @Override
            public void onFingerprint(boolean isSucceed) {
                if (isSucceed) {
                    miBasePresenter.login(name,ps);
                    ToastUtil.showToast("指纹验证成功！");
                } else {
                    ToastUtil.showToast("指纹设置失败！");
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (dialog!=null){
            dialog.dismiss();
        }
    }
}