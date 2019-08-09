package com.example.dgfab.AllParsings;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddSubService {

    @SerializedName("responce")
    @Expose
    private Boolean responce;
    @SerializedName("massage")
    @Expose
    private AddSubMessage massage;

    public Boolean getResponce() {
        return responce;
    }

    public void setResponce(Boolean responce) {
        this.responce = responce;
    }

    public AddSubMessage getMassage() {
        return massage;
    }

    public void setMassage(AddSubMessage massage) {
        this.massage = massage;
    }

}
