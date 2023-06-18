package com.example.makore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.makore.api.ChatAPI;
import com.example.makore.apiObjects.LoginData;
import com.example.makore.apiObjects.RegisterRequestBody;
import com.example.makore.callbacks.RegisterCallBack;
import com.example.makore.callbacks.TokenCallback;
import com.example.makore.databinding.ActivityLoginBinding;
import com.example.makore.databinding.ActivityRegisterBinding;

public class RegisterActivity extends AppCompatActivity {
    private ActivityRegisterBinding binding;

    private ChatAPI  chatAPI = new ChatAPI();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        handleRegister();
    }
    private void handleRegister(){
        binding.btnRegister.setOnClickListener(view -> {
            String userName = binding.etRegisterUsername.getText().toString();
            String password = binding.etRegisterPassword.getText().toString();
            String confirmPassword = binding.etRegisterSamePassword.getText().toString();
            String displayName = binding.etRegisterDisplayName.getText().toString();
//            String picture = binding.btnSelectImage.getContentDescription().toString();
            if (userName.isEmpty()) {
                binding.tvRegisterErrors.setText("Please insert user name");
                return;
            }
//
//            if (password.length() < 8) {
//                binding.tvRegisterErrors.setText("The password must be at least 8 characters");
//                return;
//            }
//
//            if (!password.equals(confirmPassword)) {
//                binding.tvRegisterErrors.setText("Please confirm the password again");
//                return;
//            }
//
//            if (displayName.isEmpty()) {
//                binding.tvRegisterErrors.setText("Please insert display name");
//                return;
//            }

//            if (picture.isEmpty()) {
//                binding.tvRegisterErrors.setText("Please choose a picture");
//                return;
//            }

            RegisterRequestBody requestBody = new RegisterRequestBody(userName,password,displayName,"stam");
            chatAPI.createUser(requestBody,  new RegisterCallBack() {
                @Override
                public void onRegisterResponse(int status) {
                    binding.tvRegisterErrors.setText("");
                    if(status == 200) {
                        finish();
//                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
//                        startActivity(intent);
                    } else {
                        binding.tvRegisterErrors.setText(R.string.usernameOrPassword);
                    }
                }
            });
        });

    }
}