package com.besolutions.rosto.Scenarios.ScenarioFour.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.VolleyError;
import com.besolutions.rosto.NetworkLayar.Apicalls;
import com.besolutions.rosto.NetworkLayar.NetworkInterface;
import com.besolutions.rosto.NetworkLayar.ResponseModel;
import com.besolutions.rosto.R;
import com.besolutions.rosto.Scenarios.ScenarioFour.Model.Model_Cart;
import com.besolutions.rosto.Scenarios.ScenarioSeven.Model.Cart_Model;
import com.besolutions.rosto.Utils.Realm_adapter;
import com.besolutions.rosto.local_data.saved_data;
import com.google.gson.Gson;

import java.util.ArrayList;

import io.realm.Realm;

public class Order_Information extends Fragment implements NetworkInterface {


    private View view;
    Realm realm;
    ProgressBar pg;
    Button btnfinish;
    EditText editname, editarea, editstreet, editphone, editnotes, editbuilding, editfloor, editflat;
    String id_user, id_branches;
    String a = "";


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.order_information, container, false);
        realm = Realm.getDefaultInstance();

        pg = view.findViewById(R.id.loading);
        pg.setVisibility(View.GONE);
        btnfinish = view.findViewById(R.id.btnOrderInformationFinish);
        editname = view.findViewById(R.id.editOrderInformationName);
        editarea = view.findViewById(R.id.editOrderInformationArea);
        editflat = view.findViewById(R.id.editOrderInformationApartment);
        editbuilding = view.findViewById(R.id.editOrderInformationBuilding);
        editfloor = view.findViewById(R.id.editOrderInformationFloor);
        editnotes = view.findViewById(R.id.editOrderInformationNotes);
        editphone = view.findViewById(R.id.editOrderInformationPhoneNumber);
        editstreet = view.findViewById(R.id.editOrderInformationStreet);

        saved_data saved_data= new saved_data();
        id_user  = saved_data.get_user_id(getContext());
        id_branches = saved_data.get_Branch_id(getContext());


        btnfinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Realm_adapter adapter = new Realm_adapter(realm);
                ArrayList<Cart_Model> cartModel = adapter.retrieve();

                a = "";
                for (int i = 0; i <adapter.retrieve().size(); i++)
                {

                    if (i+1 == adapter.retrieve().size())
                    {

                        a += "["+cartModel.get(i).getIdproduct().toString()+","+cartModel.get(i).getTxtquntity().toString()+","+cartModel.get(i).getTxtprice().toString()+","+cartModel.get(i).getSize_id().toString()+"]";

                    }else
                    {

                        a += "[" +cartModel.get(i).getIdproduct().toString()+ "," +cartModel.get(i).getTxtquntity().toString()+ "," +cartModel.get(i).getTxtprice().toString()+ "," +cartModel.get(i).getSize_id().toString()+ "]" + ",";

                    }

                }
                Log.e("allalalalala",""+"["+a+"]");

                new Apicalls(getContext(),Order_Information.this).sendOrder(editname.getText().toString(),editstreet.getText().toString(),editbuilding.getText().toString(),editfloor.getText().toString(),editflat.getText().toString(),editphone.getText().toString(),"0.0","0.0",editnotes.getText().toString(),id_user,id_branches,"["+a+"]");
                pg.setVisibility(View.VISIBLE);

            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void OnStart() {

    }

    @Override
    public void OnResponse(ResponseModel model) {

        pg.setVisibility(View.GONE);
        Realm_adapter realmAdapter = new Realm_adapter(realm);
        Gson gson = new Gson();
        Model_Cart model_cart = gson.fromJson(model.getResponse(),Model_Cart.class);

        Toast.makeText(getContext(), ""+model_cart.getMessage(), Toast.LENGTH_SHORT).show();

        realmAdapter.delete_all();

        String price = "";
        Intent in = new Intent("send_order_action");
        in.putExtra("category", price);
        getContext().sendBroadcast(in);
    }

    @Override
    public void OnError(VolleyError error) {
        pg.setVisibility(View.GONE);
        Toast.makeText(getContext(), ""+error.toString(), Toast.LENGTH_SHORT).show();

    }
}
