package com.example.adm.Fragments.Orders;

public class SelectedSessionList  {
    private  String session_title,time,bolen,count;
    public SelectedSessionList() {

    }

    public void setSession_title(String session_title) {
        this.session_title = session_title;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setBolen(String bolen) {
        this.bolen = bolen;
    }

    public String getBolen() {
        return bolen;
    }

    public String getSession_title() {
        return session_title;
    }

    public String getTime() {
        return time;
    }

    public String getCount() {
        return count;
    }
}