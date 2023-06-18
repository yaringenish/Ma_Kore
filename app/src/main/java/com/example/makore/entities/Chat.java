package com.example.makore.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Chat {
    @PrimaryKey(autoGenerate = true)
    private String id;
    private User[] users;

    public String getId() {
        return id;
    }

    public User[] getUsers() {
        return users;
    }

    public Message[] getMessages() {
        return messages;
    }

    private Message[] messages;

    public Chat(User[] users, Message[] messages) {
        this.users = users;
        this.messages = messages;
    }
}
