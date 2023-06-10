package com.example.makore;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.makore.databinding.ActivityLoginBinding;
import com.example.makore.databinding.ActivityRegisterBinding;

public class RegisterActivity extends AppCompatActivity {
    private ActivityRegisterBinding binding;

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

            if (password.length() < 8) {
                binding.tvRegisterErrors.setText("The password must be at least 8 characters");
                return;
            }

            if (!password.equals(confirmPassword)) {
                binding.tvRegisterErrors.setText("Please confirm the password again");
                return;
            }

            if (displayName.isEmpty()) {
                binding.tvRegisterErrors.setText("Please insert display name");
                return;
            }

//            if (picture.isEmpty()) {
//                binding.tvRegisterErrors.setText("Please choose a picture");
//                return;
//            }
            binding.tvRegisterErrors.setText("");
            finish();
        });

    }
}