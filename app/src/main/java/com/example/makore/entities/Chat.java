package com.example.makore.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Chat {
    @PrimaryKey(autoGenerate = true)
    private String id;
    private User[] users;
    private Message[] messages;

    public Chat(User[] users, Message[] messages) {
        this.users = users;
        this.messages = messages;
    }
}
