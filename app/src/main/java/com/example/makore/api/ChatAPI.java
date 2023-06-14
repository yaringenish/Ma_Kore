package com.example.makore.api;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.makore.MyApplication;
import com.example.makore.R;
import com.example.makore.apiObjects.LoginData;
import com.example.makore.apiObjects.TokenRequestBody;
import com.example.makore.entities.ChatListItem;

import java.util.List;

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

         retrofit = new Retrofit.Builder()
                 .baseUrl("http://192.168.52.231:12345/")
                 .addConverterFactory(GsonConverterFactory.create())
                 .build();
         webServiceAPI = retrofit.create(WebServiceAPI.class);
     }
    public void getToken(String username , String password) {
         TokenRequestBody requestBody = new TokenRequestBody(username, password);
         Call<ResponseBody> call = webServiceAPI.token(requestBody);
         call.enqueue(new Callback<ResponseBody>() {
             @Override
             public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                 LoginData loginData = new LoginData();
                 if(response.code() == 200){
                     ResponseBody responseBody = response.body();
                 }
                 else{

                 }
             }

             @Override
             public void onFailure(Call<ResponseBody> call, Throwable t) {
                 Log.e("api", "Request failed: " + t.getMessage(), t);
             }
         });

    }

 }

