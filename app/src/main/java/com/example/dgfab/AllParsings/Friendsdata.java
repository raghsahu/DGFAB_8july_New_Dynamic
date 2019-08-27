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

    private String rem_type;
    private String remainder_date;
    private String remainder_time;
    private String remainder_cause;

    public Friendsdata(String userId, String name, String ustatus, String senderid, String receiverid, String mobile, String Rem_type, String Remainder_date, String Remainder_time, String remainder_cause) {
        this.userId = userId;
        this.name = name;
        this.ustatus = ustatus;
        this.senderid = senderid;
        this.receiverid = receiverid;
        this.mobile = mobile;
        this.rem_type = Rem_type;
        this.remainder_date = Remainder_date;
        this.remainder_time = Remainder_time;
        this.remainder_cause = remainder_cause;
    }

    public String getRem_type() {
        return rem_type;
    }

    public void setRem_type(String rem_type) {
        this.rem_type = rem_type;
    }

    public String getRemainder_date() {
        return remainder_date;
    }

    public void setRemainder_date(String remainder_date) {
        this.remainder_date = remainder_date;
    }

    public String getRemainder_time() {
        return remainder_time;
    }

    public void setRemainder_time(String remainder_time) {
        this.remainder_time = remainder_time;
    }

    public String getRemainder_cause() {
        return remainder_cause;
    }

    public void setRemainder_cause(String remainder_cause) {
        this.remainder_cause = remainder_cause;
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
