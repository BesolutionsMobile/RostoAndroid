package com.besolutions.rosto.Scenarios.ScenarioTwo.Controller;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.icu.text.MessagePattern;
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
import com.besolutions.rosto.Scenarios.ScenarioThree.Controller.Edit_Profile;
import com.besolutions.rosto.Scenarios.ScenarioTwo.Model.Model_SignUp;
import com.besolutions.rosto.local_data.send_data;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.gson.Gson;

import es.dmoral.toasty.Toasty;

public class Sign_Up extends AppCompatActivity implements NetworkInterface {

    EditText editname, editemail, editpass, editphone;
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

                if (editname.getText().toString().equals("")) {
                    editname.setError("من فضلك ادخل الاسم !");

                    YoYo.with(Techniques.Flash)
                            .duration(800)
                            .repeat(1)
                            .playOn(findViewById(R.id.editName));

                    Toasty.error(Sign_Up.this, "من فضلك ادخل الاسم !", Toast.LENGTH_SHORT).show();


                } else if (editemail.getText().toString().equals("")) {

                    editemail.setError("من فضلك ادخل االبريد الالكتروني !");
                    YoYo.with(Techniques.Flash)
                            .duration(800)
                            .repeat(1)
                            .playOn(findViewById(R.id.edit_Email));

                    Toasty.error(Sign_Up.this, "من فضلك ادخل االبريد الالكتروني !", Toast.LENGTH_SHORT).show();


                } else if (editpass.getText().toString().equals("")) {
                    editpass.setError("من فضلك ادخل الرقم السري !");

                    YoYo.with(Techniques.Flash)
                            .duration(800)
                            .repeat(1)
                            .playOn(findViewById(R.id.edit_pass));

                    Toasty.error(Sign_Up.this, "من فضلك ادخل الرقم السري !", Toast.LENGTH_SHORT).show();


                } else if (editphone.getText().toString().equals("")) {

                    editphone.setError("من فضلك ادخل رقم الهاتف !");
                    YoYo.with(Techniques.Flash)
                            .duration(800)
                            .repeat(1)
                            .playOn(findViewById(R.id.editPhone));

                    Toasty.error(Sign_Up.this, "من فضلك ادخل رقم الهاتف !", Toast.LENGTH_SHORT).show();


                } else {

                    pg = new ProgressDialog(Sign_Up.this);
                    pg.setMessage("جاري التحميل....");
                    pg.show();

                    new Apicalls(Sign_Up.this, Sign_Up.this).registerUser(editname.getText().toString(), editemail.getText().toString(), editphone.getText().toString(), editpass.getText().toString());


                }


            }
        });


    }

    @Override
    public void OnStart() {

    }

    @Override
    public void OnResponse(ResponseModel model) {

        pg.cancel();
        Gson gson = new Gson();

        Model_SignUp signUp = gson.fromJson(model.getResponse(), Model_SignUp.class);

        send_data send_data = new send_data();
        send_data.SET_USER_ID(this, signUp.getIdUser());

        if (signUp.getStatus() == 1) {

            Toasty.success(this, "تم تسجيل دخولك بنجاح ", Toast.LENGTH_SHORT).show();

            startActivity(new Intent(Sign_Up.this, MainActivity.class));
            finish();

        } else if (signUp.getStatus() == 2) {

            YoYo.with(Techniques.Flash)
                    .duration(800)
                    .repeat(1)
                    .playOn(findViewById(R.id.editName));

            YoYo.with(Techniques.Flash)
                    .duration(800)
                    .repeat(1)
                    .playOn(findViewById(R.id.edit_Email));

            YoYo.with(Techniques.Flash)
                    .duration(800)
                    .repeat(1)
                    .playOn(findViewById(R.id.edit_pass));

            YoYo.with(Techniques.Flash)
                    .duration(800)
                    .repeat(1)
                    .playOn(findViewById(R.id.editPhone));


            editemail.setError(" نأسف ...البريد الالكتروني مسجل مسبقا. ");


            Toasty.error(this, "" + signUp.getMessage(), Toast.LENGTH_SHORT).show();

        } else
            Toasty.error(this, "" + signUp.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void OnError(VolleyError error) {

        Toasty.error(this, "" + error.toString(), Toast.LENGTH_SHORT).show();

        pg.cancel();
    }
}
