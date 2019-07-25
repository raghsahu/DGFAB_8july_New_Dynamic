package com.example.dgfab.AllParsings;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DestroyingConnections {
    @SerializedName("data")
    @Expose
    private String data;
    @SerializedName("responce")
    @Expose
    private Boolean responce;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Boolean getResponce() {
        return responce;
    }

    public void setResponce(Boolean responce) {
        this.responce = responce;
    }
}
