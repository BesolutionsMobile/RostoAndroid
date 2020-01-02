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
import com.besolutions.rosto.Scenarios.ScenarioThree.Model.Model_View_Profile;
import com.besolutions.rosto.Scenarios.ScenarioThree.Model.Question;
import com.besolutions.rosto.local_data.saved_data;
import com.besolutions.rosto.local_data.send_data;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.gson.Gson;

import es.dmoral.toasty.Toasty;

public class Edit_Profile extends AppCompatActivity implements NetworkInterface {

    EditText editname, editmail, editphone;
    Button btnedit;
    ProgressBar pg;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit__profile);

        editname = findViewById(R.id.editUserNmeProfile);
        editmail = findViewById(R.id.editEmailProfile);
        editphone = findViewById(R.id.editPhoneProfile);
        btnedit = findViewById(R.id.btnEditProfile);
        pg = findViewById(R.id.loading);
        final String user_id = saved_data.get_user_id(this);

        //Toast.makeText(this, ""+saved_data.get_user_id(this), Toast.LENGTH_SHORT).show();
        new Apicalls(Edit_Profile.this, Edit_Profile.this).view_profile(user_id);


        btnedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (editname.getText().toString().equals("")) {
                    editname.setError("من فضلك ادخل الاسم !");
                    YoYo.with(Techniques.Flash)
                            .duration(800)
                            .repeat(1)
                            .playOn(findViewById(R.id.editUserNmeProfile));

                    Toasty.error(Edit_Profile.this, "من فضلك ادخل الاسم!", Toast.LENGTH_SHORT).show();

                } else if (editmail.getText().toString().equals("")) {

                    editmail.setError("من فضلك ادخل البريد الالكتروني !");
                    YoYo.with(Techniques.Flash)
                            .duration(800)
                            .repeat(1)
                            .playOn(findViewById(R.id.editEmailProfile));

                    Toasty.error(Edit_Profile.this, "من فضلك ادخل البريد الالكتروني!", Toast.LENGTH_SHORT).show();


                } else if (editphone.getText().toString().equals("")) {
                    editphone.setError("من فضلك ادخل الرقم السري !");

                    YoYo.with(Techniques.Flash)
                            .duration(800)
                            .repeat(1)
                            .playOn(findViewById(R.id.editPhoneProfile));

                    Toasty.error(Edit_Profile.this, "من فضلك ادخل الرقم السري!", Toast.LENGTH_SHORT).show();

                }else {

                    pg.setVisibility(View.VISIBLE);
                    new Apicalls(Edit_Profile.this, Edit_Profile.this).edit_profile(editname.getText().toString(), editmail.getText().toString(), editphone.getText().toString(), user_id);

                }
            }
        });

    }

    @Override
    public void OnStart() {

    }

    @Override
    public void OnResponse(ResponseModel model) {

        pg.setVisibility(View.GONE);
        switch (model.getResponseCode()) {

            case 7:

                Gson gson1 = new Gson();

                Model_View_Profile modelViewProfile = gson1.fromJson(model.getResponse(),Model_View_Profile.class);

                if (modelViewProfile.getUser().getMail() == null)
                {

                }else if (modelViewProfile.getStatus()==1)
                {
                    editname.setText(modelViewProfile.getUser().getName());
                    editphone.setText(modelViewProfile.getUser().getPhone());
                    editmail.setText(modelViewProfile.getUser().getMail());

                    send_data send_data = new send_data();

                    send_data.SET_USER_NAME(this,modelViewProfile.getUser().getName());
                    send_data.SET_USER_EMAIL(this,modelViewProfile.getUser().getMail());
                    send_data.SET_USER_PHONE(this,modelViewProfile.getUser().getPhone());
                    send_data.SET_USER_ID(this,modelViewProfile.getUser().getId());

                }else if (modelViewProfile.getStatus()==2)
                {
                    Toasty.error(this, ""+modelViewProfile.getStatus(), Toast.LENGTH_SHORT).show();

                }else if (modelViewProfile.getStatus()==3)
                {
                    Toasty.error(this, "" + modelViewProfile.getStatus(), Toast.LENGTH_SHORT).show();
                }



            case 8:
                Gson gson = new Gson();
                Model_Edit_Profile edit_profile = gson.fromJson(model.getResponse(), Model_Edit_Profile.class);
                if (edit_profile.getMessage() == null)
                {

                }else if (edit_profile.getStatus() == 1) {

                    Toasty.success(this,"تم تحديث البيانات الشخصية بنجاح.", Toast.LENGTH_SHORT).show();

                } else if (edit_profile.getStatus() == 2) {


                    YoYo.with(Techniques.Flash)
                            .duration(800)
                            .repeat(1)
                            .playOn(findViewById(R.id.editEmailProfile));

                    YoYo.with(Techniques.Flash)
                            .duration(800)
                            .repeat(1)
                            .playOn(findViewById(R.id.editPhoneProfile));

                    editmail.setError("من فضلك راجع البريد الاكتروني الخاصة بك.");

                    editphone.setError("رقم الهاتف مستخدم مسبقا.");


                    Toasty.error(this, "خطا في البريد الالكتروني او رقم الهاتف.", Toast.LENGTH_SHORT).show();

                } else if (edit_profile.getStatus() == 3) {

                    Toasty.error(this, ""+edit_profile.getMessage(), Toast.LENGTH_SHORT).show();
                }

        }
    }

    @Override
    public void OnError(VolleyError error) {

        pg.setVisibility(View.GONE);

        Toasty.error(this, "" + error.toString(), Toast.LENGTH_SHORT).show();

    }
}
