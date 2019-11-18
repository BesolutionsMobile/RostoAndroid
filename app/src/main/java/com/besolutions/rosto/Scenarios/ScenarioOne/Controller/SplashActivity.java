package com.besolutions.rosto.Scenarios.ScenarioOne.Controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.besolutions.rosto.R;
import com.besolutions.rosto.Scenarios.ScenarioTwo.Controller.SignIn;
import com.besolutions.rosto.Utils.TinyDB;

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


                Intent intent = new Intent(getApplicationContext(), SignIn.class);
                startActivity(intent);


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

                Intent intent = new Intent(getApplicationContext(), SignIn.class);
                startActivity(intent);

            }
        };
        new Timer().schedule(timerTask, 3000);
    }
}
