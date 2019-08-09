package com.example.dgfab.AllParsings;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MySubservices {

    @SerializedName("data")
    @Expose
    private List<MySubservicesData> data;
    @SerializedName("responce")
    @Expose
    private Boolean responce;

    public List<MySubservicesData> getData() {
        return data;
    }

    public void setData(List<MySubservicesData> data) {
        this.data = data;
    }

    public Boolean getResponce() {
        return responce;
    }

    public void setResponce(Boolean responce) {
        this.responce = responce;
    }
}
