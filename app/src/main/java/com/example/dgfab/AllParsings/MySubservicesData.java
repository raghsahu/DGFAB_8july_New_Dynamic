package com.example.dgfab.AllParsings;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MySubservicesData {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("subservice_id")
    @Expose
    private String subserviceId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("status")
    @Expose
    private String status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSubserviceId() {
        return subserviceId;
    }

    public void setSubserviceId(String subserviceId) {
        this.subserviceId = subserviceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
