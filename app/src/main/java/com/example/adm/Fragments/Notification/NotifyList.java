package com.example.adm.Fragments.Notification;

public class NotifyList {
    private String msg,date,username,phone_number,session,function;

    public NotifyList() {

    }

    public void setFunction(String function) {
        this.function = function;
    }

    public String getFunction() {
        return function;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDate() {
        return date;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public String getSession() {
        return session;
    }

    public String getUsername() {
        return username;
    }

    public String getMsg() {
        return msg;
    }
}
