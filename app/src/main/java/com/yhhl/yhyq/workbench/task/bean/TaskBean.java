package com.yhhl.yhyq.workbench.task.bean;

import com.google.gson.Gson;
import com.yhhl.yhyq.base.BaseEntity;

public class TaskBean extends BaseEntity {

    /**
     * id : 2021071215182471966
     * task_code : 100012
     * content : fr
     * schedule : 10
     * member_id : 10252
     * member_name : 张石山
     * member_type : 0
     * project_id : 2021070622460522667
     * start_time : 2021-07-08
     * end_time : 2021-07-09
     * start_time_str : null
     * end_time_str : null
     * unit_num : 1
     * feed_back : 带我去各单位浅一点
     * level : 0
     * creator : null
     * project_status : null
     * publish_status : 1
     * file_name : null
     * finish_date : null
     * del_flag : 0
     * condition : null
     * assist_flag : null
     * login_user_id : null
     * publish_date : 2021-07-12
     * id_no : 150102199004071677
     * file_status : 0
     * user_name : 小玉
     */

    private String id;
    private String task_code;
    private String content;
    private int schedule;
    private int member_id;
    private String member_name;
    private String member_type;
    private String project_id;
    private String start_time;
    private String end_time;
    private Object start_time_str;
    private Object end_time_str;
    private int unit_num;
    private String feed_back;
    private String level;
    private Object creator;
    private Object project_status;
    private String publish_status;
    private Object file_name;
    private Object finish_date;
    private int del_flag;
    private Object condition;
    private Object assist_flag;
    private Object login_user_id;
    private String publish_date;
    private String id_no;
    private int file_status;
    private String user_name;

    public static TaskBean objectFromData(String str) {

        return new Gson().fromJson(str, TaskBean.class);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTask_code() {
        return task_code;
    }

    public void setTask_code(String task_code) {
        this.task_code = task_code;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getSchedule() {
        return schedule;
    }

    public void setSchedule(int schedule) {
        this.schedule = schedule;
    }

    public int getMember_id() {
        return member_id;
    }

    public void setMember_id(int member_id) {
        this.member_id = member_id;
    }

    public String getMember_name() {
        return member_name;
    }

    public void setMember_name(String member_name) {
        this.member_name = member_name;
    }

    public String getMember_type() {
        return member_type;
    }

    public void setMember_type(String member_type) {
        this.member_type = member_type;
    }

    public String getProject_id() {
        return project_id;
    }

    public void setProject_id(String project_id) {
        this.project_id = project_id;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public Object getStart_time_str() {
        return start_time_str;
    }

    public void setStart_time_str(Object start_time_str) {
        this.start_time_str = start_time_str;
    }

    public Object getEnd_time_str() {
        return end_time_str;
    }

    public void setEnd_time_str(Object end_time_str) {
        this.end_time_str = end_time_str;
    }

    public int getUnit_num() {
        return unit_num;
    }

    public void setUnit_num(int unit_num) {
        this.unit_num = unit_num;
    }

    public String getFeed_back() {
        return feed_back;
    }

    public void setFeed_back(String feed_back) {
        this.feed_back = feed_back;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Object getCreator() {
        return creator;
    }

    public void setCreator(Object creator) {
        this.creator = creator;
    }

    public Object getProject_status() {
        return project_status;
    }

    public void setProject_status(Object project_status) {
        this.project_status = project_status;
    }

    public String getPublish_status() {
        return publish_status;
    }

    public void setPublish_status(String publish_status) {
        this.publish_status = publish_status;
    }

    public Object getFile_name() {
        return file_name;
    }

    public void setFile_name(Object file_name) {
        this.file_name = file_name;
    }

    public Object getFinish_date() {
        return finish_date;
    }

    public void setFinish_date(Object finish_date) {
        this.finish_date = finish_date;
    }

    public int getDel_flag() {
        return del_flag;
    }

    public void setDel_flag(int del_flag) {
        this.del_flag = del_flag;
    }

    public Object getCondition() {
        return condition;
    }

    public void setCondition(Object condition) {
        this.condition = condition;
    }

    public Object getAssist_flag() {
        return assist_flag;
    }

    public void setAssist_flag(Object assist_flag) {
        this.assist_flag = assist_flag;
    }

    public Object getLogin_user_id() {
        return login_user_id;
    }

    public void setLogin_user_id(Object login_user_id) {
        this.login_user_id = login_user_id;
    }

    public String getPublish_date() {
        return publish_date;
    }

    public void setPublish_date(String publish_date) {
        this.publish_date = publish_date;
    }

    public String getId_no() {
        return id_no;
    }

    public void setId_no(String id_no) {
        this.id_no = id_no;
    }

    public int getFile_status() {
        return file_status;
    }

    public void setFile_status(int file_status) {
        this.file_status = file_status;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }
}
