package com.yhhl.yhyq.workbench.projectapproval.bean;

import com.google.gson.Gson;
import com.yhhl.yhyq.base.BaseEntity;

public class ProjectApprovalBean extends BaseEntity {

    /**
     * id : 1
     * customer_info : 服务很物权法华
     * topic_content : 发定位覅uewhufhwe吃的
     * file_url : dgsdywdyw
     * creator : 10429
     * create_time : 2021-07-27 06:47:10
     * del_flag : 0
     */

    private int id;
    private String customer_info;
    private String topic_content;
    private String file_url;
    private int creator;
    private String create_time;
    private int del_flag;

    public static ProjectApprovalBean objectFromData(String str) {

        return new Gson().fromJson(str, ProjectApprovalBean.class);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomer_info() {
        return customer_info;
    }

    public void setCustomer_info(String customer_info) {
        this.customer_info = customer_info;
    }

    public String getTopic_content() {
        return topic_content;
    }

    public void setTopic_content(String topic_content) {
        this.topic_content = topic_content;
    }

    public String getFile_url() {
        return file_url;
    }

    public void setFile_url(String file_url) {
        this.file_url = file_url;
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
}
