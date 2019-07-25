package com.example.dgfab.AllParsings;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;
public class Get_Cities {

    @SerializedName("data")
    @Expose
    private List<Get_Cities_Data> data;
    @SerializedName("responce")
    @Expose
    private Boolean responce;

    public Get_Cities(String stateId, String name) {
    }

    public List<Get_Cities_Data> getData() {
        return data;
    }

    public void setData(List<Get_Cities_Data> data) {
        this.data = data;
    }

    public Boolean getResponce() {
        return responce;
    }

    public void setResponce(Boolean responce) {
        this.responce = responce;
    }
}
