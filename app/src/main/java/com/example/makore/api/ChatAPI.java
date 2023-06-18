package com.example.makore.api;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.makore.apiObjects.AddContactRequestBody;
import com.example.makore.apiObjects.LoginData;
import com.example.makore.apiObjects.TokenRequestBody;
import com.example.makore.callbacks.AddContactCallback;
import com.example.makore.callbacks.TokenCallback;
import com.example.makore.entities.ChatListItem;

import java.util.List;
import java.util.concurrent.Executors;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ChatAPI {

// private MutableLiveData<List<ChatListItem>> ChatListData;
// private PostDao dao;
 Retrofit retrofit;
 WebServiceAPI webServiceAPI;

     public ChatAPI() {
    //            this.ChatListData = ChatListData;
    //            this.dao = dao;
//         192.168.52.231
         retrofit = new Retrofit.Builder()
                 .baseUrl("http://192.168.68.104:12345/")
                 .callbackExecutor(Executors.newSingleThreadExecutor())
                 .addConverterFactory(GsonConverterFactory.create())
                 .build();
         webServiceAPI = retrofit.create(WebServiceAPI.class);
     }
    public void getToken(String username , String password,final TokenCallback callback) {
         TokenRequestBody requestBody = new TokenRequestBody(username, password);
         Call<ResponseBody> call = webServiceAPI.token(requestBody);
         call.enqueue(new Callback<ResponseBody>() {
             @Override
             public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                 if(response.code() == 200){
                     ResponseBody responseBody = response.body();
                     try{
                         LoginData loginData = new LoginData();
                         loginData.setToken(responseBody.string());
                         loginData.setUsername(username);
                         callback.onTokenReceived(loginData);
                     }catch (Exception e) {
                         //handle exception
                     }
                 }
                 else{
                     LoginData loginData = new LoginData();
                     callback.onTokenReceived(loginData);
                 }
             }
             @Override
             public void onFailure(Call<ResponseBody> call, Throwable t) {
                 Log.e("api", "Request failed: " + t.getMessage(), t);
             }
         });
    }

    public void getChatsbyUsername(MutableLiveData<List<ChatListItem>> chatItems,String token) {
        Call<List<ChatListItem>> call = webServiceAPI.getChatsbyUsername(("Bearer " + token));
        call.enqueue(new Callback<List<ChatListItem>>() {
            @Override
            public void onResponse(Call<List<ChatListItem>> call, Response<List<ChatListItem>> response) {
                chatItems.postValue(response.body());
//                if(response.code() == 200){
//                    chatItems.postValue(response.body());
//                    System.out.println("SDfsdf");
//                }
            }
            @Override
            public void onFailure(Call<List<ChatListItem>> call, Throwable t) {
                Log.e("api", "Request failed: " + t.getMessage(), t);
            }
        });
    }

//    public void addContact(String username,String token, ,final AddContactCallback callback) {
//        AddContactRequestBody requestBody = new AddContactRequestBody(username);
//        Call<void> call = webServiceAPI.createChat(("Bearer " + token), requestBody);
//        call.enqueue(new Callback<void>() {
//            @Override
//            public void onResponse(Call<List<ChatListItem>> call, Response<List<ChatListItem>> response) {
//                int c = response.code();
//                List<ChatListItem> r = response.body();
//                chatItems.postValue(response.body());
//                System.out.println("SDfsdf");
////                if(response.code() == 200){
////                    chatItems.postValue(response.body());
////                    System.out.println("SDfsdf");
////                }
//            }
//            @Override
//            public void onFailure(Call<List<ChatListItem>> call, Throwable t) {
//                Log.e("api", "Request failed: " + t.getMessage(), t);
//            }
//        });
//    }

 }

