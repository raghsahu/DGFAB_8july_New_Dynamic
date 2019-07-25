package com.example.dgfab.AllParsings;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MyInfo {
    @SerializedName("data")
    @Expose
    private MyInfoData data;
    @SerializedName("responce")
    @Expose
    private Boolean responce;

    public MyInfoData getData() {
        return data;
    }

    public void setData(MyInfoData data) {
        this.data = data;
    }

    public Boolean getResponce() {
        return responce;
    }

    public void setResponce(Boolean responce) {
        this.responce = responce;
    }
}
