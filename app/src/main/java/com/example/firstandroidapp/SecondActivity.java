package com.example.firstandroidapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class SecondActivity extends AppCompatActivity {

    private static final String TAG = "@@SecondActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent intent = getIntent();
        String welcomeMessage = "dear " + intent.getStringExtra(MainActivity.USR_NAME) + " " +
                intent.getStringExtra(MainActivity.USR_SURNAME);

        String lilJokeMessage = "";
        int usrAge = (Calendar.getInstance().get(Calendar.YEAR)) - Integer.parseInt(intent.getStringExtra(MainActivity.USR_BIRTH_YEAR));

        Log.w(TAG, "usrAge: \t\t" + usrAge
        );

        if (usrAge < 18) {
            lilJokeMessage = "too young to visit such apps!! lil' bastard..";
        } else if (usrAge < 40) {
            lilJokeMessage = "exactly in our target auditory \n(you and the other billion of people...)";
        } else {
            lilJokeMessage = "goin' home dirty pensioner, lol";
        }

        ((TextView) findViewById(R.id.vtUsrName)).setText(welcomeMessage);
        ((TextView) findViewById(R.id.vtLilJoke)).setText(lilJokeMessage);
    }
}
