package com.besolutions.rosto.Scenarios.ScenarioOne.Controller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.MenuItem;

import com.besolutions.rosto.R;
import com.besolutions.rosto.Scenarios.ScenarioFour.Controller.Orders_Fragment;
import com.besolutions.rosto.Scenarios.ScenarioOne.Pattrens.IFOnBackPressed;
import com.besolutions.rosto.Scenarios.ScenarioThree.Controller.Me_Fragment;
import com.besolutions.rosto.Scenarios.SenarioFive.Controller.Branches_Fragment;
import com.besolutions.rosto.Utils.TinyDB;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    TinyDB tinyDB;
    public  static BottomNavigationView navigation;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    IntentFilter myFilter = new IntentFilter();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navigation = findViewById(R.id.navigation);
        fragmentManager = getSupportFragmentManager();
        navigation.setSelectedItemId(R.id.branches);
        Branches_Fragment branches_fragment = new Branches_Fragment();
        loadFragment(branches_fragment);
        myFilter.addAction("delete_action");
        myFilter.addAction("add_order_action");
        myFilter.addAction("send_order_action");
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
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

    @Override
    public void onStart() {
        this.registerReceiver(mReceiverLocation,myFilter);

        super.onStart();
    }

    @Override
    public void onStop() {
        this.unregisterReceiver(mReceiverLocation);
        super.onStop();
    }

    private void loadFragment(Fragment fragment) {

        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
    }
/*
    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }


 */


    private BroadcastReceiver mReceiverLocation = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            try {
                //String deletedPrice = String.valueOf(intent.getSerializableExtra("category"));
                // int deletes_price=count_price()-Integer.parseInt(deletedPrice);
                String action = intent.getAction();
                if (action != null)
                {
                    if (action.equals("delete_action"))
                    {

                        navigation.setSelectedItemId(R.id.orders);

                    }else if (action.equals("add_order_action"))
                    {

                        navigation.setSelectedItemId(R.id.branches);

                    }else if (action.equals("send_order_action"))
                    {

                        navigation.setSelectedItemId(R.id.branches);

                    }
                }


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    @Override public void onBackPressed() {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if (!(fragment instanceof IFOnBackPressed) || !((IFOnBackPressed) fragment).onBackPressed()) {
            super.onBackPressed();
        }
    }
}
