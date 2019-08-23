package com.example.dgfab.AllParsings;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Friendsdata {

    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("ustatus")
    @Expose
    private String ustatus;
    @SerializedName("senderid")
    @Expose
    private String senderid;
    @SerializedName("receiverid")
    @Expose
    private String receiverid;


    @SerializedName("mobile")
    @Expose
    private String mobile;

    public Friendsdata(String userId, String name, String ustatus, String senderid, String receiverid, String mobile) {
        this.userId = userId;
        this.name = name;
        this.ustatus = ustatus;
        this.senderid = senderid;
        this.receiverid = receiverid;
        this.mobile = mobile;
    }


    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUstatus() {
        return ustatus;
    }

    public void setUstatus(String ustatus) {
        this.ustatus = ustatus;
    }

    public String getSenderid() {
        return senderid;
    }

    public void setSenderid(String senderid) {
        this.senderid = senderid;
    }

    public String getReceiverid() {
        return receiverid;
    }

    public void setReceiverid(String receiverid) {
        this.receiverid = receiverid;
    }


}
