package com.example.makore.api;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.makore.apiObjects.AddContactRequestBody;
import com.example.makore.apiObjects.LoginData;
import com.example.makore.apiObjects.RegisterRequestBody;
import com.example.makore.apiObjects.TokenRequestBody;
import com.example.makore.callbacks.GetChatCallBack;
import com.example.makore.callbacks.AddContactCallback;
import com.example.makore.callbacks.RegisterCallBack;
import com.example.makore.callbacks.TokenCallback;
import com.example.makore.entities.Chat;
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
                 .baseUrl("http://10.0.0.25:12345/")
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

    public void createUser(RegisterRequestBody requestBody,  final RegisterCallBack callback){
        Call<Void> call = webServiceAPI.createUser(requestBody);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                callback.onRegisterResponse(response.code());
            }
            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e("api", "Request failed: " + t.getMessage(), t);
            }
        });
    }

    public void addContact(String username,String token,final AddContactCallback callback) {
        AddContactRequestBody requestBody = new AddContactRequestBody(username);
        Call<ResponseBody> call = webServiceAPI.createChat(("Bearer " + token), requestBody);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.code() == 200) {
                    callback.onAddContactResponse(response.code(), "");
                } else {
                    try{
                        ResponseBody r2 = response.errorBody();
                        callback.onAddContactResponse(response.code(), r2.string());
                    } catch (Exception e) {
                        // handle exception
                    }
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("api", "Request failed: " + t.getMessage(), t);
            }
        });
    }

    public void getChat(String token, String chatId , final GetChatCallBack callback){
        Call<Chat> call = webServiceAPI.getChatById(("Bearer " + token),chatId);
        call.enqueue(new Callback<Chat>() {
            @Override
            public void onResponse(Call<Chat> call, Response<Chat> response) {
                    callback.onGetChatResponse(response.code(), response.body());

            }
            @Override
            public void onFailure(Call<Chat> call, Throwable t) {
                Log.e("api", "Request failed: " + t.getMessage(), t);
            }
        });
    }
 }

