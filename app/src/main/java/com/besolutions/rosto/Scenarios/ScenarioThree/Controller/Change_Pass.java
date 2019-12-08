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
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.gson.Gson;

import es.dmoral.toasty.Toasty;

public class Change_Pass extends AppCompatActivity implements NetworkInterface {

    EditText editoldpass, editnewpass, editconfirmnewpass;
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
        final String id_user = saved_data.get_user_id(this);

        btnchangepass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (editoldpass.getText().toString().equals("")) {
                    YoYo.with(Techniques.Flash)
                            .duration(800)
                            .repeat(1)
                            .playOn(findViewById(R.id.editOldPassword));

                    editoldpass.setError("من فضلك ادخل كلمة المرور القديمة!");
                    Toasty.error(Change_Pass.this, "من فضلك ادخل كلمة المرور القديمة!", Toast.LENGTH_SHORT).show();

                } else if (editnewpass.getText().toString().equals("")) {
                    YoYo.with(Techniques.Flash)
                            .duration(800)
                            .repeat(1)
                            .playOn(findViewById(R.id.editNewPassword));

                    editnewpass.setError("من فضلك ادخل كلمة المرور الجديدة!");

                    Toasty.error(Change_Pass.this, "من فضلك ادخل كلمة المرور الجديدة!", Toast.LENGTH_SHORT).show();


                } else if (editnewpass.getText().toString().length() <= 6) {

                    YoYo.with(Techniques.Flash)
                            .duration(800)
                            .repeat(1)
                            .playOn(findViewById(R.id.editNewPassword));

                    editnewpass.setError("كلمة السر قصيرة!");
                    Toasty.error(Change_Pass.this, "لا يمكن ان تكون كلمة السر اقل من 6 حروف او ارقام!", Toast.LENGTH_SHORT).show();


                } else if (editconfirmnewpass.getText().toString().equals("")) {

                    YoYo.with(Techniques.Flash)
                            .duration(800)
                            .repeat(1)
                            .playOn(findViewById(R.id.editConfirmNewPassword));

                    editconfirmnewpass.setError("من فضلك اعد ادخال كلمة السر!");
                    Toasty.error(Change_Pass.this, "من فضلك اعد ادخال كلمة السر!", Toast.LENGTH_SHORT).show();


                } else if (!editnewpass.getText().toString().equals(editconfirmnewpass.getText().toString())) {
                    YoYo.with(Techniques.Flash)
                            .duration(800)
                            .repeat(1)
                            .playOn(findViewById(R.id.editNewPassword));

                    YoYo.with(Techniques.Flash)
                            .duration(800)
                            .repeat(1)
                            .playOn(findViewById(R.id.editConfirmNewPassword));

                    editnewpass.setError("كلمة السر غير متطابقه!");
                    editconfirmnewpass.setError("كلمة السر غير متطابقه!");

                    Toasty.error(Change_Pass.this, "كلمة السر غير متطابقه!", Toast.LENGTH_SHORT).show();

                } else {

                    pg.setVisibility(View.VISIBLE);
                    new Apicalls(Change_Pass.this, Change_Pass.this).change_password(editoldpass.getText().toString(), editnewpass.getText().toString(), editconfirmnewpass.getText().toString(), id_user);
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

        Gson gson = new Gson();

        Model_Edit_Profile modelEditProfile = gson.fromJson(model.getResponse(), Model_Edit_Profile.class);

        if (modelEditProfile.getStatus() == 1) {

            Toasty.success(this, "تم تغيير كلمة السر بنجاح", Toast.LENGTH_SHORT).show();


        } else if (modelEditProfile.getStatus() == 2) {
            YoYo.with(Techniques.Flash)
                    .duration(800)
                    .repeat(1)
                    .playOn(findViewById(R.id.editOldPassword));

            editoldpass.setError("كلمة السر التي ادخلتها غير صحيحة من فضلك حاول مرة اخري");

            Toasty.error(this, "كلمة السر التي ادخلتها غير صحيحة من فضلك حاول مرة اخري", Toast.LENGTH_SHORT).show();


        } else if (modelEditProfile.getStatus() == 3) {
            Toasty.error(this, ""+ modelEditProfile.getMessage(), Toast.LENGTH_SHORT).show();

        }


    }

    @Override
    public void OnError(VolleyError error) {
        pg.setVisibility(View.GONE);

        Toast.makeText(this, "" + error.toString(), Toast.LENGTH_SHORT).show();

    }
}
