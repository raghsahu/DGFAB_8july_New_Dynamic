package com.example.dgfab.AllParsings;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Friends {

    @SerializedName("data")
    @Expose
    private List<Friendsdata> data;
    @SerializedName("responce")
    @Expose
    private Boolean responce;

    public List<Friendsdata> getData() {
        return data;
    }

    public void setData(List<Friendsdata> data) {
        this.data = data;
    }

    public Boolean getResponce() {
        return responce;
    }

    public void setResponce(Boolean responce) {
        this.responce = responce;
    }
}
