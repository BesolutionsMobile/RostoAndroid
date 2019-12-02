package com.besolutions.rosto.Scenarios.ScenarioTwo.Controller;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.besolutions.rosto.NetworkLayar.Apicalls;
import com.besolutions.rosto.NetworkLayar.NetworkInterface;
import com.besolutions.rosto.NetworkLayar.ResponseModel;
import com.besolutions.rosto.R;
import com.besolutions.rosto.Scenarios.ScenarioOne.Controller.MainActivity;
import com.besolutions.rosto.Scenarios.ScenarioTwo.Model.UserFinalResponse;
import com.besolutions.rosto.local_data.send_data;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class SignIn extends AppCompatActivity  implements NetworkInterface {

    TextView txtRegist;
    EditText editmail,editpass;
    Button btnsignin;
    ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        txtRegist = findViewById(R.id.txtRegister);
        editmail = findViewById(R.id.edit_Email);
        editpass = findViewById(R.id.edit_pass);
        btnsignin = findViewById(R.id.btn_SignIn);

        btnsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                new Apicalls(SignIn.this,SignIn.this).loginUser(editmail.getText().toString(),editpass.getText().toString());
                pd.show();
            }
        });

        pd = new ProgressDialog(SignIn.this);


        txtRegist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(SignIn.this,Sign_Up.class));

            }
        });

        //requests();

    }

    @Override
    public void OnStart() {

        pd.setMessage("Loading...");
    }

    @Override
    public void OnResponse(ResponseModel model) {

        pd.cancel();
        Gson gson = new Gson();

        send_data send_data = new send_data();
        send_data.userId_check(this,true);
        //Toast.makeText(this, model.getResponse(), Toast.LENGTH_SHORT).show();

        UserFinalResponse user = gson.fromJson(model.getResponse(), UserFinalResponse.class);

        if (user.getStatus()== 1)
        {
            startActivity(new Intent(SignIn.this, MainActivity.class));
            Toast.makeText(this, ""+user.getMessage(), Toast.LENGTH_SHORT).show();

            send_data.SET_USER_NAME(this,user.getUserData().getName());
            send_data.SET_USER_EMAIL(this,user.getUserData().getMail());
            send_data.SET_USER_PHONE(this,user.getUserData().getPhone());
            send_data.SET_USER_ID(this,user.getUserData().getId());

        }else if (user.getStatus() == 2)
        {
            Toast.makeText(this, ""+user.getMessage(), Toast.LENGTH_SHORT).show();
        }else
        {
            Toast.makeText(this, ""+user.getMessage(), Toast.LENGTH_SHORT).show();
        }



    }

    @Override
    public void OnError(VolleyError error) {

        Toast.makeText(this, "error "+error.toString(), Toast.LENGTH_SHORT).show();
        pd.cancel();



    }

    void requests() {


        StringRequest requests = new StringRequest(Request.Method.POST, "https://webdesign.be4em.info/rosto_api_ar/user/login/549834453/25598", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response != null) {
                    Log.e("Your Array Response", response);
                } else {
                    Log.e("Your Array Response", "Data Null");
                }
                Toast.makeText(getApplicationContext(), "Response:  " + response, Toast.LENGTH_SHORT).show();
                pd.cancel();

            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("error is ", "" + error);

                Toast.makeText(SignIn.this, ""+error.getMessage(), Toast.LENGTH_SHORT).show();
                pd.cancel();

            }
        }) {

            //Pass Your Parameters here
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("mail", "aaa@gmail.com");
                params.put("password", "123456789");

                return params;
            }
        };
        requests.setShouldCache(false);
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(requests);

    }
}
