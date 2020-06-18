package com.example.interiordesign;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;

public class MiscActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private int[] images={R.drawable.i202006140017,R.drawable.i202006140022,R.drawable.i202006140023,
            R.drawable.i202006140026,R.drawable.i202006140027,R.drawable.i202006140028,R.drawable.i202006140034};
    private String [] names={"bedroom","dressingtable","hearth","tv","sleeepingtable","fireplace","jummer"};
    private RecyclerAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_misc);
        recyclerView=findViewById(R.id.recyclerview5);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(layoutManager);
        adapter=new RecyclerAdapter(images,names,this);
        recyclerView.setAdapter(adapter);
        Toolbar toolbar=findViewById(R.id.toolbar5);
        toolbar.inflateMenu(R.menu.example_menu);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId()==R.id.item1){
                    Intent intent1=new Intent(MiscActivity.this,ProfileActivity.class);
                    startActivity(intent1);
                    return true;
                }
                else if (item.getItemId()==R.id.item2){
                    FirebaseAuth.getInstance().signOut();
                    Intent intent=new Intent(MiscActivity.this,FirstActivity.class);
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
