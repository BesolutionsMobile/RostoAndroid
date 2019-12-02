package com.besolutions.rosto.Scenarios.ScenarioOne.Controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.besolutions.rosto.R;
import com.besolutions.rosto.Scenarios.ScenarioTwo.Controller.SignIn;
import com.besolutions.rosto.Utils.TinyDB;
import com.besolutions.rosto.local_data.saved_data;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {
    TinyDB tinyDB ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        tinyDB = new TinyDB(this);


        TimerTask timerTask = new TimerTask()
        {
            @Override
            public void run()
            {
                saved_data saved_data = new saved_data();


                if (saved_data.get_user_check(SplashActivity.this)==false) {

                startActivity(new Intent(getApplicationContext(), SignIn.class));

            }
                if (saved_data.get_user_check(SplashActivity.this) == true) {

                    startActivity(new Intent(getApplicationContext(), MainActivity.class));

                }
            }
        };
        new Timer().schedule(timerTask, 3000);
    }

    @Override
    protected void onResume() {
        super.onResume();

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {

                //startActivity(new Intent(getApplicationContext(), SignIn.class));
                saved_data saved_data = new saved_data();


                if (saved_data.get_user_check(SplashActivity.this)==false) {

                    startActivity(new Intent(getApplicationContext(), SignIn.class));

                }
                if (saved_data.get_user_check(SplashActivity.this) == true) {

                    startActivity(new Intent(getApplicationContext(), MainActivity.class));

                }

            }
        };
        new Timer().schedule(timerTask, 3000);
    }
}
