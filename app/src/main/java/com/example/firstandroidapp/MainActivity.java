package com.example.firstandroidapp;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private static final String TAG = "@@MainActivity";

    public static final String USR_NAME = "com.example.firstandroidapp.USR_NAME";
    public static final String USR_SURNAME = "com.example.firstandroidapp.USR_SURNAME";
    public static final String USR_BIRTH_DATE = "com.example.firstandroidapp.USR_BIRTHDATE";
    public static final String USR_BIRTH_YEAR = "com.example.firstandroidapp.USR_BIRTHYEAR";

    private String usrYearOfBirth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    /**
     * Handle attempt to go to the next activity
     * @param view
     */
    public void letsGoBtnClickHandler(View view) {

        String usrName = ((EditText) findViewById(R.id.vetName)).getText().toString();
        String usrSurname = ((EditText) findViewById(R.id.vetSurname)).getText().toString();
        String usrBirthDate = ((EditText) findViewById(R.id.vetBirthDate)).getText().toString();
        Toast emptyValueToast;

        if (usrName.isEmpty()) {
            emptyValueToast = Toast.makeText(getApplicationContext(), "Name is required, dummy..\n(as all the other fields)", Toast.LENGTH_SHORT);
            emptyValueToast.setGravity(Gravity.TOP, 0, 200);
            emptyValueToast.show();
            return;
        }
        if (usrSurname.isEmpty()) {
            emptyValueToast = Toast.makeText(getApplicationContext(), "Surname is required, dummy..\n(as all the other fields)", Toast.LENGTH_SHORT);
            emptyValueToast.setGravity(Gravity.TOP, 0, 200);
            emptyValueToast.show();
            return;
        }
        if (usrBirthDate.isEmpty()) {
            emptyValueToast = Toast.makeText(getApplicationContext(), "Birth date is required, dummy..\n(as all the other fields)", Toast.LENGTH_SHORT);
            emptyValueToast.setGravity(Gravity.TOP, 0, 200);
            emptyValueToast.show();
            return;
        }

        Intent intent = new Intent(this, SecondActivity.class);

        // add user credentials validation
        Log.i(TAG, "usrName: \t\t" + usrName +
                "\nusrSurname: \t\t" + usrSurname +
                "\nusrBirthDate: \t\t" + usrBirthDate +
                "\nthis.usrYearOfBirth: \t" + usrYearOfBirth
        );
        intent.putExtra(USR_NAME, usrName);
        intent.putExtra(USR_SURNAME, usrSurname);
        intent.putExtra(USR_BIRTH_DATE, usrBirthDate);
        intent.putExtra(USR_BIRTH_YEAR, usrYearOfBirth);
        startActivity(intent);
    }

    /**
     * Open DatePicker to select User BirthDate
     *
     * @param view
     */
    public void vetBirthDateClickHandler(View view) {
        DialogFragment datePicker = new DatePickerFragmentDialog();
        datePicker.show(getSupportFragmentManager(), "Custom Date Picker");
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {

        // set full date to edit text view
        ((EditText) findViewById(R.id.vetBirthDate)).setText(String.format("%d/%d/%d", dayOfMonth, month, year));

        // also set usrYearOfBirth field to pass it further into Second Activity without parsing full dateOfBirth
        usrYearOfBirth = "" + year;

    }
}
