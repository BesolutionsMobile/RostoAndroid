package com.besolutions.rosto;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;

import com.besolutions.rosto.Scenarios.ScenarioOne.Controller.MainActivity;


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
                String price = "";
                Intent in = new Intent("send_order_action");
                in.putExtra("category", price);
                dialog.getContext().sendBroadcast(in);
                dialog.dismiss();
           }
        }, 5000);


    }
}