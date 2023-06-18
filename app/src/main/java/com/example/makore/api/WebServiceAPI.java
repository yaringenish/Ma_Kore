package com.example.makore.api;

import com.example.makore.apiObjects.RegisterRequestBody;
import com.example.makore.apiObjects.TokenRequestBody;
import com.example.makore.entities.Chat;
import com.example.makore.entities.ChatListItem;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface WebServiceAPI {
 @GET("Chats")
 Call<List<ChatListItem>> getChatsbyUsername(@Header("Authorization") String bearerToken);

 @POST("Chats")
 Call<Void> createChat(@Body ChatListItem chat);

 @GET("Chats/{id}")
 Call<Chat> getChatById(@Header("Authorization") String bearerToken, @Path("id") String id);

 @DELETE("Chats/{id}")
 Call<Void> deleteChat(@Path("id") int id);

 @POST("Chats/{id}/Messages")
 Call<Void> addMessage(@Body ChatListItem chat);

 @GET("Chats/{id}/Messages")
 Call<Void> getMessages(@Path("id") int id);

 @POST("Users")
 Call<Void> createUser (@Body RegisterRequestBody requestBody);

 @GET("Users/{username}")
 Call<Void> getUser(@Path("id") int id);

 //checking if username and password are valid and then create token.
 @POST("Tokens")
 Call<ResponseBody> token (@Body TokenRequestBody requestBody);




}