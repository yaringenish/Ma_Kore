package com.example.makore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.example.makore.apiObjects.LoginData;
import com.example.makore.callbacks.TokenCallback;
import com.example.makore.databinding.ActivityCurrentChatBinding;
import com.example.makore.databinding.ActivitySettingsBinding;

public class SettingsActivity extends AppCompatActivity {
    private ActivitySettingsBinding binding;

    private TextView url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySettingsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        url = SharedViewSingleton.getInstance().getSharedTextView();
//        handleTheme();
        handleChangeUrl();
    }

    private void handleChangeUrl(){
        binding.btnUrl.setOnClickListener(view -> {
            EditText editText = binding.etUrl;
            String newUrl = binding.etUrl.getText().toString();
            SharedViewSingleton.getInstance().setSharedUrl(newUrl);
        });
    }
    private void handleTheme(){
        binding.btnTheme.setOnClickListener(view -> {

        });
    }

}