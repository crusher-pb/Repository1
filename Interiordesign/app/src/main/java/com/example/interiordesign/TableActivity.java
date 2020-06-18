package com.example.interiordesign;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;

public class TableActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private int[] images={R.drawable.i202006140016,R.drawable.i202006140024,R.drawable.i202006140029,
            R.drawable.i202006140040,R.drawable.i202006140042,R.drawable.i202006140044,R.drawable.i202006140018};
    private String [] names={"artistschool","kitchentable","desk","diningtablemodel","lowspeeddinningtable","jocodiningtable","Bedsidetable"};
    private RecyclerAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);
        recyclerView=findViewById(R.id.recyclerview4);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(layoutManager);
        adapter=new RecyclerAdapter(images,names,this);
        recyclerView.setAdapter(adapter);
        Toolbar toolbar=findViewById(R.id.toolbar4);
        toolbar.inflateMenu(R.menu.example_menu);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId()==R.id.item1){
                    Intent intent1=new Intent(TableActivity.this,ProfileActivity.class);
                    startActivity(intent1);
                    return true;
                }
                else if (item.getItemId()==R.id.item2){
                    FirebaseAuth.getInstance().signOut();
                    Intent intent=new Intent(TableActivity.this,FirstActivity.class);
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
