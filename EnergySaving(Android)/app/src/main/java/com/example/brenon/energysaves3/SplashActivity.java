package com.example.brenon.energysaves3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Handler;
import android.content.Intent;

public class SplashActivity extends AppCompatActivity implements Runnable {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Handler handler = new Handler();
        handler.postDelayed(this, 2000);
    }
    @Override
    public void run(){
        startActivity(new Intent(this, ActivityHistoricoConsumo.class));
        finish();

    }

}
