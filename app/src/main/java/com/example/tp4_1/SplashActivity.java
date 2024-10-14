package com.example.tp4_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;


public class SplashActivity extends AppCompatActivity {

    private ImageView image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity);
        image = findViewById(R.id.image);

        image.animate().rotation(360f).setDuration(2000);
        image.animate().scaleX(0.5f).scaleY(0.5f).setDuration(3000);
        image.animate().translationYBy(1000f).setDuration(2000);
        image.animate().alpha(0f).setDuration(6000);
        Thread t1 = new Thread(){
            @Override
            public void run() {
                try {
                    sleep(2000);
                    Intent intent = new Intent(SplashActivity.this, ListActivity.class);
                    startActivity(intent);
                    SplashActivity.this.finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        t1.start();
    }
}
