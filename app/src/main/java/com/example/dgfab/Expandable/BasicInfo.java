package com.example.dgfab.Expandable;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

public class BasicInfo {
    //For Basic
    String ProductName, SubType, BrandName, ModelNumber;
    Button AddNewKeyword, AddNewSubservice, AddmoreProinfo, AddMoreDetails;
    RecyclerView ProductKeywords, ProductInformation;
    Spinner Product_Group, ProType, PlaceofOrigin, MoreDetails;


    //++++++++++++++++++++
    //Trade Info
    RadioButton uniforFOB, oneFOB;
    //++++++++++
    Context context;


    public BasicInfo(Context context, String productName, String subType, String brandName, String modelNumber, Button addNewKeyword, Button addNewSubservice, Button addmoreProinfo, Button addMoreDetails, RecyclerView productKeywords, RecyclerView productInformation, Spinner product_Group, Spinner proType, Spinner placeofOrigin, Spinner moreDetails) {
        this.context = context;
        ProductName = productName;
        SubType = subType;
        BrandName = brandName;
        ModelNumber = modelNumber;
        AddNewKeyword = addNewKeyword;
        AddNewSubservice = addNewSubservice;
        AddmoreProinfo = addmoreProinfo;
        AddMoreDetails = addMoreDetails;
        ProductKeywords = productKeywords;
        ProductInformation = productInformation;
        Product_Group = product_Group;
        ProType = proType;
        PlaceofOrigin = placeofOrigin;
        MoreDetails = moreDetails;
        if (context != null) {
            Start(context);
        }
    }

    public BasicInfo(RadioButton uniforFOB, RadioButton oneFOB) {
        this.uniforFOB = uniforFOB;
        this.oneFOB = oneFOB;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getSubType() {
        return SubType;
    }

    public void setSubType(String subType) {
        SubType = subType;
    }

    public String getBrandName() {
        return BrandName;
    }

    public void setBrandName(String brandName) {
        BrandName = brandName;
    }

    public String getStringPRoduct() {
        return ProductName;
    }

    public String getModelNumber() {
        return ModelNumber;
    }

    public void setModelNumber(String modelNumber) {
        ModelNumber = modelNumber;
    }

    public Button getAddNewKeyword() {
        return AddNewKeyword;
    }

    public void setAddNewKeyword(Button addNewKeyword) {
        AddNewKeyword = addNewKeyword;
    }

    public Button getAddNewSubservice() {
        return AddNewSubservice;
    }

    public void setAddNewSubservice(Button addNewSubservice) {
        AddNewSubservice = addNewSubservice;
    }

    public Button getAddmoreProinfo() {
        return AddmoreProinfo;
    }

    public void setAddmoreProinfo(Button addmoreProinfo) {
        AddmoreProinfo = addmoreProinfo;
    }

    public Button getAddMoreDetails() {
        return AddMoreDetails;
    }

    public void setAddMoreDetails(Button addMoreDetails) {
        AddMoreDetails = addMoreDetails;
    }

    public RecyclerView getProductKeywords() {
        return ProductKeywords;
    }

    public void setProductKeywords(RecyclerView productKeywords) {
        ProductKeywords = productKeywords;
    }

    public RecyclerView getProductInformation() {
        return ProductInformation;
    }

    public void setProductInformation(RecyclerView productInformation) {
        ProductInformation = productInformation;
    }

    public Spinner getProduct_Group() {
        return Product_Group;
    }

    public void setProduct_Group(Spinner product_Group) {
        Product_Group = product_Group;
    }

    public Spinner getProType() {
        return ProType;
    }

    public void setProType(Spinner proType) {
        ProType = proType;
    }

    public Spinner getPlaceofOrigin() {
        return PlaceofOrigin;
    }

    public void setPlaceofOrigin(Spinner placeofOrigin) {
        PlaceofOrigin = placeofOrigin;
    }

    public Spinner getMoreDetails() {
        return MoreDetails;
    }

    public void setMoreDetails(Spinner moreDetails) {
        MoreDetails = moreDetails;
    }

    public boolean Start(Context context) {
        if (this.ProductKeywords != null) {
            //Toast.makeText(context, "it is exist", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            // Toast.makeText(context, "it does not exist", Toast.LENGTH_SHORT).show();
            return false;
        }
    }


}
