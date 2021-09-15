package com.yhhl.yhyq.home.bean;

import com.google.gson.Gson;
import com.yhhl.yhyq.base.BaseEntity;

import java.io.Serializable;
import java.util.List;

public class BackLogBean extends BaseEntity {


    /**
     * id : 127
     * subject : 王方剑申请加入您的公司
     * content : 王方剑申请加入您的公司
     * category : 1
     * priority : 3
     * link : {"url":"approveEntryApplication?id=48","open_type":"Window"}
     * sender : {"account":"system","name":"系统"}
     * receivers : []
     * attachments : []
     * mark : {"is_mark":0,"comment":""}
     * is_read : 0
     * create_time : 2021-03-25 11:49:17
     */

    private int id;
    private String subject;
    private String content;
    private int category;
    private int priority;
    private LinkBean link;
    private SenderBean sender;
    private MarkBean mark;
    private int is_read;
    private String create_time;

    public static BackLogBean objectFromData(String str) {

        return new Gson().fromJson(str, BackLogBean.class);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public LinkBean getLink() {
        return link;
    }

    public void setLink(LinkBean link) {
        this.link = link;
    }

    public SenderBean getSender() {
        return sender;
    }

    public void setSender(SenderBean sender) {
        this.sender = sender;
    }

    public MarkBean getMark() {
        return mark;
    }

    public void setMark(MarkBean mark) {
        this.mark = mark;
    }

    public int getIs_read() {
        return is_read;
    }

    public void setIs_read(int is_read) {
        this.is_read = is_read;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }


    public static class LinkBean implements Serializable {
        /**
         * url : approveEntryApplication?id=48
         * open_type : Window
         */

        private String url;
        private String open_type;

        public static LinkBean objectFromData(String str) {

            return new Gson().fromJson(str, LinkBean.class);
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getOpen_type() {
            return open_type;
        }

        public void setOpen_type(String open_type) {
            this.open_type = open_type;
        }
    }

    public static class SenderBean implements Serializable {
        /**
         * account : system
         * name : 系统
         */

        private String account;
        private String name;

        public static SenderBean objectFromData(String str) {

            return new Gson().fromJson(str, SenderBean.class);
        }

        public String getAccount() {
            return account;
        }

        public void setAccount(String account) {
            this.account = account;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class MarkBean implements Serializable {
        /**
         * is_mark : 0
         * comment :
         */

        private int is_mark;
        private String comment;

        public static MarkBean objectFromData(String str) {

            return new Gson().fromJson(str, MarkBean.class);
        }

        public int getIs_mark() {
            return is_mark;
        }

        public void setIs_mark(int is_mark) {
            this.is_mark = is_mark;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }
    }
}
