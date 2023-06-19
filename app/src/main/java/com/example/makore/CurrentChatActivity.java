package com.example.makore;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.makore.adapters.ChatListAdapter;
import com.example.makore.adapters.MessageListAdapter;
import com.example.makore.api.ChatAPI;
import com.example.makore.apiObjects.AddMessageRequestBody;
import com.example.makore.apiObjects.LoginData;
import com.example.makore.callbacks.GetChatCallBack;
import com.example.makore.callbacks.TokenCallback;
import com.example.makore.databinding.ActivityAddContactBinding;
import com.example.makore.databinding.ActivityCurrentChatBinding;
import com.example.makore.entities.Chat;
import com.example.makore.entities.Message;
import com.example.makore.viewmodels.ChatItemViewModel;

import java.util.List;


public class CurrentChatActivity extends AppCompatActivity {
    private ActivityCurrentChatBinding binding;

    private ChatItemViewModel viewModel;
    private String token;
    private String chatId;
    private ChatAPI chatAPI = new ChatAPI();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCurrentChatBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        token = getIntent().getStringExtra("token");
        chatId = getIntent().getStringExtra("chatId");
        viewModel = new ViewModelProvider(this, new ViewModelProvider.Factory() {
            @NonNull
            @Override
            public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
                if (modelClass.isAssignableFrom(ChatItemViewModel.class)) {
                    return (T) new ChatItemViewModel(getIntent().getStringExtra("token"),getIntent().getStringExtra("chatId"));
                }
                throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
            }
        }).get(ChatItemViewModel.class);

        RecyclerView messageListItems = findViewById(R.id.lstMessages);
        final MessageListAdapter adapter = new MessageListAdapter(this);

        messageListItems.setAdapter(adapter);
        messageListItems.setLayoutManager(new LinearLayoutManager(this));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, LinearLayoutManager.VERTICAL);
        messageListItems.addItemDecoration(dividerItemDecoration);


        viewModel.getCurrentChatMessages().observe(this, messages -> {
            adapter.setMessageListItems(messages);

        });
        handleSend();

    }

    private void handleSend() {
        binding.btnSend.setOnClickListener(view -> {
        AddMessageRequestBody requestBody =  new AddMessageRequestBody(binding.sendBar.getText().toString());
        if(requestBody.getMsg() != ""){
            viewModel.addMessage(requestBody);
        }
        });
    }
}
