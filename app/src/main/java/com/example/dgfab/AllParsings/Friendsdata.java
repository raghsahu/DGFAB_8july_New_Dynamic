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

    public Friendsdata(String userId, String name, String ustatus, String senderid, String receiverid) {
        this.userId = userId;
        this.name = name;
        this.ustatus = ustatus;
        this.senderid = senderid;
        this.receiverid = receiverid;
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
