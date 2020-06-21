package com.besolutions.rosto;
import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.view.Window;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.besolutions.rosto.Scenarios.ScenarioMain.Controller.MainActivity;
import com.besolutions.rosto.Scenarios.ScenarioHome.Controller.Home;


public class MyProgressDialog {
    public void dialog(final Context context, int resource, double widthh) {
        final Dialog dialog = new Dialog(context);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(resource);
        int width = (int) (context.getResources().getDisplayMetrics().widthPixels * widthh);
        int height = android.view.WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.getWindow().setLayout(width, height);
        dialog.show();



        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                MainActivity.navigation.setSelectedItemId(R.id.branches);
                FragmentTransaction fr = ((AppCompatActivity)context).getSupportFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_container, new Home());
                fr.commit();
                dialog.dismiss();
           }
        }, 5000);


    }
}