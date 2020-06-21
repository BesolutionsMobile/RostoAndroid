package com.besolutions.rosto.Scenarios.ScenarioMyOrders.Controller;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.VolleyError;
import com.besolutions.rosto.NetworkLayar.Apicalls;
import com.besolutions.rosto.NetworkLayar.NetworkInterface;
import com.besolutions.rosto.NetworkLayar.ResponseModel;
import com.besolutions.rosto.R;
import com.besolutions.rosto.Scenarios.ScenarioMyOrders.Model.ModelMyOrders;
import com.besolutions.rosto.Scenarios.ScenarioMyOrders.Model.ModelOrder;
import com.besolutions.rosto.Scenarios.ScenarioMyOrders.Model.ModelOrderDetails;
import com.besolutions.rosto.Scenarios.ScenarioMyOrders.Model.ModelOrderOrder;
import com.besolutions.rosto.Scenarios.ScenarioMyOrders.Model.ModelOrderProduct;
import com.besolutions.rosto.Scenarios.ScenarioMyOrders.Model.Model_My_Orders_Details;
import com.besolutions.rosto.Scenarios.ScenarioMyOrders.Pattrens.Rcy_My_Orders_Adapter;
import com.besolutions.rosto.Scenarios.ScenarioMyOrders.Pattrens.Rcy_My_Orders_Details_Adapter;
import com.besolutions.rosto.Utils.TinyDB;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Objects;

import es.dmoral.toasty.Toasty;

public class My_Orders_Details extends Fragment implements NetworkInterface {


    private View view;
    RecyclerView recyclerView;
    ArrayList<ModelOrderProduct> my_orders_details_list = new ArrayList<>();
    ProgressBar pg;
    TinyDB tinyDB;
    ModelOrderOrder modelOrderOrder;
    ModelOrderProduct[] orderProducts;
    int toatalprice = 0;
    TextView txtTotal;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.my_order_details, container, false);
//        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Contact us");

        recyclerView = view.findViewById(R.id.rcyMyOrderDetails);
        pg = view.findViewById(R.id.loading);
        txtTotal = view.findViewById(R.id.txtTotal);

        tinyDB = new TinyDB(getContext());

        String myOrderId = tinyDB.getString("MyOrderId");

        pg.setVisibility(View.VISIBLE);

        new Apicalls(getContext(), My_Orders_Details.this).get_my_order_details(myOrderId);

//        String[] textprice = {"50", "150", "20", "200", "170", "160", "340", "550", "20", "90", "97", "79"};
//
//        String[] textcount = {"2", "1", "4", "1", "3", "2", "3", "5", "4", "1", "3", "2"};
//
//        String[] texttype = {"متوسط", "كبير", "صغير", "متوسط", "كبير", "صغير", "متوسط", "كبير", "صغير", "متوسط", "كبير", "صغير",};
//
//        String[] texttitle = {"نص فرخة مشوية", "وجبة روستو الخطير", "روستو سندويتش", "روستو كباب + كفتة", "فرخة مشوية", "وجبة الطيار الرائع",  "روستو كباب + كفتة", "فرخة مشوية", "وجبة الطيار الرائع", "روستو كباب + كفتة", "فرخة مشوية", "وجبة الطيار الرائع"};
//
//        for (int i =0; i<textcount.length;i++){
//
//            Model_My_Orders_Details orders_details = new Model_My_Orders_Details(textprice[i],textcount[i],texttype[i],texttitle[i]);
//            my_orders_details_list.add(orders_details);
//
//        }
//
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//        Rcy_My_Orders_Details_Adapter adabter = new Rcy_My_Orders_Details_Adapter(my_orders_details_list, getContext());
//        recyclerView.setAdapter(adabter);


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
        ModelOrderDetails modelOrderDetails = gson.fromJson(model.getResponse(), ModelOrderDetails.class);
        modelOrderOrder = modelOrderDetails.getOrder();
        orderProducts = modelOrderOrder.getProducts();



        if (modelOrderDetails.getStatus() == 1) {


            for (int i =0; i<orderProducts.length; i++){

                ModelOrderProduct product = new ModelOrderProduct();

                product.setPricePerItem(orderProducts[i].getPricePerItem());
                product.setProductImage(orderProducts[i].getProductImage());
                product.setProductName(orderProducts[i].getProductName());
                product.setQuantity(orderProducts[i].getQuantity());
                product.setSize(orderProducts[i].getSize());

                int price = Integer.parseInt(orderProducts[i].getPricePerItem());

                toatalprice += price;

                my_orders_details_list.add(product);

            }

            txtTotal.setText("" + toatalprice);

        } else if (modelOrderDetails.getStatus() == 3) {


            Toasty.error(Objects.requireNonNull(getContext()), "لا توجد طلبات سابقة ...", Toast.LENGTH_LONG).show();
        } else {

            Toasty.error(Objects.requireNonNull(getContext()), ""+modelOrderDetails.getStatus(), Toast.LENGTH_LONG).show();

        }


        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        Rcy_My_Orders_Details_Adapter adabter = new Rcy_My_Orders_Details_Adapter(my_orders_details_list, getContext());
        recyclerView.setAdapter(adabter);

    }

    @Override
    public void OnError(VolleyError error) {
        pg.setVisibility(View.GONE);

    }
}
