package com.example.dgfab.AllParsings;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Searching_Manufacturers_Data {

    @SerializedName("user_type_name")
    @Expose
    private String userTypeName;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("user_type")
    @Expose
    private String userType;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("mdname")
    @Expose
    private String mdname;
    @SerializedName("lastname")
    @Expose
    private String lastname;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("company_name")
    @Expose
    private String companyName;
    @SerializedName("brand_name")
    @Expose
    private String brandName;
    @SerializedName("team")
    @Expose
    private String team;
    @SerializedName("about")
    @Expose
    private String about;
    @SerializedName("monthly")
    @Expose
    private String monthly;
    @SerializedName("average")
    @Expose
    private String average;
    @SerializedName("purchase")
    @Expose
    private String purchase;
    @SerializedName("primary_address")
    @Expose
    private String primaryAddress;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("pin")
    @Expose
    private String pin;
    @SerializedName("sub_type")
    @Expose
    private String subType;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("cover_image")
    @Expose
    private String coverImage;
    @SerializedName("trandmark_cer")
    @Expose
    private String trandmarkCer;
    @SerializedName("copyright_cer")
    @Expose
    private String copyrightCer;
    @SerializedName("others_cer")
    @Expose
    private String othersCer;
    @SerializedName("gst_cer")
    @Expose
    private String gstCer;
    @SerializedName("degree_cer")
    @Expose
    private String degreeCer;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("reqid")
    @Expose
    private String reqid;
    @SerializedName("senderid")
    @Expose
    private String senderid;
    @SerializedName("receiverid")
    @Expose
    private String receiverid;
    @SerializedName("reqstatus")
    @Expose
    private String reqstatus;

    public Searching_Manufacturers_Data NextaNode;

    public Searching_Manufacturers_Data(String user_id, String name, String email, String mobile, String pending) {
        this.id = user_id;
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.reqstatus = pending;

    }

//    public Searching_Manufacturers_Data(String id, String brandName, String name, String image, String status ,String Cover_Image) {
//        this.id = id;
//        this.brandName = brandName;
//        this.name = name;
//        this.image = image;
//        this.status = status;
//        this.coverImage = Cover_Image;
//    }



    public String getUserTypeName() {
        return userTypeName;
    }

    public void setUserTypeName(String userTypeName) {
        this.userTypeName = userTypeName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMdname() {
        return mdname;
    }

    public void setMdname(String mdname) {
        this.mdname = mdname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getMonthly() {
        return monthly;
    }

    public void setMonthly(String monthly) {
        this.monthly = monthly;
    }

    public String getAverage() {
        return average;
    }

    public void setAverage(String average) {
        this.average = average;
    }

    public String getPurchase() {
        return purchase;
    }

    public void setPurchase(String purchase) {
        this.purchase = purchase;
    }

    public String getPrimaryAddress() {
        return primaryAddress;
    }

    public void setPrimaryAddress(String primaryAddress) {
        this.primaryAddress = primaryAddress;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getSubType() {
        return subType;
    }

    public void setSubType(String subType) {
        this.subType = subType;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    public String getTrandmarkCer() {
        return trandmarkCer;
    }

    public void setTrandmarkCer(String trandmarkCer) {
        this.trandmarkCer = trandmarkCer;
    }

    public String getCopyrightCer() {
        return copyrightCer;
    }

    public void setCopyrightCer(String copyrightCer) {
        this.copyrightCer = copyrightCer;
    }

    public String getOthersCer() {
        return othersCer;
    }

    public void setOthersCer(String othersCer) {
        this.othersCer = othersCer;
    }

    public String getGstCer() {
        return gstCer;
    }

    public void setGstCer(String gstCer) {
        this.gstCer = gstCer;
    }

    public String getDegreeCer() {
        return degreeCer;
    }

    public void setDegreeCer(String degreeCer) {
        this.degreeCer = degreeCer;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReqid() {
        return reqid;
    }

    public void setReqid(String reqid) {
        this.reqid = reqid;
    }

    public String getSenderid() {
        return senderid;
    }

    public void setSenderid(String senderid) {
        this.senderid = senderid;
    }

    public String getReceiverid() {
        return receiverid;
    }

    public void setReceiverid(String receiverid) {
        this.receiverid = receiverid;
    }

    public String getReqstatus() {
        return reqstatus;
    }

    public void setReqstatus(String reqstatus) {
        this.reqstatus = reqstatus;
    }

}
