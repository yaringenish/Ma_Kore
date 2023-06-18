package com.example.makore;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.makore.api.ChatAPI;

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
//        handleAddContact();
    }


//    private void handleAddContact() {
//        binding.btnAddContact.setOnClickListener(view -> {
//            username = binding.etAddContactUsername.getText().toString();
//            token = getIntent().getStringExtra("token");
//
//            chatAPI.addContact(username, token, );
//
//            chatAPI.getToken(username, password, new TokenCallback() {
//                @Override
//                public void onTokenReceived(LoginData ld) {
//                    if (ld.getToken() != null) {
//                        Intent intent = new Intent(LoginActivity.this, ChatListActivity.class);
//                        intent.putExtra("token", ld.getToken());
//                        startActivity(intent);
//                    } else {
//                        binding.tvLoginErrors.setText(R.string.usernameOrPassword);
//                    }
//                }
//            });
//        }
//    }


    private void handleBack() {
        binding.btnBackToChatList.setOnClickListener(view -> {
            //back to chatList activity
            finish();
        });
    }
}
