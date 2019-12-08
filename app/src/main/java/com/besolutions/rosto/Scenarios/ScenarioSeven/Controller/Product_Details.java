package com.besolutions.rosto.Scenarios.ScenarioSeven.Controller;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.besolutions.rosto.NetworkLayar.Apicalls;
import com.besolutions.rosto.NetworkLayar.NetworkInterface;
import com.besolutions.rosto.NetworkLayar.ResponseModel;
import com.besolutions.rosto.R;
import com.besolutions.rosto.Scenarios.ScenarioSeven.Model.Cart_Model;
import com.besolutions.rosto.Scenarios.ScenarioSeven.Model.Model_Product_Details;
import com.besolutions.rosto.Scenarios.ScenarioSeven.Model.Price_Details;
import com.besolutions.rosto.Utils.Realm_adapter;
import com.besolutions.rosto.local_data.saved_data;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.util.ArrayList;

import io.realm.Realm;

public class Product_Details extends Fragment implements NetworkInterface {

    Price_Details[] price_details;
    TextView txtname, txtdescription, txtdecrese, txtincrease, txtnumber, txtprice;
    ImageView imgdeatails;
    Button btnaddtocart;
    Spinner spinner;
    ProgressBar pg;
    Realm realm;
    String size;
    String size_id;
    int num = 1;
    int totalPrice1;
    private View view;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_product_details, container, false);

        realm = Realm.getDefaultInstance();


        txtname = view.findViewById(R.id.txtProductDetailsName);
        txtdecrese = view.findViewById(R.id.txtProductDetailsDecrease);
        txtdescription = view.findViewById(R.id.txtProductDetailsDescription);
        txtincrease = view.findViewById(R.id.txtProductDetailsIncrease);
        txtnumber = view.findViewById(R.id.txtProductDetailsNumber);
        txtprice = view.findViewById(R.id.txtProductDetailsPrice);
        imgdeatails = view.findViewById(R.id.imgProductDetails);
        spinner = view.findViewById(R.id.spinnerProductDetails);
        pg = view.findViewById(R.id.loading);
        btnaddtocart = view.findViewById(R.id.btnProductDetailsAddCart);


        final saved_data saved_data = new saved_data();
        String product_id = saved_data.get_product_id(getContext());

        new Apicalls(getContext(), Product_Details.this).get_product_details(product_id);

        btnaddtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String product_id = saved_data.get_product_id(getContext());
                Cart_Model c = new Cart_Model();
                c.setTxtname(txtname.getText().toString());
                c.setTxtprice(txtprice.getText().toString());
                c.setTxtquntity(txtnumber.getText().toString());
                c.setIdproduct(product_id);
                c.setTxtsize(size);
                c.setSize_id(size_id);

                Realm_adapter adapter = new Realm_adapter(realm);

                adapter.save(c);
/*
                Orders_Fragment orders_fragment = new Orders_Fragment();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container,orders_fragment);
                transaction.commit();


 */
                String price = "";
                Intent in = new Intent("delete_action");
                in.putExtra("category", price);
                getContext().sendBroadcast(in);

                //Intent intent = new Intent(getContext(), MainActivity.class);
                //startActivity(intent);
                //FragmentTransaction fr = ((AppCompatActivity)context).getSupportFragmentManager().beginTransaction();
                //fr.replace(R.id.fragment_container,new Orders_Fragment());
                //fr.commit();

            }

        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }


    @Override
    public void OnStart() {

    }

    @Override
    public void OnResponse(ResponseModel model) {

        pg.setVisibility(View.GONE);
        Gson gson = new Gson();
        Model_Product_Details modelProductDetails = gson.fromJson(model.getResponse(), Model_Product_Details.class);

        price_details = modelProductDetails.getPrices();
        txtdescription.setText(modelProductDetails.getProduct().getDescription());
        txtname.setText(modelProductDetails.getProduct().getName());
        Glide.with(this)
                .load(modelProductDetails.getProduct().getImage())
                .placeholder(R.drawable.rostologo)
                .into(imgdeatails);



        ArrayList<String> adapterList = new ArrayList<String>();
        for (int i = 0; i < price_details.length; i++) {

            adapterList.add(price_details[i].getSize());

        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, adapterList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                int price = Integer.parseInt(price_details[position].getPrice());
                int selectrd = num * price;

                txtprice.setText(""+selectrd);
                size = price_details[position].getSize();
                size_id = price_details[position].getIdSize();
                totalPrice1 = Integer.parseInt(price_details[position].getPrice());


                txtincrease.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        num = Integer.parseInt(txtnumber.getText().toString());
                        num++;
                        if (num < 30) {
                            txtnumber.setText("" + num);
                            txtprice.setText("" + num * totalPrice1);

                        } else if (num > 30) {
                            num = 30;
                            txtnumber.setText("" + num);
                            txtprice.setText("" + num * totalPrice1);

                        }


                    }
                });

                txtdecrese.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        num = Integer.parseInt(txtnumber.getText().toString());
                        num--;
                        if (num >= 1) {
                            txtnumber.setText("" + num);
                            txtprice.setText("" + num * totalPrice1);

                        } else if (num <= 0) {
                            num = 1;
                            txtnumber.setText("" + num);
                            txtprice.setText("" + num * totalPrice1);
                        }
                    }
                });

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

                txtprice.setText(price_details[0].getPrice());
                final int toatalPrice = Integer.parseInt(txtprice.getText().toString());


                txtincrease.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        num = Integer.parseInt(txtnumber.getText().toString());
                        num++;
                        if (num < 30) {
                            txtnumber.setText("" + num);
                            txtprice.setText("" + num * toatalPrice);

                        } else if (num > 30) {
                            num = 30;
                            txtnumber.setText("" + num);
                            txtprice.setText("" + num * toatalPrice);

                        }


                    }
                });

                txtdecrese.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        num = Integer.parseInt(txtnumber.getText().toString());
                        num--;
                        if (num >= 1) {
                            txtnumber.setText("" + num);
                            txtprice.setText("" + num * toatalPrice);

                        } else if (num <= 0) {
                            num = 1;
                            txtnumber.setText("" + num);
                            txtprice.setText("" + num * toatalPrice);
                        }
                    }
                });


            }
        });



    }

    @Override
    public void OnError(VolleyError error) {

        pg.setVisibility(View.GONE);
        Toast.makeText(getContext(), "" + error.toString(), Toast.LENGTH_SHORT).show();

    }
}
