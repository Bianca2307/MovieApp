package com.example.movieapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.movieapp.ui.on_boarding.OnBoardingActivity;

public class MainActivity extends AppCompatActivity {

    public static void open(Context context){
        context.startActivity(new Intent(context, MainActivity.class));

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}