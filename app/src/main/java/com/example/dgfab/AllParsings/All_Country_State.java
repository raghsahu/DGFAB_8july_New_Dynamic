package com.example.dgfab.AllParsings;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class All_Country_State {

    @SerializedName("data")
    @Expose
    private List<Datum_Country> data = null;
    @SerializedName("responce")
    @Expose
    private Boolean responce;

    public List<Datum_Country> getData() {
        return data;
    }

    public void setData(List<Datum_Country> data) {
        this.data = data;
    }

    public Boolean getResponce() {
        return responce;
    }

    public void setResponce(Boolean responce) {
        this.responce = responce;
    }
}
