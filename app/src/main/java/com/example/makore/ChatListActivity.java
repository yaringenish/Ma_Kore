package com.example.makore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.makore.adapters.ChatListAdapter;
import com.example.makore.entities.ChatListItem;
import com.example.makore.viewmodels.ChatItemViewModel;

import java.util.ArrayList;
import java.util.List;

public class ChatListActivity extends AppCompatActivity {

    private ChatItemViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_list);

        String token = getIntent().getStringExtra("token");
        viewModel = new ViewModelProvider(this, new ViewModelProvider.Factory() {
            @NonNull
            @Override
            public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
                if (modelClass.isAssignableFrom(ChatItemViewModel.class)) {
                    return (T) new ChatItemViewModel(token);
                }
                throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
            }
        }).get(ChatItemViewModel.class);


//        viewModel = new ViewModelProvider(this).get(ChatItemViewModel.class);
        RecyclerView lstChatItems = findViewById(R.id.lstChatItems);
        final ChatListAdapter adapter = new ChatListAdapter(this);

        lstChatItems.setAdapter(adapter);
        lstChatItems.setLayoutManager(new LinearLayoutManager(this));

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, LinearLayoutManager.VERTICAL);
        lstChatItems.addItemDecoration(dividerItemDecoration);

        viewModel.getChatList().observe(this, chatListItems -> {
            adapter.setChatListItems(chatListItems);
        });


    }
}