package com.besolutions.rosto.Scenarios.ScenarioThree.Controller;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.besolutions.rosto.NetworkLayar.Apicalls;
import com.besolutions.rosto.NetworkLayar.NetworkInterface;
import com.besolutions.rosto.NetworkLayar.ResponseModel;
import com.besolutions.rosto.R;
import com.besolutions.rosto.Scenarios.ScenarioThree.Model.Model_Edit_Profile;
import com.besolutions.rosto.local_data.saved_data;
import com.google.gson.Gson;

public class Change_Pass extends AppCompatActivity implements NetworkInterface {

    EditText editoldpass, editnewpass,editconfirmnewpass;
    Button btnchangepass;
    ProgressBar pg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pass);

        editoldpass = findViewById(R.id.editOldPassword);
        editnewpass = findViewById(R.id.editNewPassword);
        editconfirmnewpass = findViewById(R.id.editConfirmNewPassword);
        btnchangepass = findViewById(R.id.btnChangePassword);
        pg = findViewById(R.id.loading);
        pg.setVisibility(View.GONE);
        saved_data saved_data = new saved_data();
        final String id_user= saved_data.get_user_id(this);

        btnchangepass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                pg.setVisibility(View.VISIBLE);
                new Apicalls(Change_Pass.this,Change_Pass.this).change_password(editoldpass.getText().toString(),editnewpass.getText().toString(),editconfirmnewpass.getText().toString(),id_user);

            }
        });

    }

    @Override
    public void OnStart() {

    }

    @Override
    public void OnResponse(ResponseModel model) {

        pg.setVisibility(View.GONE);

        Gson gson = new Gson();

        Model_Edit_Profile modelEditProfile = gson.fromJson(model.getResponse(),Model_Edit_Profile.class);

        if (modelEditProfile.getStatus()==1)
        {
            Toast.makeText(this, ""+modelEditProfile.getMessage(), Toast.LENGTH_SHORT).show();


        }else if (modelEditProfile.getStatus()==2)
        {
            Toast.makeText(this, ""+modelEditProfile.getMessage(), Toast.LENGTH_SHORT).show();


        }else if (modelEditProfile.getStatus()==3)
        {
            Toast.makeText(this, ""+modelEditProfile.getMessage(), Toast.LENGTH_SHORT).show();

        }



    }

    @Override
    public void OnError(VolleyError error) {
        pg.setVisibility(View.GONE);

        Toast.makeText(this, ""+error.toString(), Toast.LENGTH_SHORT).show();

    }
}
