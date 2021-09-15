package com.yhhl.yhyq.workbench.addresslist.bean;

import com.yhhl.yhyq.workbench.addresslist.IndexBar.bean.BaseIndexPinyinBean;


public class AddressListBean extends BaseIndexPinyinBean {
    private String name;
    private String tel;
    private String code;
    private String company_id;
    private String dept_id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCompany_id() {
        return company_id;
    }

    public void setCompany_id(String company_id) {
        this.company_id = company_id;
    }

    public String getDept_id() {
        return dept_id;
    }

    public void setDept_id(String dept_id) {
        this.dept_id = dept_id;
    }

    @Override
    public String getTarget() {
        return name;
    }
}