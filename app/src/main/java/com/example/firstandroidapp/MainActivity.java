package com.example.firstandroidapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    public static final String USR_NAME = "com.example.firstandroidapp.USR_NAME";
    public static final String USR_SURNAME = "com.example.firstandroidapp.USR_SURNAME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void letsGoBtnClickHandler(View view) {
        // React

        Intent intent = new Intent(this, SecondActivity.class);

        String usrName = ((EditText) findViewById(R.id.nameEditText)).getText().toString();
        String usrSurname = ((EditText) findViewById(R.id.surnameEditText)).getText().toString();

        // add validation of user credentials validation

        intent.putExtra(USR_NAME, usrName);
        intent.putExtra(USR_SURNAME, usrSurname);
        startActivity(intent);
    }
}
