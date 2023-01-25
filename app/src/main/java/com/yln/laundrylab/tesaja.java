package com.yln.laundrylab;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.ktx.Firebase;

import java.time.temporal.Temporal;

public class tesaja extends AppCompatActivity {
    private FirebaseUser firebaseUser;
    private TextView textName;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Log.d("LaundryLab", "This Loaded");

        setContentView(R.layout.fragment_home);
        textName = findViewById(R.id.firebaseName);
        if(firebaseUser!=null){
            textName.setText(firebaseUser.getDisplayName());
        }else {
            textName.setText("Login gagal");
        }
    }
}
