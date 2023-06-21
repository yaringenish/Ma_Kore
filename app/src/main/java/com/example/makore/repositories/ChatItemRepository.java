package com.example.makore.repositories;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.makore.R;
import com.example.makore.api.ChatAPI;
import com.example.makore.apiObjects.AddMessageRequestBody;
import com.example.makore.callbacks.GetChatCallBack;
import com.example.makore.entities.Chat;
import com.example.makore.entities.ChatListItem;
import com.example.makore.entities.Message;

import java.util.LinkedList;
import java.util.List;

public class ChatItemRepository {
//private ChatItemDao dao;
private ChatListData ChatListData;

private ChatListMessages chatListMessages;
//private ChatAPI api;
    private String token;
    private String chatId;

    private ChatAPI chatAPI = new ChatAPI();

 public ChatItemRepository(String token) {
//    LocalDatabase db = LocalDatabase.getInstance();
//    dao = db.ChatDao(); // getting data from local storage
    ChatListData = new ChatListData();
    this.token = token;
//    api = new ChatAPI(ChatListData, dao); // getting data from server
    }

    public ChatItemRepository(String token,String chatId) {
//    LocalDatabase db = LocalDatabase.getInstance();
//    dao = db.ChatDao(); // getting data from local storage
        this.chatId = chatId;
        this.token = token;
        chatListMessages = new ChatListMessages();

//    api = new ChatAPI(ChatListData, dao); // getting data from server
    }

    class ChatListMessages extends MutableLiveData<List<Message>>{
        public ChatListMessages(){
            super();
            new Thread(() ->{
                ChatAPI chatAPI = new ChatAPI();
                chatAPI.getChat(chatListMessages, token,chatId);
            }).start();
        }
        @Override
        protected void onActive() {
            super.onActive();
            new Thread(() ->{
                ChatAPI chatAPI = new ChatAPI();
                chatAPI.getChat(chatListMessages, token,chatId);
            }).start();
//                 new Thread(()->{ChatListData.postValue(dao.get());}).start();
        }

    }


    class ChatListData extends MutableLiveData<List<ChatListItem>> {
    public ChatListData() {
        super();
        List<ChatListItem> s = new LinkedList<>();
        setValue(new LinkedList<>());
//        ChatAPI chatAPI = new ChatAPI();
        //and doing everything that needs to get all the chatListItems.
    }

             @Override
             protected void onActive() {
                 super.onActive();
                 new Thread(() ->{
                     ChatAPI chatAPI = new ChatAPI();
                     chatAPI.getChatsbyUsername(ChatListData, token);
                     Log.i("here", "gere");
                 }).start();
//                 new Thread(()->{ChatListData.postValue(dao.get());}).start();
             }
 }
    public LiveData <List<ChatListItem>> getAll() {
        return ChatListData;
    }
    public LiveData <List<Message>> getMessages() {
        return chatListMessages;
    }

    public void addMessage(AddMessageRequestBody requestBody){
        chatAPI.addMessage(chatListMessages, token, chatId , requestBody);
    }
//    public void addChatItem(final ChatListItem chatItem) {
//            api.addChat(chatItem);
//        }
//
//       public void delete(final ChatListItem chatItem) {
//            api.delete(chatItem);
//     }
//
         public void reload(String msgName) {
             // do something
        }

    }

