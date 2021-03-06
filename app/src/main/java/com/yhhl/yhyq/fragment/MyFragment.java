package com.yhhl.yhyq.fragment;

import android.app.Dialog;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.yhhl.yhyq.R;
import com.yhhl.yhyq.UrlActivity;
import com.yhhl.yhyq.base.AppManager;
import com.yhhl.yhyq.base.BaseFragment;
import com.yhhl.yhyq.base.BasePresenter;
import com.yhhl.yhyq.dialog.DialogUtil;
import com.yhhl.yhyq.login.LoginActivity;
import com.yhhl.yhyq.person.AboutUsActivity;
import com.yhhl.yhyq.person.PersonActivity;
import com.yhhl.yhyq.person.VCardActivity;
import com.yhhl.yhyq.util.IntentUtil;
import com.yhhl.yhyq.util.SpzUtils;
import com.yhhl.yhyq.util.ToastUtil;

public class MyFragment extends BaseFragment implements View.OnClickListener {

    private RelativeLayout invoice_re;
    private RelativeLayout set_re;
    private Dialog dialog_invoice;
    private RelativeLayout about_re;
    private RelativeLayout card;
    private ImageView headPic;
    private RelativeLayout quit_re;

    @Override
    public void initView() {
        invoice_re = view.findViewById(R.id.invoice_re);
        set_re = view.findViewById(R.id.set_re);
        about_re = view.findViewById(R.id.about_re);
        card = view.findViewById(R.id.card);
        headPic = view.findViewById(R.id.headPic);
        quit_re = view.findViewById(R.id.quit_re);
    }

    @Override
    public void initListener() {
        invoice_re.setOnClickListener(this);
        set_re.setOnClickListener(this);
        about_re.setOnClickListener(this);
        card.setOnClickListener(this);
        quit_re.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public int initLayoutId() {
        return R.layout.fragment_my;
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
            case R.id.invoice_re:
                if (dialog_invoice==null){
                    dialog_invoice = DialogUtil.invoice(getContext(),"??????????????????????????????????????????????????????"+"\n\n"+"?????????????????????9111111111111111"+"\n\n"+"???????????????????????????????????????????????????????????????"+"\n\n"+
                "?????????9111111111111111"+"\n\n"+"??????????????????????????????????????????16??????1??????1???6???101??????X401???"+"\n\n"+"?????????047289546");
                }else {
                    dialog_invoice.show();
                }
            break;
            case R.id.set_re:
                IntentUtil.getInstance().intent(getContext(), PersonActivity.class,null);
                break;
            case R.id.about_re:
                IntentUtil.getInstance().intent(getContext(), AboutUsActivity.class,null);
                break;
            case R.id.card:
                IntentUtil.getInstance().intent(getContext(), VCardActivity.class,null);
                break;
            case R.id.quit_re:
//                IntentUtil.getInstance().intent(getContext(), UrlActivity.class,null);
                SpzUtils.putBoolean("is_login",false);
                AppManager.LogOut(getActivity(), LoginActivity.class);
                getActivity().finish();
                ToastUtil.showToast("??????????????????");
                break;
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (dialog_invoice!=null){
            dialog_invoice.dismiss();
        }
    }
}