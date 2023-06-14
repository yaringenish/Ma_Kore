package com.example.makore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
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
        viewModel = new ViewModelProvider(this).get(ChatItemViewModel.class);
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