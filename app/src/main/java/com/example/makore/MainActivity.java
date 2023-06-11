package com.example.makore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private String token;
    private String username;
    private int view = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);

//        Intent intent = new Intent(this, ChatListActivity.class);
//        startActivity(intent);

    }
}



//    Intent intent = new Intent(this, LoginActivity.class);
//    startActivity(intent);