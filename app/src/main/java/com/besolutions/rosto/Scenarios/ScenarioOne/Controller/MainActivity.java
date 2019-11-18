package com.besolutions.rosto.Scenarios.ScenarioOne.Controller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.besolutions.rosto.R;
import com.besolutions.rosto.Scenarios.ScenarioFour.Controller.Orders_Fragment;
import com.besolutions.rosto.Scenarios.ScenarioThree.Controller.Me_Fragment;
import com.besolutions.rosto.Scenarios.SenarioFive.Controller.Branches_Fragment;
import com.besolutions.rosto.Utils.TinyDB;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    TinyDB tinyDB;
    BottomNavigationView navigation;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navigation = findViewById(R.id.navigation);
        fragmentManager=getSupportFragmentManager();

        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener()
        {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem)
            {
                switch (menuItem.getItemId())
                {
                    case R.id.me:

                        Me_Fragment me_fragment = new Me_Fragment();
                        loadFragment(me_fragment);

                        return true;


                    case R.id.orders:
                        Orders_Fragment orders_fragment = new Orders_Fragment();
                        loadFragment(orders_fragment);
                        return true;

                    case R.id.branches:

                        Branches_Fragment branches_fragment = new Branches_Fragment();
                        loadFragment(branches_fragment);

                        return true;


                    default:
                        return true;

                }


            }
        });


    }
    private void loadFragment(Fragment fragment)
    {

        fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container,fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }
}
