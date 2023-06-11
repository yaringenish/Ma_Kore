package com.example.makore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.makore.adapters.ChatListAdapter;
import com.example.makore.entities.ChatListItem;

import java.util.ArrayList;
import java.util.List;

public class ChatListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_list);

        RecyclerView lstChatItems = findViewById(R.id.lstChatItems);
        final ChatListAdapter adapter = new ChatListAdapter(this);

        lstChatItems.setAdapter(adapter);
        lstChatItems.setLayoutManager(new LinearLayoutManager(this));

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, LinearLayoutManager.VERTICAL);
        lstChatItems.addItemDecoration(dividerItemDecoration);


        List<ChatListItem> s = new ArrayList<>();
        s.add(new ChatListItem("roi", R.drawable.levels, "aaaa"));
        s.add(new ChatListItem("roi2", R.drawable.levels, "aaaa2"));
        s.add(new ChatListItem("roi3", R.drawable.levels, "aaaa3"));
        s.add(new ChatListItem("roi4", R.drawable.levels, "aaaa4"));
        s.add(new ChatListItem("roi5", R.drawable.levels, "aaaa5"));


        s.add(new ChatListItem("roi6", R.drawable.levels, "aaaa6"));
        s.add(new ChatListItem("roi7", R.drawable.levels, "aaaa7"));
        s.add(new ChatListItem("roi8", R.drawable.levels, "aaaa8"));
        s.add(new ChatListItem("roi9", R.drawable.levels, "aaaa9"));
        s.add(new ChatListItem("roi10", R.drawable.levels, "aaaa10"));

        s.add(new ChatListItem("roi11", R.drawable.levels, "aaaa11"));
        s.add(new ChatListItem("roi12", R.drawable.levels, "aaaa12"));
        s.add(new ChatListItem("roi13", R.drawable.levels, "aaaa13"));
        s.add(new ChatListItem("roi14", R.drawable.levels, "aaaa14"));
        s.add(new ChatListItem("roi15", R.drawable.levels, "aaaa15"));

        s.add(new ChatListItem("roi16", R.drawable.levels, "aaaa16"));
        s.add(new ChatListItem("roi17", R.drawable.levels, "aaaa17"));
        s.add(new ChatListItem("roi18", R.drawable.levels, "aaaa18"));
        s.add(new ChatListItem("roi19", R.drawable.levels, "aaaa19"));
        s.add(new ChatListItem("roi20", R.drawable.levels, "aaaa20"));

        adapter.setChatListItems(s);


    }
}