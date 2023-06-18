package com.example.makore.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;


import com.example.makore.entities.ChatListItem;
import com.example.makore.repositories.ChatItemRepository;

import java.util.List;

public class ChatItemViewModel extends ViewModel {
    private ChatItemRepository mRepository;
    private LiveData<List<ChatListItem>> chatList;

     public ChatItemViewModel(String token){
         mRepository = new ChatItemRepository(token);
         chatList = mRepository.getAll();
     }


    public LiveData<List<ChatListItem>> getChatList() {
        return chatList;
    }
//    public void add(ChatListItem chatItem){
//         mRepository.add(chatItem);
//    }
//    public void delete(ChatListItem chatItem){
//        mRepository.delete(chatItem);
//    }
//    public void reload(){
//        mRepository.reload();
//    }
}
