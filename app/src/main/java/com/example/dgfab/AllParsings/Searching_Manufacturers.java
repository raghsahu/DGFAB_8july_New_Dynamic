package com.example.dgfab.AllParsings;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Searching_Manufacturers {

    @SerializedName("responce")
    @Expose
    private Boolean responce;
    @SerializedName("data")
    @Expose
    private List<Searching_Manufacturers_Data> data;

    public Boolean getResponce() {
        return responce;
    }

    public void setResponce(Boolean responce) {
        this.responce = responce;
    }

    public List<Searching_Manufacturers_Data> getData() {
        return data;
    }

    public void setData(List<Searching_Manufacturers_Data> data) {
        this.data = data;
    }


}
