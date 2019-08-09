package com.example.dgfab.AllParsings;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetProducts {


    @SerializedName("data")
    @Expose
    private List<GetProductsData> data ;
    @SerializedName("responce")
    @Expose
    private Boolean responce;

    public List<GetProductsData> getData() {
        return data;
    }

    public void setData(List<GetProductsData> data) {
        this.data = data;
    }

    public Boolean getResponce() {
        return responce;
    }

    public void setResponce(Boolean responce) {
        this.responce = responce;
    }
}
