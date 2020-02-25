package com.example.firstandroidapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent intent = getIntent();
        String welcomeMessage = "Dear " + intent.getStringExtra(MainActivity.USR_NAME) +
                intent.getStringExtra(MainActivity.USR_SURNAME) + ", Welcome to The Best App Ever!!!";

        ((TextView) findViewById(R.id.welcomeTextView)).setText(welcomeMessage);
    }
}
