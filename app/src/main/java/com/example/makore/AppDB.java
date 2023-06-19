package com.example.makore;
import androidx.room.Database;
import androidx.room.RoomDatabase;


import com.example.makore.dao.ChatDao;
import com.example.makore.dao.ChatItemDao;
import com.example.makore.entities.Chat;
import com.example.makore.entities.ChatListItem;
import com.example.makore.entities.Message;
import com.example.makore.entities.User;

@Database(entities = {ChatListItem.class,Chat.class, User.class, Message.class} , version = 1)
public abstract class AppDB extends RoomDatabase {
    public abstract ChatItemDao chatItemDao();

    public abstract ChatDao chatDao();
}
