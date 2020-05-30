package com.example.weatherapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.provider.Settings;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity6 extends AppCompatActivity {
    int PERMISSION_ID=44;
    FusedLocationProviderClient mFusedLocationClient;
    public static String key="d1ced8681b58e1bf47a01c766879fb2b";
    public static String url="http://api.openweathermap.org/";
    public static String lat;
    public static String lon;
    TextView country,latitude,longitude,sunrise,sunset,temp,humidity,pressure,windspeed,cloudcover;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);
        country=findViewById(R.id.textViewco2);
        latitude=findViewById(R.id.textViewla2);
        longitude=findViewById(R.id.textViewlo2);
        sunrise=findViewById(R.id.textViewsr2);
        sunset=findViewById(R.id.textViewss2);
        temp=findViewById(R.id.textViewct2);
        humidity=findViewById(R.id.textViewh2);
        pressure=findViewById(R.id.textViewp2);
        windspeed=findViewById(R.id.textViewws2);
        cloudcover=findViewById(R.id.textViewcc2);
        mFusedLocationClient= LocationServices.getFusedLocationProviderClient(this);
        getLastLocation();
        findViewById(R.id.button4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData();
            }
        });
    }
    @SuppressLint("MissingPermission")
    private void getLastLocation(){
        if (checkPermissions()) {
            if (isLocationEnabled()) {
                mFusedLocationClient.getLastLocation().addOnCompleteListener(
                        new OnCompleteListener<Location>() {
                            @Override
                            public void onComplete(@NonNull Task<Location> task) {
                                Location location=task.getResult();
                                if (location == null) {
                                    requestNewLocationData();
                                } else {
                                    lat=Double.toString(location.getLatitude());
                                    lon=Double.toString(location.getLongitude());
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


    @SuppressLint("MissingPermission")
    private void requestNewLocationData(){

        LocationRequest mLocationRequest = new LocationRequest();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(0);
        mLocationRequest.setFastestInterval(0);
        mLocationRequest.setNumUpdates(1);

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        mFusedLocationClient.requestLocationUpdates(
                mLocationRequest, mLocationCallback,
                Looper.myLooper()
        );

    }

    private LocationCallback mLocationCallback = new LocationCallback() {
        @Override
        public void onLocationResult(LocationResult locationResult) {
            Location mLastLocation = locationResult.getLastLocation();
            lat=Double.toString(mLastLocation.getLatitude());
            lon=Double.toString(mLastLocation.getLongitude());
        }
    };

    private boolean checkPermissions() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        return false;
    }

    private void requestPermissions() {
        ActivityCompat.requestPermissions(
                this,
                new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION},
                PERMISSION_ID
        );
    }

    private boolean isLocationEnabled() {
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
                LocationManager.NETWORK_PROVIDER
        );
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_ID) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getLastLocation();
            }
        }
    }

    @Override
    public void onResume(){
        super.onResume();
        if (checkPermissions()) {
            getLastLocation();
        }
    }
    private void getData() {
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        OpenweatherAPI.WeatherService1 service1=retrofit.create(OpenweatherAPI.WeatherService1.class);
        Call<PostWeather> call=service1.getCurrentWeatherData(lat,lon,key);
        call.enqueue(new Callback<PostWeather>() {
            @Override
            public void onResponse(Call<PostWeather> call, Response<PostWeather> response) {
                if(response.code()==200){
                    PostWeather postWeather=response.body();
                    assert postWeather!=null;

                    country.setText("Country: "+ postWeather.getSys().getCountry());
                    latitude.setText("Latitude: "+ postWeather.getCoord().getLat());
                    longitude.setText("Longitude: "+ postWeather.getCoord().getLon());
                    long sr=postWeather.getSys().getSunrise();
                    Date date1=new java.util.Date(sr*1000L);
                    SimpleDateFormat sdf1 =new java.text.SimpleDateFormat("dd-MM-yyyy HH:mm:ss z");
                    sdf1.setTimeZone(java.util.TimeZone.getTimeZone("GMT+5:30"));
                    String sunr= sdf1.format(date1);
                    sunrise.setText("Sunrise at: "+sunr);
                    long ss=postWeather.getSys().getSunset();
                    Date date2=new java.util.Date(ss*1000L);
                    SimpleDateFormat sdf2=new java.text.SimpleDateFormat("dd-MM-yyyy HH:mm:ss z");
                    sdf2.setTimeZone(java.util.TimeZone.getTimeZone("GMT+5:30"));
                    String suns=sdf2.format(date2);
                    sunset.setText("Sunset at: "+suns);
                    String t=String.format("%.2f",postWeather.getMain().getTemp());
                    temp.setText("Current Temperature: "+t+ " Celcius");
                    humidity.setText("Humidity: "+ postWeather.getMain().getHumidity()+" %");
                    pressure.setText("Pressure: "+ postWeather.getMain().getPressure()+" hPa");
                    windspeed.setText("Wind speed: "+ postWeather.getWind().getSpeed()+" m/s");
                    cloudcover.setText("Cloud cover: "+ postWeather.getClouds().getAll()+ " %");
                }
            }

            @Override
            public void onFailure(Call<PostWeather> call, Throwable t) {
                country.setText(t.getMessage());
            }
        });
    }
}
