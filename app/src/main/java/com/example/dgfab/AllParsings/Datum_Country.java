package com.example.dgfab.AllParsings;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum_Country {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("sortname")
    @Expose
    private String sortname;
    @SerializedName("name")
    @Expose
    private String name;

    public Datum_Country(String country_id, String country_name, String fullname) {
        this.id =country_id;
        this.sortname = country_name;
        this.name = fullname;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSortname() {
        return sortname;
    }

    public void setSortname(String sortname) {
        this.sortname = sortname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
