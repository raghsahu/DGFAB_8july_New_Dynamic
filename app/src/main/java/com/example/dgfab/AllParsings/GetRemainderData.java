package com.example.dgfab.AllParsings;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetRemainderData {
    @SerializedName("remainder_id")
    @Expose
    private String remainderId;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("friend_id")
    @Expose
    private String friendId;
    @SerializedName("remainder_date")
    @Expose
    private String remainderDate;
    @SerializedName("remainder_time")
    @Expose
    private String remainderTime;
    @SerializedName("remainder_cause")
    @Expose
    private String remainderCause;
    @SerializedName("rem_type")
    @Expose
    private String remType;
    @SerializedName("friend_name")
    @Expose
    private String friendName;
    @SerializedName("status")
    @Expose
    private String status;

    public String getRemainderId() {
        return remainderId;
    }

    public void setRemainderId(String remainderId) {
        this.remainderId = remainderId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFriendId() {
        return friendId;
    }

    public void setFriendId(String friendId) {
        this.friendId = friendId;
    }

    public String getRemainderDate() {
        return remainderDate;
    }

    public void setRemainderDate(String remainderDate) {
        this.remainderDate = remainderDate;
    }

    public String getRemainderTime() {
        return remainderTime;
    }

    public void setRemainderTime(String remainderTime) {
        this.remainderTime = remainderTime;
    }

    public String getRemainderCause() {
        return remainderCause;
    }

    public void setRemainderCause(String remainderCause) {
        this.remainderCause = remainderCause;
    }

    public String getRemType() {
        return remType;
    }

    public void setRemType(String remType) {
        this.remType = remType;
    }

    public String getFriendName() {
        return friendName;
    }

    public void setFriendName(String friendName) {
        this.friendName = friendName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
