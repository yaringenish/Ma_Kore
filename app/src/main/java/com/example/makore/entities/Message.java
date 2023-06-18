package com.example.makore.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity
public class Message {
    @PrimaryKey(autoGenerate = true)
    private String id;
    private Date created;
    private User sender;
    private String content;


    public Message(User sender, String content) {
        this.created = new Date();
        this.sender = sender;
        this.content = content;
    }
}
