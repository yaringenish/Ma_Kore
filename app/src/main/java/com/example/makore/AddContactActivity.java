package com.example.makore;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.makore.api.ChatAPI;

import com.example.makore.callbacks.AddContactCallback;
import com.example.makore.databinding.ActivityAddContactBinding;


public class AddContactActivity extends AppCompatActivity {
    private ActivityAddContactBinding binding;
    private String username;
    private String token;

    private ChatAPI chatAPI = new ChatAPI();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddContactBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        handleBack();
        handleAddContact();
    }


    private void handleAddContact() {
        binding.btnAddContact.setOnClickListener(view -> {
            username = binding.etAddContactUsername.getText().toString();
            token = getIntent().getStringExtra("token");

            chatAPI.addContact(username, token, new AddContactCallback() {
                @Override
                public void onAddContactResponse(int code, String error) {
                    if(code == 200) {
                        finish();
                } else {
                        binding.tvAddContactErrors.setText(error);
                    }
            }
        });
    });
    }


    private void handleBack() {
        binding.btnBackToChatList.setOnClickListener(view -> {
            //back to chatList activity
            finish();
        });
    }
}