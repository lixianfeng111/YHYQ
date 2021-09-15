package com.yhhl.yhyq.workbench.invoice.bean;

import com.google.gson.Gson;
import com.yhhl.yhyq.base.BaseEntity;

public class InvoiceDetailBean extends BaseEntity {

    /**
     * id : 29
     * company_name : 发票测试公司
     * taxpayer_number : TEST No 002
     * address : 北京昌平
     * phone : 13212324565
     * open_bank : 北京昌平支行
     * bank_account : 214235415334
     * content : 报销
     * amount : 12231.00
     * tax_rate : 12.0
     * tax_amount : null
     * scan_file : null
     */

    private String id;
    private String company_name;
    private String taxpayer_number;
    private String address;
    private String phone;
    private String open_bank;
    private String bank_account;
    private String content;
    private String amount;
    private String tax_rate;
    private String tax_amount;
    private String scan_file;

    public static InvoiceDetailBean objectFromData(String str) {

        return new Gson().fromJson(str, InvoiceDetailBean.class);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getTaxpayer_number() {
        return taxpayer_number;
    }

    public void setTaxpayer_number(String taxpayer_number) {
        this.taxpayer_number = taxpayer_number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getOpen_bank() {
        return open_bank;
    }

    public void setOpen_bank(String open_bank) {
        this.open_bank = open_bank;
    }

    public String getBank_account() {
        return bank_account;
    }

    public void setBank_account(String bank_account) {
        this.bank_account = bank_account;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getTax_rate() {
        return tax_rate;
    }

    public void setTax_rate(String tax_rate) {
        this.tax_rate = tax_rate;
    }

    public Object getTax_amount() {
        return tax_amount;
    }

    public void setTax_amount(String tax_amount) {
        this.tax_amount = tax_amount;
    }

    public Object getScan_file() {
        return scan_file;
    }

    public void setScan_file(String scan_file) {
        this.scan_file = scan_file;
    }
}
