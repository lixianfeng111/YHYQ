package com.yhhl.yhyq.net;

/**
 * function ：接口
 */
public interface HttpUrlUtils {
    String INNER_BASE_URL = "https://172.17.8.100/techApi/";
    String BASE_URL = "http://test.yunheyiqi.com:8000/";
    /**
     * 用户相关接口
     */
    //1.登陆
    String LOGIN = "oauth/token";
    String MESSAGES = "messages";
    String EXPENSE = "bill-infos";
    String INVOICE = "invoices";
//    String INVOICE_DETAIL = "invoices";
    String TASK = "task-infos";
    String PROJECT_APPROVAL="topic-infos";
    String COMPANYAndGROUPS="company-groups";
    String ADDRESS_LIST="tel-list";

}