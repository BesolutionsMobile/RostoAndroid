package com.besolutions.rosto.Scenarios.ScenarioTwo.Controller;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.besolutions.rosto.NetworkLayar.Apicalls;
import com.besolutions.rosto.NetworkLayar.NetworkInterface;
import com.besolutions.rosto.NetworkLayar.ResponseModel;
import com.besolutions.rosto.R;
import com.besolutions.rosto.Scenarios.ScenarioOne.Controller.MainActivity;
import com.besolutions.rosto.Scenarios.ScenarioTwo.Model.Model_SignUp;
import com.google.gson.Gson;

public class Sign_Up extends AppCompatActivity implements NetworkInterface {

    EditText editname,editemail,editpass,editphone;
    Button btnsignup;
    ProgressDialog pg;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        editname = findViewById(R.id.editName);
        editemail = findViewById(R.id.edit_Email);
        editpass = findViewById(R.id.edit_pass);
        editphone = findViewById(R.id.editPhone);
        btnsignup = findViewById(R.id.btn_SignUp);

        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                new Apicalls(Sign_Up.this,Sign_Up.this).registerUser(editname.getText().toString(),editemail.getText().toString(),editpass.getText().toString(),editphone.getText().toString());

                pg.show();

            }
        });

        pg = new ProgressDialog(Sign_Up.this);
        pg.setMessage("Loading...");
    }

    @Override
    public void OnStart() {

    }

    @Override
    public void OnResponse(ResponseModel model) {

        Gson gson = new Gson();

        Model_SignUp model_signUp = gson.fromJson(model.getResponse(),Model_SignUp.class);

        Toast.makeText(this, ""+model_signUp.getStatus(), Toast.LENGTH_SHORT).show();

    }

    @Override
    public void OnError(VolleyError error) {

        Toast.makeText(this, ""+error.toString(), Toast.LENGTH_SHORT).show();

        pg.cancel();
    }
}
