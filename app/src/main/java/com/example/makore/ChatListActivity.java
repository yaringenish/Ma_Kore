package com.example.makore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.example.makore.adapters.ChatListAdapter;
import com.example.makore.api.ChatAPI;
import com.example.makore.callbacks.GetChatCallBack;
import com.example.makore.entities.Chat;
import com.example.makore.entities.ChatListItem;
import com.example.makore.viewmodels.ChatItemViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ChatListActivity extends AppCompatActivity implements ChatListAdapter.OnItemClickListener{

    private ChatItemViewModel viewModel;
    private String token;
    public void onItemClick(ChatListItem chatListItem) {
        Intent intent = new Intent(this, CurrentChatActivity.class);
        intent.putExtra("token", token);
        intent.putExtra("chatId",chatListItem.getId());
        startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_list);
        token = getIntent().getStringExtra("token");
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
        adapter.setOnItemClickListener(this);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, LinearLayoutManager.VERTICAL);
        lstChatItems.addItemDecoration(dividerItemDecoration);
        viewModel.getChatList().observe(this, chatListItems -> {
            adapter.setChatListItems(chatListItems);
        });

        handleAddContact();
    }


    private void handleAddContact(){
        FloatingActionButton btn = findViewById(R.id.btnAddContact);
        btn.setOnClickListener(view -> {
            Intent intent = new Intent(this, AddContactActivity.class);
            intent.putExtra("token", getIntent().getStringExtra("token"));
            startActivity(intent);
        });

    }


//    protected void onResume() {
//        super.onResume();
//        viewModel.getChatList().observe(this, chatListItems -> {
//            adapter.setChatListItems(chatListItems);
//        });

//    }

}


