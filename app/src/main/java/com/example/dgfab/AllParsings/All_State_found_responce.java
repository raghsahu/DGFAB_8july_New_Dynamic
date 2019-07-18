package com.example.dgfab.AllParsings;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class All_State_found_responce {
    @SerializedName("data")
    @Expose
    private List<All_State_found> data = null;
    @SerializedName("responce")
    @Expose
    private Boolean responce;

    public List<All_State_found> getData() {
        return data;
    }

    public void setData(List<All_State_found> data) {
        this.data = data;
    }

    public Boolean getResponce() {
        return responce;
    }

    public void setResponce(Boolean responce) {
        this.responce = responce;
    }
}
