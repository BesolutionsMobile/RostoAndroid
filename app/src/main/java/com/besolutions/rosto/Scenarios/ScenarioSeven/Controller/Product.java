package com.besolutions.rosto.Scenarios.ScenarioSeven.Controller;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.besolutions.rosto.NetworkLayar.Apicalls;
import com.besolutions.rosto.NetworkLayar.NetworkInterface;
import com.besolutions.rosto.NetworkLayar.ResponseModel;
import com.besolutions.rosto.R;
import com.besolutions.rosto.Scenarios.ScenarioSeven.Model.Model_Product;
import com.besolutions.rosto.Scenarios.ScenarioSeven.Model.Productes;
import com.besolutions.rosto.Scenarios.ScenarioSeven.Pattrens.RcyProductAdapter;
import com.besolutions.rosto.Scenarios.SenarioFive.Pattrens.RcyMainAdapter;
import com.besolutions.rosto.Utils.TinyDB;
import com.besolutions.rosto.local_data.saved_data;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class Product extends Fragment implements NetworkInterface {

    TinyDB tinyDB;

    Productes[] productes;
    List<Productes> productesList = new ArrayList<>();
    RecyclerView recyclerView;
    ProgressBar pg;
    private View view;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.activity_product, container, false);
        recyclerView = view.findViewById(R.id.recProduct);
        pg = view.findViewById(R.id.loading);
        productesList.clear();
        tinyDB = new TinyDB(getContext());
        saved_data saved_data = new saved_data();
        String id = saved_data.get_Branch_id(getContext());

        //Intent intent = getActivity().getIntent();
        //String id_home = intent.getStringExtra("id_home");
        String id_home = tinyDB.getString("id_home");

        new Apicalls(getContext(), Product.this).get_product_by_category_id_branch_id(id_home, id);
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

        Gson gson = new Gson();

        Model_Product model_product = gson.fromJson(model.getResponse(), Model_Product.class);

        productes = model_product.getProductes();

        if (model_product.getStatus()== 1) {
            for (int i = 0; i < productes.length; i++) {
                Productes products = new Productes();

                products.setName(productes[i].getName());
                products.setPrice(productes[i].getPrice());
                products.setImage(productes[i].getImage());
                products.setProductId(productes[i].getProductId());

                productesList.add(products);
            }
        }else if (model_product.getStatus() == 3)
        {

            Toast.makeText(getContext(), " لا توجد بيانات..", Toast.LENGTH_SHORT).show();
        }else
        {
            Toast.makeText(getContext(), ""+model_product.getStatus() , Toast.LENGTH_SHORT).show();
        }

        RecyclerView recyclerView = view.findViewById(R.id.recProduct);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        RcyProductAdapter adabter = new RcyProductAdapter(productesList,getContext());
        recyclerView.setAdapter(adabter);

    }

    @Override
    public void OnError(VolleyError error) {

        pg.setVisibility(View.GONE);

        Toast.makeText(getContext(), "" + error.toString(), Toast.LENGTH_SHORT).show();

    }
}
