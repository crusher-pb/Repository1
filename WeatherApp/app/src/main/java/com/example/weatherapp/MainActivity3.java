package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class MainActivity3 extends AppCompatActivity implements Choices.OnNoteListener {
    private ArrayList<String> mNames=new ArrayList<>();
    private ArrayList<String> mImageUrls=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        getImages();
    }
    private void getImages(){

        mImageUrls.add("https://cdn4.iconfinder.com/data/icons/maps-and-navigation-solid-icons-vol-1/72/07-512.png");
        mNames.add("Current location");

        mImageUrls.add("https://manula.s3.amazonaws.com/user/2525/img/gps-2.png");
        mNames.add("Any location");

        mImageUrls.add("https://image.flaticon.com/icons/svg/185/185376.svg");
        mNames.add("City name");

        initRecyclerView();

    }

    private void initRecyclerView(){
        RecyclerView recyclerView = findViewById(R.id.choiceList);
        Choices adapter = new Choices(this,mNames,mImageUrls,this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onNoteClick(int position) {
        if (position==0){
            Intent intent=new Intent(this,MainActivity6.class);
            startActivity(intent);
        }
        else if (position==1){
            Intent intent=new Intent(this,MainActivity2.class);
            startActivity(intent);
        }
        else if (position==2){
            Intent intent=new Intent(this,MainActivity7.class);
            startActivity(intent);
        }
    }
}
