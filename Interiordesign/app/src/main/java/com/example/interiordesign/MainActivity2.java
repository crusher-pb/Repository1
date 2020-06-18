package com.example.interiordesign;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity2 extends AppCompatActivity{
    private RecyclerView recyclerView;
    private int[] images={R.drawable.i202006140019,R.drawable.i202006140021,R.drawable.i202006140025,R.drawable.i202006140038};
    private String [] names={"chairsingleton","comfycouch","sofasingle","sofabitgood"};
    private RecyclerAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        recyclerView=findViewById(R.id.recyclerview);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(layoutManager);
        adapter=new RecyclerAdapter(images,names,this);
        recyclerView.setAdapter(adapter);
        Toolbar toolbar=findViewById(R.id.toolbar);
        toolbar.inflateMenu(R.menu.example_menu);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId()==R.id.item1){
                    Intent intent1=new Intent(MainActivity2.this,ProfileActivity.class);
                    startActivity(intent1);
                    return true;
                }
                else if (item.getItemId()==R.id.item2){
                    FirebaseAuth.getInstance().signOut();
                    Intent intent=new Intent(MainActivity2.this,FirstActivity.class);
                    startActivity(intent);
                    return true;
                }
                else {
                    return false;
                }
            }
        });
    }

}
