package com.besolutions.rosto.Scenarios.ScenarioMyOrders.Controller;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.VolleyError;
import com.besolutions.rosto.MyProgressDialog;
import com.besolutions.rosto.NetworkLayar.Apicalls;
import com.besolutions.rosto.NetworkLayar.NetworkInterface;
import com.besolutions.rosto.NetworkLayar.ResponseModel;
import com.besolutions.rosto.R;
import com.besolutions.rosto.Scenarios.ScenarioMain.Controller.MainActivity;
import com.besolutions.rosto.Scenarios.ScenarioMain.Pattrens.IFOnBackPressed;
import com.besolutions.rosto.Scenarios.ScenarioMyOrders.Model.ModelMyOrders;
import com.besolutions.rosto.Scenarios.ScenarioMyOrders.Model.ModelOrder;
import com.besolutions.rosto.Scenarios.ScenarioMyOrders.Pattrens.Rcy_My_Orders_Adapter;
import com.besolutions.rosto.Scenarios.ScenarioProduct.Model.Model_Product;
import com.besolutions.rosto.Scenarios.ScenarioProduct.Model.Productes;
import com.besolutions.rosto.Scenarios.SenarioBranches.Controller.Branches_Fragment;
import com.besolutions.rosto.Utils.MyUtilFile;
import com.besolutions.rosto.local_data.saved_data;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Objects;

import es.dmoral.toasty.Toasty;

public class My_Orders extends Fragment implements NetworkInterface, IFOnBackPressed {


    private View view;
    RecyclerView recyclerView;
    ArrayList<ModelOrder> orderArrayList = new ArrayList<>();
    ProgressBar pg;
    ModelOrder[] orders;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.my_orders_fragment, container, false);
//        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Contact us");

        recyclerView = view.findViewById(R.id.rcyMyOrders);
        pg = view.findViewById(R.id.loading);


        String user_id = saved_data.get_user_id(Objects.requireNonNull(getContext()));

        pg.setVisibility(View.VISIBLE);

        new MyUtilFile(getContext()).showMessage(user_id);

        new Apicalls(getContext(), My_Orders.this).get_my_order(user_id);


//        String[] textcode = {"#34657658", "#25669545", "#56556657", "#52655526", "#9936695", "#987874559", "#546848899", "#484864684", "#6877687886", "#576878787", "#56787687", "#54799797"};
//
//        String[] textdate = {"21 فبراير 2020", "22 فبراير 2020", "23 فبراير 2020", "27 فبراير 2020", "26 فبراير 2020", "29 فبراير 2020", "27 فبراير 2020", "26 فبراير 2020", "29 فبراير 2020", "27 فبراير 2020", "26 فبراير 2020", "29 فبراير 2020"};
//
//        String[] textnumbers = {"13", "16", "20", "45", "96", "55", "25", "36", "25", "25", "36", "25"};
//
//        for (int i = 0; i < textcode.length; i++) {
//
//            My_Orders_Model my_orders_model = new My_Orders_Model(textnumbers[i], textcode[i], textdate[i]);
//            orderArrayList.add(my_orders_model);
//
//        }
//
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//        Rcy_My_Orders_Adapter adabter = new Rcy_My_Orders_Adapter(orderArrayList, getContext());
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
        ModelMyOrders modelMyOrders = gson.fromJson(model.getResponse(), ModelMyOrders.class);
        orders = modelMyOrders.getOrders();

        if (modelMyOrders.getStatus() == 1) {

            for (int i = 0; i < orders.length; i++) {

                ModelOrder modelOrder = new ModelOrder();

                modelOrder.setAddress(orders[i].getAddress());
                modelOrder.setBuilding(orders[i].getBuilding());
                modelOrder.setCreatedAt(orders[i].getCreatedAt());
                modelOrder.setCreatedBy(orders[i].getCreatedBy());
                modelOrder.setDeleted(orders[i].getDeleted());
                modelOrder.setFlat(orders[i].getFlat());
                modelOrder.setFloor(orders[i].getFloor());
                modelOrder.setId(orders[i].getId());
                modelOrder.setIdBranch(orders[i].getIdBranch());
                modelOrder.setIdCustomer(orders[i].getIdCustomer());
                modelOrder.setIdPaymentWay(orders[i].getIdPaymentWay());
                modelOrder.setLastUpdatedBy(orders[i].getLastUpdatedBy());
                modelOrder.setLastUpdatedBy(orders[i].getLastUpdatedBy());
                modelOrder.setLatitude(orders[i].getLatitude());
                modelOrder.setLongitude(orders[i].getLongitude());
                modelOrder.setMobile(orders[i].getMobile());
                modelOrder.setName(orders[i].getName());
                modelOrder.setNotes(orders[i].getNotes());
                modelOrder.setShipping(orders[i].getShipping());
                modelOrder.setStatus(orders[i].getStatus());
                modelOrder.setStreet(orders[i].getStreet());
                modelOrder.setTotalPrice(orders[i].getTotalPrice());
                modelOrder.setTotalWithShipping(orders[i].getTotalWithShipping());
                modelOrder.setUpdatedAt(orders[i].getUpdatedAt());

                orderArrayList.add(modelOrder);
            }

        } else if (modelMyOrders.getStatus() == 3) {


            Toasty.error(Objects.requireNonNull(getContext()), "لا توجد طلبات سابقة ...", Toast.LENGTH_LONG).show();
        } else {

            Toasty.error(Objects.requireNonNull(getContext()), ""+modelMyOrders.getStatus(), Toast.LENGTH_LONG).show();

        }

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        Rcy_My_Orders_Adapter adabter = new Rcy_My_Orders_Adapter(orderArrayList, getContext());
        recyclerView.setAdapter(adabter);


    }

    @Override
    public void OnError(VolleyError error) {

        pg.setVisibility(View.GONE);

    }

    @Override
    public boolean onBackPressed() {
        MainActivity.navigation.setSelectedItemId(R.id.branches);
        FragmentTransaction fr = getActivity().getSupportFragmentManager().beginTransaction();
        fr.replace(R.id.fragment_container, new Branches_Fragment());
        fr.commit();
        return true;
    }
}
