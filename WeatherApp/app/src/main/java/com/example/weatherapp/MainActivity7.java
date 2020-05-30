package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity7 extends AppCompatActivity {
    private static final String key = "d1ced8681b58e1bf47a01c766879fb2b";
    private static final String url = "http://api.openweathermap.org/";
    public static String city;
    TextView country,latitude,longitude,sunrise,sunset,temp,humidity,pressure,windspeed,cloudcover;
    EditText cityinput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7);
        country=findViewById(R.id.textViewco3);
        latitude=findViewById(R.id.textViewla3);
        longitude=findViewById(R.id.textViewlo3);
        sunrise=findViewById(R.id.textViewsr3);
        sunset=findViewById(R.id.textViewss3);
        temp=findViewById(R.id.textViewct3);
        humidity=findViewById(R.id.textViewh3);
        pressure=findViewById(R.id.textViewp3);
        windspeed=findViewById(R.id.textViewws3);
        cloudcover=findViewById(R.id.textViewcc3);
        cityinput=findViewById(R.id.editText3);
        findViewById(R.id.button5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                city=cityinput.getText().toString();
                getData();
            }
        });
    }

    private void getData()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        OpenweatherAPI.WeatherService2 service=retrofit.create(OpenweatherAPI.WeatherService2.class);
        Call<PostWeather> call=service.getCurrentWeatherData(city,key);
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
