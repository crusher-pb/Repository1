package com.example.interiordesign;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity7 extends AppCompatActivity {
    EditText emailid,password;
    Button btnsignin;
    TextView tvsignup;
    FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7);

        mFirebaseAuth=FirebaseAuth.getInstance();
        emailid=findViewById(R.id.editText3);
        password=findViewById(R.id.editText4);
        btnsignin=findViewById(R.id.button2);
        tvsignup=findViewById(R.id.textView2);

        mAuthStateListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mFirebaseUser=mFirebaseAuth.getCurrentUser();
                if (mFirebaseUser!=null){
                 Toast.makeText(MainActivity7.this,"You are logged in",Toast.LENGTH_SHORT).show();
                 startActivity(new Intent(MainActivity7.this,MainActivity2.class));
                }
                else{
                    Toast.makeText(MainActivity7.this,"Please Login",Toast.LENGTH_SHORT).show();
                }
            }
        };
        btnsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=emailid.getText().toString();
                String pwd=password.getText().toString();
                if (email.isEmpty() && pwd.isEmpty()){
                    Toast.makeText(MainActivity7.this,"Fields are empty",Toast.LENGTH_SHORT).show();
                }
                else if (pwd.isEmpty()){
                    password.setError("Please enter password");
                    password.requestFocus();
                }
                else if (email.isEmpty()){
                    emailid.setError("Please enter password");
                    emailid.requestFocus();
                }
                else if (!(email.isEmpty()) && !(pwd.isEmpty())){
                    mFirebaseAuth.signInWithEmailAndPassword(email,pwd).addOnCompleteListener(MainActivity7.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()){
                                Toast.makeText(MainActivity7.this,"Login unsuccessful! Please try again",Toast.LENGTH_SHORT).show();
                            }
                            else {
                                startActivity(new Intent(MainActivity7.this,MainActivity2.class));
                            }
                        }
                    });
                }
                else{
                    Toast.makeText(MainActivity7.this,"An error occurred! Please try again",Toast.LENGTH_SHORT).show();
                }
            }
        });

        tvsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity7.this,MainActivity.class));
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);
    }
}
