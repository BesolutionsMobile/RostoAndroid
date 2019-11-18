package com.besolutions.rosto.Scenarios.ScenarioThree.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.besolutions.rosto.R;

public class Me_Fragment extends Fragment {

    private View view;
    TextView txtprofile,txtpass;




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.me_fragment, container, false);

        txtpass = view.findViewById(R.id.txt_EditPass);
        txtprofile = view.findViewById(R.id.txt_EditProfile);

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
