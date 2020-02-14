package com.besolutions.rosto.Scenarios.ScenarioOne.Controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.besolutions.rosto.R;
import com.besolutions.rosto.Scenarios.ScenarioTwo.Controller.SignIn;
import com.besolutions.rosto.Utils.TinyDB;
import com.besolutions.rosto.local_data.saved_data;
import com.bumptech.glide.Glide;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {
    TinyDB tinyDB;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        setContentView(R.layout.activity_splash2);
//        imageView = findViewById(R.id.imaggif);
//        Glide.with(this)
//                .asGif()
//                .load(getDrawable(R.drawable.logogif))
//                .into(imageView);

        tinyDB = new TinyDB(this);


        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                saved_data saved_data = new saved_data();
                if (saved_data.get_user_check(SplashActivity.this) == false) {

                    startActivity(new Intent(getApplicationContext(), SignIn.class));
                    finish();

                }
                if (saved_data.get_user_check(SplashActivity.this) == true) {

                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();
                }
            }
        };
        new Timer().schedule(timerTask, 4000);
    }

    @Override
    protected void onResume() {

        super.onResume();

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {

                //startActivity(new Intent(getApplicationContext(), SignIn.class));
                saved_data saved_data = new saved_data();


                if (saved_data.get_user_check(SplashActivity.this) == false) {

                    startActivity(new Intent(getApplicationContext(), SignIn.class));
                    finish();

                }
                if (saved_data.get_user_check(SplashActivity.this) == true) {

                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();
                }

            }
        };
        new Timer().schedule(timerTask, 4000);
    }
}
