package com.example.makore.entities;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class ChatListItem {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String displayName;
    private int picture;
    private String lstMsg;

    public ChatListItem(String displayName, int picture, String lstMsg) {
        this.displayName = displayName;
        this.picture = picture;
        this.lstMsg = lstMsg;
    }

    public ChatListItem(String displayName, int picture) {
        this.displayName = displayName;
        this.picture = picture;
    }


    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public int getPicture() {
        return picture;
    }

    public void setPicture(int picture) {
        this.picture = picture;
    }

    public String getLstMsg() {
        return lstMsg;
    }

    public void setLstMsg(String lstMsg) {
        this.lstMsg = lstMsg;
    }
}
