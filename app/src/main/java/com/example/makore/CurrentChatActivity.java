package com.example.makore;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

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
import com.example.makore.entities.User;
import com.example.makore.viewmodels.ChatItemViewModel;

import java.util.List;


public class CurrentChatActivity extends AppCompatActivity {
    private ActivityCurrentChatBinding binding;

    private ChatItemViewModel viewModel;
    private String token;
    private String chatId;
    private String contactUsername;
    private String contactPicture;
    private String contactDisplay;
    private String username;
    private TextView url;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCurrentChatBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        token = getIntent().getStringExtra("token");
        chatId = getIntent().getStringExtra("chatId");
        username = getIntent().getStringExtra("username");
        contactDisplay = getIntent().getStringExtra("otherUser");
        contactPicture = getIntent().getStringExtra("picture");
        contactUsername = getIntent().getStringExtra("otherUsername");
        url = SharedViewSingleton.getInstance().getSharedTextView();
        User contact = new User(contactUsername,contactDisplay,contactPicture);
        viewModel = new ViewModelProvider(this, new ViewModelProvider.Factory() {
            @NonNull
            @Override
            public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
                if (modelClass.isAssignableFrom(ChatItemViewModel.class)) {

                    return (T) new ChatItemViewModel(token,chatId,CurrentChatActivity.this,username,contact,url.getText().toString());
                }
                throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
            }
        }).get(ChatItemViewModel.class);

        RecyclerView messageListItems = findViewById(R.id.lstMessages);
        final MessageListAdapter adapter = new MessageListAdapter(this,username );

        TextView tvDisplayName = binding.userDisplayName;
        tvDisplayName.setText(getIntent().getStringExtra("otherUser"));
        messageListItems.setAdapter(adapter);
        messageListItems.setLayoutManager(new LinearLayoutManager(this));



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
        binding.sendBar.setText("");
        });
    }

    protected void onResume() {
        super.onResume();
        viewModel.setChatApi(url.getText().toString());
    }
}
