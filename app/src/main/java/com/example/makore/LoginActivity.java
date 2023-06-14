package com.example.makore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.makore.api.ChatAPI;
import com.example.makore.apiObjects.LoginData;
import com.example.makore.apiObjects.TokenRequestBody;
import com.example.makore.callbacks.TokenCallback;
import com.example.makore.databinding.ActivityLoginBinding;


public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;
    private String username;
    private String password;

    private ChatAPI  chatAPI = new ChatAPI();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        handleLogin();
        handleRegister();
    }

    private void handleLogin() {
        binding.btnLogin.setOnClickListener(view -> {
            username = binding.etLoginUsername.getText().toString();
            password = binding.etLoginPassword.getText().toString();


//            LoginData loginData = new LoginData();
            chatAPI.getToken(username, password,  new TokenCallback() {
                @Override
                public void onTokenReceived(LoginData ld) {
                    if(ld.getToken() != null) {
                        Intent intent = new Intent(LoginActivity.this, ChatListActivity.class);
                        intent.putExtra("token", ld.getToken());
                        startActivity(intent);
                    } else {
                        binding.tvLoginErrors.setText(R.string.usernameOrPassword);
                    }
                }
            });
//            if(loginData.getToken() != null) {
//                Intent intent = new Intent(this, ChatListActivity.class);
//                intent.putExtra("token", loginData.getToken());
//                startActivity(intent);
//            } else {
//                binding.tvLoginErrors.setText(R.string.usernameOrPassword);
//            }

//            Intent intent = new Intent(this, ChatListActivity.class);
//            startActivity(intent);
            //validate
            //post request for token
            //setUsername
            //if valid username and password go to chat activity and transfer the token and username to the new intent
//            finish();
        });
    }
    private void handleRegister(){
        binding.btnLoginRegister.setOnClickListener(view -> {
            //start registerActivity
            Intent intent = new Intent(this, RegisterActivity.class);
            startActivity(intent);
            //no need for finish because when finishing register will return to here.
        });

    }
}