package com.example.makore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.widget.CompoundButton;
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
        handleTheme();
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
        binding.switchBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged (CompoundButton buttonView, boolean isChecked){

                // checking if the switch is turned on
                if (isChecked) {

                    // setting theme to night mode
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    buttonView.setText("Night Mode");
                }

                // if the above condition turns false
                // it means switch is turned off
                // by-default the switch will be off
                else {
                    // setting theme to light theme
                    AppCompatDelegate.setDefaultNightMode (AppCompatDelegate.MODE_NIGHT_NO);
                    buttonView.setText("Light Mode");
                }
            }
        });
//        binding.switchBtn.setOnClickListener(view -> {
//
//        });
    }

}