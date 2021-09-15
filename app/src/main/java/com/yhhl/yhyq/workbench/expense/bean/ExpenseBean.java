package com.yhhl.yhyq.workbench.expense.bean;

import com.google.gson.Gson;
import com.yhhl.yhyq.base.BaseEntity;

public class ExpenseBean extends BaseEntity {

    /**
     * id : 4
     * company_id : 10000
     * company_name : 北京云和互联科技有限公司
     * dept_name : null
     * bill_value : 1000
     * remark : null
     * creator : 10008
     * create_time : 2021-08-02 15:05:52
     * del_flag : 0
     * bill_user_name : null
     */

    private int id;
    private int company_id;
    private String company_name;
    private String dept_name;
    private int bill_value;
    private String remark;
    private int creator;
    private String create_time;
    private int del_flag;
    private String bill_user_name;

    public static ExpenseBean StringFromData(String str) {

        return new Gson().fromJson(str, ExpenseBean.class);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCompany_id() {
        return company_id;
    }

    public void setCompany_id(int company_id) {
        this.company_id = company_id;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getDept_name() {
        return dept_name;
    }

    public void setDept_name(String dept_name) {
        this.dept_name = dept_name;
    }

    public int getBill_value() {
        return bill_value;
    }

    public void setBill_value(int bill_value) {
        this.bill_value = bill_value;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getCreator() {
        return creator;
    }

    public void setCreator(int creator) {
        this.creator = creator;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public int getDel_flag() {
        return del_flag;
    }

    public void setDel_flag(int del_flag) {
        this.del_flag = del_flag;
    }

    public String getBill_user_name() {
        return bill_user_name;
    }

    public void setBill_user_name(String bill_user_name) {
        this.bill_user_name = bill_user_name;
    }
}
