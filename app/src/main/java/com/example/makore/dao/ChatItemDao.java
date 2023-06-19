package com.example.makore.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.makore.entities.Chat;
import com.example.makore.entities.ChatListItem;

import java.util.List;
@Dao
public interface ChatItemDao {

        @Query("SELECT * FROM chatlistitem")
        List<ChatListItem> index();

        @Query("SELECT * FROM chatlistitem WHERE id = :id")
        ChatListItem get(int id);
        @Insert
        void insert(ChatListItem... chatitems);
        @Update
        void update(ChatListItem... chatitems);
        @Delete
        void delete(ChatListItem... chatitems);

}
