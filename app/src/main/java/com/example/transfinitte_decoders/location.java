package com.example.transfinitte_decoders;


import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Looper;
import android.provider.Settings;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.GeoPoint;

import org.imperiumlabs.geofirestore.GeoFirestore;
import org.imperiumlabs.geofirestore.GeoQuery;
import org.imperiumlabs.geofirestore.listeners.GeoQueryDataEventListener;
import org.imperiumlabs.geofirestore.listeners.GeoQueryEventListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class location extends AppCompatActivity {
    String user = "9983208880"; //phone number
    String lat;
    String lon;
    double latitude;
    double longitude;
    int PERMISSION_ID = 24;
    Location mLastLocation;
    Location location;
    GeoFirestore geofirestore;
    GeoQuery geoQuery;
    private FusedLocationProviderClient fusedLocationClient;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        tv = findViewById(R.id.textView);
        tv.setText("GeoFire");
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        CollectionReference collectionref = FirebaseFirestore.getInstance().collection("geoData");
        geofirestore = new GeoFirestore(collectionref);
        getLastLocation();
    }

    private boolean checkPermissions(){
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            return true;
        }
        return false;
    }
    private void requestPermissions(){
        ActivityCompat.requestPermissions(
                this,
                new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_BACKGROUND_LOCATION},
                PERMISSION_ID
        );
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) { //when a user allows or denies the permissions
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_ID) {
            if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                requestNewLocationData();
            }
        }
    }
    private boolean isLocationEnabled(){
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
                LocationManager.NETWORK_PROVIDER
        );
    }
    private void requestNewLocationData(){

        LocationRequest mLocationRequest = new LocationRequest();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(3000);
        mLocationRequest.setFastestInterval(1000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        fusedLocationClient.requestLocationUpdates(
                mLocationRequest, mLocationCallback,
                Looper.myLooper()
        );

    }
    private LocationCallback mLocationCallback = new LocationCallback() {
        @Override
        public void onLocationResult(LocationResult locationResult) {
            mLastLocation = locationResult.getLastLocation();
            latitude = mLastLocation.getLatitude();
            longitude = mLastLocation.getLongitude();
            lat = String.valueOf(mLastLocation.getLatitude());
            lon = String.valueOf(mLastLocation.getLongitude());
        }
    };
    private void getLastLocation(){
        if (checkPermissions()) {
            if (isLocationEnabled()) {
                fusedLocationClient.getLastLocation().addOnCompleteListener(
                        new OnCompleteListener<Location>() {
                            @Override
                            public void onComplete(@NonNull Task<Location> task) {
                                tv.setText("Permissions Checked");
                                location = task.getResult();
                                if (location == null) {
                                    requestNewLocationData();
                                } else {
                                    lat = String.valueOf(location.getLatitude());
                                    lon = String.valueOf(location.getLongitude());
                                    latitude = location.getLatitude();
                                    longitude = location.getLongitude();
                                    geofirestore.setLocation(user, new GeoPoint(latitude, longitude), new GeoFirestore.CompletionCallback() {
                                        @Override
                                        public void onComplete(Exception e) {
                                            Log.d("Server","Saved to Firestore");
                                        }
                                    });

                                    Log.d("LAT",lat.toString());
                                    tv.setText("Found a volunteer within 1 km.");
                                    queryGeoFire();
                                }
                            }
                        }
                );
            } else {
                Toast.makeText(this, "Turn on location", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }
        } else {
            requestPermissions();
        }
    }
    public void queryGeoFire() {
        geoQuery = geofirestore.queryAtLocation(new GeoPoint(latitude, longitude), 1);
        geoQuery.addGeoQueryEventListener(new GeoQueryEventListener() {

            @Override
            public void onKeyEntered(String s, GeoPoint geoPoint) {
                if(!(user.equalsIgnoreCase(s))) {
                    Toast.makeText(getApplicationContext(), "Volunteer within 1 km", Toast.LENGTH_LONG);
                    Log.d("geoPoint","Volunt");
                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + user));
                    startActivity(intent);
                }

            }

            @Override
            public void onKeyExited(String s) {
                geofirestore.removeLocation(s);
            }

            @Override
            public void onKeyMoved(String s, GeoPoint geoPoint) {
            }

            @Override
            public void onGeoQueryReady() {
            }

            @Override
            public void onGeoQueryError(Exception e) {

            }
        });
    }

    @Override
    public void onResume(){
        super.onResume();
        if (checkPermissions()) {
            requestNewLocationData();
        }

    }
}
