package com.example.makore;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.makore.databinding.ActivityAddContactBinding;


public class CurrentChatActivity extends AppCompatActivity {
    private ActivityAddContactBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddContactBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

    }

}
