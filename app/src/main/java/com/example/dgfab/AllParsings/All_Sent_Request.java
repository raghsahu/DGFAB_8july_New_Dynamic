package com.example.dgfab.AllParsings;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class All_Sent_Request {

    @SerializedName("data")
    @Expose
    private List<All_Sent_Request_Data> data = null;
    @SerializedName("responce")
    @Expose
    private Boolean responce;

    public List<All_Sent_Request_Data> getData() {
        return data;
    }

    public void setData(List<All_Sent_Request_Data> data) {
        this.data = data;
    }

    public Boolean getResponce() {
        return responce;
    }

    public void setResponce(Boolean responce) {
        this.responce = responce;
    }

}
