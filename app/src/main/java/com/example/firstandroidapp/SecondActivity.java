package com.example.firstandroidapp;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.util.Calendar;

public class SecondActivity extends AppCompatActivity implements LocationListener {

    private static final String TAG = "@@SecondActivity";
    private static final String DEFAULT_LOC_MESSAGE = "Click on the button and allow sharing location w/ us";
    private static final String ERROR_LOC_MESSAGE = "Srry the location wasn't be shared so u couldn't see it. Btw it's ur fault..";
    private static final int REQUEST_CODE_LOCATION_OK=1;

    private Button btnGetLoc;
    private TextView vtLocLatitude, vtrLocLatitude, vtLocLongitude, vtrLocLongitude, vtLocTips;
    private LocationManager locationManager;
    private Location location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        btnGetLoc = findViewById(R.id.btnGetLoc);
        vtLocTips = findViewById(R.id.vtLocTips);
        vtLocLatitude = findViewById(R.id.vtLocLatitude);
        vtLocLatitude.setVisibility(View.INVISIBLE);
        vtrLocLatitude = findViewById(R.id.vtrLocLatitude);
        vtLocLongitude = findViewById(R.id.vtLocLongitude);
        vtLocLongitude.setVisibility(View.INVISIBLE);
        vtrLocLongitude = findViewById(R.id.vtrLocLongitude);

        btnGetLoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vtLocTips.setVisibility(View.INVISIBLE);
                vtLocLatitude.setVisibility(View.INVISIBLE);
                vtrLocLatitude.setVisibility(View.INVISIBLE);
                vtLocLongitude.setVisibility(View.INVISIBLE);
                vtrLocLongitude.setVisibility(View.INVISIBLE);

                locationManager = (LocationManager)
                        SecondActivity.this.getSystemService(Context.LOCATION_SERVICE);

                // Request for permission
                ActivityCompat.requestPermissions(SecondActivity.this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        REQUEST_CODE_LOCATION_OK);
                if(ActivityCompat.checkSelfPermission(SecondActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
                {
                    // Update location if permission granted
                    locationManager.requestLocationUpdates(
                            LocationManager.GPS_PROVIDER,
                            5000,
                            10,
                            SecondActivity.this);
                    location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

                    vtrLocLatitude.setText(Double.toString(location.getLatitude()));
                    vtrLocLongitude.setText(Double.toString(location.getLongitude()));

                    vtLocLatitude.setVisibility(View.VISIBLE);
                    vtrLocLatitude.setVisibility(View.VISIBLE);
                    vtLocLongitude.setVisibility(View.VISIBLE);
                    vtrLocLongitude.setVisibility(View.VISIBLE);
                }
            }
        });

        Intent intent = getIntent();
        String welcomeMessage = "dear " + intent.getStringExtra(MainActivity.USR_NAME) + " " +
                intent.getStringExtra(MainActivity.USR_SURNAME) + " u're ";

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

        Log.i(TAG, "lilJoke:\t\t" + lilJokeMessage +
                "\nAge:\t\t" + usrAge);

        ((TextView) findViewById(R.id.vtUsrName)).setText(welcomeMessage);
        ((TextView) findViewById(R.id.vtUsrName)).setGravity(Gravity.CENTER);
        ((TextView) findViewById(R.id.vtLilJoke)).setText(lilJokeMessage);
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults)
    {
        if(ActivityCompat.checkSelfPermission(SecondActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
        {
            // Update location if permission granted
            locationManager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER,
                    5000,
                    10,
                    SecondActivity.this);
            location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

            vtrLocLatitude.setText(Double.toString(location.getLatitude()));
            vtrLocLongitude.setText(Double.toString(location.getLongitude()));

            vtLocLatitude.setVisibility(View.VISIBLE);
            vtrLocLatitude.setVisibility(View.VISIBLE);
            vtLocLongitude.setVisibility(View.VISIBLE);
            vtrLocLongitude.setVisibility(View.VISIBLE);
        }
        else
        {
            //иначе пользователь получает уведомление, что для определения геолокации нужно разрешение
            vtLocTips.setText(ERROR_LOC_MESSAGE);
            vtLocTips.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onLocationChanged(Location loc) {

        location = loc;
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) { }

    @Override
    public void onProviderEnabled(String provider) { }

    @Override
    public void onProviderDisabled(String provider) { }

}
