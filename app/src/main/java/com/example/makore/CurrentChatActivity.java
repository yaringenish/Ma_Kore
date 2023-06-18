package com.example.makore;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.makore.callbacks.GetChatCallBack;
import com.example.makore.databinding.ActivityAddContactBinding;
import com.example.makore.entities.Chat;
import com.example.makore.viewmodels.ChatItemViewModel;


public class CurrentChatActivity extends AppCompatActivity {
    private ActivityAddContactBinding binding;

    private ChatItemViewModel viewModel;
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddContactBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
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

        RecyclerView lstChatItems = findViewById(R.id.lstMessages);






    }

}
