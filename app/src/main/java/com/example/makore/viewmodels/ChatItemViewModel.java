package com.example.makore.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;


import com.example.makore.entities.ChatListItem;
import com.example.makore.entities.Message;
import com.example.makore.repositories.ChatItemRepository;

import java.util.List;

public class ChatItemViewModel extends ViewModel {
    private ChatItemRepository mRepository;
    private LiveData<List<ChatListItem>> chatList;
    private LiveData<List<Message>> currentChatMessages;
     public ChatItemViewModel(String token){
         mRepository = new ChatItemRepository(token);
         chatList = mRepository.getAll();
     }
    public ChatItemViewModel(String token,String chatId){
        mRepository = new ChatItemRepository(token,chatId);
        chatList = mRepository.getAll();
        currentChatMessages = mRepository.getMessages();
    }


    public LiveData<List<ChatListItem>> getChatList() {
        return chatList;
    }
    public LiveData<List<Message>> getCurrentChatMessages() {
        return currentChatMessages;
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
