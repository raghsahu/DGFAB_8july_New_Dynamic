package com.example.dgfab.AllParsings;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MyServices {

    @SerializedName("data")
    @Expose
    private List<MyServicesData> data = null;
    @SerializedName("responce")
    @Expose
    private Boolean responce;

    public List<MyServicesData> getData() {
        return data;
    }

    public void setData(List<MyServicesData> data) {
        this.data = data;
    }

    public Boolean getResponce() {
        return responce;
    }

    public void setResponce(Boolean responce) {
        this.responce = responce;
    }


}
