package com.example.native_gps;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.Manifest;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class MainActivity extends AppCompatActivity implements LocationListener {

    LocationManager lmanager;
    String provider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener((view) -> {
//
//        }
//    });

        lmanager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        provider = lmanager.getBestProvider(criteria,false);

        if(Build.VERSION.SDK_INT>=23 &&
                ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            return;
        }
        if(provider != null && !provider.equals(""))
        {
            Location location = lmanager.getLastKnownLocation(provider);
            lmanager.requestLocationUpdates(provider,20000,1,this);

            if(location != null){
                onLocationChanged(location);
            }
            else{
                Toast.makeText(MainActivity.this,"Location Not Available", Toast.LENGTH_LONG).show();
            }
        }
        else{
            Toast.makeText(MainActivity.this,"No Provider Found",Toast.LENGTH_LONG).show();
        }
    }


    @Override
    public void onLocationChanged(@NonNull Location location) {
        TextView T1 = (TextView) findViewById(R.id.txtlat);
        TextView T2 = (TextView) findViewById(R.id.txtlong);

        T1.setText(""+location.getLatitude());
        T2.setText(""+location.getLongitude());
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        LocationListener.super.onStatusChanged(provider, status, extras);
    }

    @Override
    public void onProviderEnabled(@NonNull String provider) {
        LocationListener.super.onProviderEnabled(provider);
    }

    @Override
    public void onProviderDisabled(@NonNull String provider) {
        LocationListener.super.onProviderDisabled(provider);
    }
}