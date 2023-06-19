package com.example.makore.repositories;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Room;

import com.example.makore.AppDB;
import com.example.makore.R;
import com.example.makore.api.ChatAPI;
import com.example.makore.apiObjects.AddMessageRequestBody;
import com.example.makore.callbacks.GetChatCallBack;
import com.example.makore.dao.ChatDao;
import com.example.makore.dao.ChatItemDao;
import com.example.makore.entities.Chat;
import com.example.makore.entities.ChatListItem;
import com.example.makore.entities.Message;

import java.util.LinkedList;
import java.util.List;

public class ChatItemRepository {

private ChatListData ChatListData;

private ChatListMessages chatListMessages;
    private AppDB db;
    private String token;

    private String username;
    private String chatId;
    private ChatDao chatDao;
    private ChatItemDao chatItemDao;
    private ChatAPI chatAPI ;

    private Chat currentChat;
    private List<Message> chatMessages;

    private List<ChatListItem> chatItemsList;



 public ChatItemRepository(String token,Context context) {
//    LocalDatabase db = LocalDatabase.getInstance();
     db = Room.databaseBuilder(context,
                     AppDB.class, "FooDB")
             .allowMainThreadQueries().build();
     chatDao =  db.chatDao();
     ChatListData = new ChatListData();
     chatAPI = new ChatAPI();
    this.token = token;

    }

    public ChatItemRepository(String token,String chatId,Context context,String username) {
//    LocalDatabase db = LocalDatabase.getInstance();
        db = Room.databaseBuilder(context,
                        AppDB.class, "FooDB")
                .allowMainThreadQueries().build();
        chatItemDao = db.chatItemDao();
        this.username = username;
        this.chatId = chatId;
        this.token = token;
        chatListMessages = new ChatListMessages();

//    api = new ChatAPI(ChatListData, dao); // getting data from server
    }

    class ChatListMessages extends MutableLiveData<List<Message>>{
        public ChatListMessages(){
            super();
            //from Dao
            currentChat = chatDao.get(chatId);
            chatMessages = new LinkedList<>();
            for(int i = 0 ; i < currentChat.getMessages().length; i++){
                chatMessages.add(currentChat.getMessages()[i]);
            }
            chatListMessages.postValue(chatMessages);
            new Thread(() ->{
                ChatAPI chatAPI = new ChatAPI();
                chatAPI.getChat(chatListMessages, token,chatId);
            }).start();
        }
        @Override
        protected void onActive() {
            super.onActive();
            //from Dao
            currentChat = chatDao.get(chatId);
            List<Message> chatMessages = new LinkedList<>();
            for(int i = 0 ; i < currentChat.getMessages().length; i++){
                chatMessages.add(currentChat.getMessages()[i]);
            }
            chatListMessages.postValue(chatMessages);
            //from server
            new Thread(() ->{
                chatAPI.getChat(chatListMessages, token,chatId);
            }).start();
//                 new Thread(()->{ChatListData.postValue(dao.get());}).start();
        }

    }


    class ChatListData extends MutableLiveData<List<ChatListItem>> {
    public ChatListData() {
        super();
        //from Dao
        chatItemsList = chatItemDao.index();
        ChatListData.postValue(chatItemsList);
        new Thread(() ->{
            chatAPI.getChatsbyUsername(ChatListData, token);
        }).start();
    }

             @Override
             protected void onActive() {
                 super.onActive();
                 new Thread(() ->{
                     chatAPI.getChatsbyUsername(ChatListData, token);
                 }).start();
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
        int newSize = currentChat.getMessages().length + 1;
        Message[] updatedMessages = new Message[newSize];
        System.arraycopy(getMessages(), 0, updatedMessages, 0, currentChat.getMessages().length);
//        updatedMessages[newSize - 1] = new Message();
//        chatAPI.getUser()
        currentChat.setMessages(updatedMessages);
        chatDao.update(currentChat);
    }
//    public void addChatItem(final ChatListItem chatItem) {
//            api.addChat(chatItem);
//        }
//
//       public void delete(final ChatListItem chatItem) {
//            api.delete(chatItem);
//     }
//
//         public void reload() {
//             api.get();
//        }

    }

