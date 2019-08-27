package com.example.dgfab.AllParsings;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetRemainder {
    @SerializedName("data")
    @Expose
    private List<GetRemainderData> data;
    @SerializedName("responce")
    @Expose
    private Boolean responce;

    public List<GetRemainderData> getData() {
        return data;
    }

    public void setData(List<GetRemainderData> data) {
        this.data = data;
    }

    public Boolean getResponce() {
        return responce;
    }

    public void setResponce(Boolean responce) {
        this.responce = responce;
    }
}
