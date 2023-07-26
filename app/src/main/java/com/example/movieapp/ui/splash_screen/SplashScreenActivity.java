package com.example.movieapp.ui.splash_screen;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.movieapp.MainActivity;
import com.example.movieapp.R;
import com.example.movieapp.ui.on_boarding.OnBoardingActivity;

@SuppressLint("CustomSplashScreen")
public class SplashScreenActivity extends AppCompatActivity {

    private Handler handler;
    private Runnable runnable;
    private static final long DELAY = 2000L;

    //pt fiecare activitate
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_splash_screen);

        initHandlerToOpenNextActivity();
    }

    private void initHandlerToOpenNextActivity() {
        handler = new Handler(Looper.getMainLooper());
        runnable = this::openNextScreen;
        handler.postDelayed(runnable, DELAY);
    }

    private void openNextScreen() {
        OnBoardingActivity.open(this);
        finish();
    }

    //pt fiecare activitate
    @Override
    protected void onDestroy() {
        removeHandler();
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        removeHandler();
        super.onBackPressed();
    }

    private void removeHandler() {
        if (handler != null && runnable != null) {
            handler.removeCallbacks(runnable);
            runnable = null;
            handler = null;
        }
    }
}

