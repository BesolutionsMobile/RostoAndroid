package com.besolutions.rosto.Scenarios.ScenarioThree.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.besolutions.rosto.R;
import com.besolutions.rosto.Scenarios.ScenarioTwo.Controller.SignIn;
import com.besolutions.rosto.local_data.send_data;

public class Me_Fragment extends Fragment {

    private View view;
    TextView txtprofile,txtpass;
    Button btnlogout;




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.me_fragment, container, false);

        txtpass = view.findViewById(R.id.txt_EditPass);
        txtprofile = view.findViewById(R.id.txt_EditProfile);
        btnlogout = view.findViewById(R.id.btnLogOut);

        btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                send_data send_data = new send_data();
                send_data.userId_check(getContext(),false);

                startActivity(new Intent(getContext(), SignIn.class));
            }
        });


        txtprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent =new Intent(getContext(),Edit_Profile.class);
                startActivity(intent);

            }
        });

        txtpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getContext(),Change_Pass.class));
            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);

    }


}
