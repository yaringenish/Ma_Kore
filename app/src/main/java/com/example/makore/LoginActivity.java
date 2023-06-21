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
            setContentView(R.layout.connecting);
//            username = binding.etLoginUsername.getText().toString();
//            password = binding.etLoginPassword.getText().toString();

            username = "yarin";
            password = "11111111";
            chatAPI.getToken(username, password,  new TokenCallback() {
                @Override
                public void onTokenReceived(LoginData ld) {
                    if(ld.getToken() != null) {
                        Intent intent = new Intent(LoginActivity.this, ChatListActivity.class);
                        intent.putExtra("token", ld.getToken());
                        intent.putExtra("username", ld.getUsername());
                        startActivity(intent);
                    } else {
                        binding.tvLoginErrors.setText(R.string.usernameOrPassword);
                    }
                }
            });
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