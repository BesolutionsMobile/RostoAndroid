package com.besolutions.rosto.Scenarios.ScenarioFour.Controller;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.besolutions.rosto.R;
import com.besolutions.rosto.Scenarios.ScenarioSeven.Model.Cart_Model;
import com.besolutions.rosto.Scenarios.ScenarioSeven.Pattrens.RcyCartAdapter;
import com.besolutions.rosto.Scenarios.ScenarioSeven.Pattrens.Realm_adapter;
import com.besolutions.rosto.Scenarios.SenarioFive.Controller.Branches_Fragment;

import java.util.ArrayList;

import io.realm.Realm;

public class Orders_Fragment extends Fragment {

    private View view;
    Realm realm;
    ImageView imgdelete;
    RecyclerView recyclerView;
    ArrayList<Cart_Model> cartModels = new ArrayList<>();
    TextView total_price;
    Button btncontinueorder,btnaddorder;
    static int delet_Price ;
    static int total=0;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.ordes_fragment, container, false);

        Realm.init(getContext());

        Realm_adapter adapter = new Realm_adapter(realm);

        total_price = view.findViewById(R.id.txtCartTotalPrice);
        btncontinueorder = view.findViewById(R.id.btnCartContinueOrder);
        btnaddorder = view.findViewById(R.id.btnCartAddOrder);
        total_price.setText(""+count_price());

        btnaddorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentTransaction fr = getActivity().getSupportFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_container,new Branches_Fragment());
                fr.commit();

            }
        });

        btncontinueorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentTransaction fr = getActivity().getSupportFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_container,new Order_Information());
                fr.commit();

            }
        });

        cartModels = adapter.retrieve();

        recyclerView =view.findViewById(R.id.recCart);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        RcyCartAdapter adabter = new RcyCartAdapter(cartModels,getContext());
        recyclerView.setAdapter(adabter);




        return view;
    }

    @Override
    public void onStart() {
        getActivity().registerReceiver(mReceiverLocation,new IntentFilter("delete_action"));

        super.onStart();
    }

    @Override
    public void onStop() {
        getActivity().unregisterReceiver(mReceiverLocation);
        super.onStop();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);

    }

    private int count_price()
    {
        int one_price = 0;
        Realm_adapter realmAdapter = new Realm_adapter(realm);
        cartModels = realmAdapter.retrieve();

        //SET VIEW GONE
        if (cartModels.size()==0)
        {
           LinearLayout linearLayout = view.findViewById(R.id.linearRecyclerView);
           linearLayout.setVisibility(View.GONE);

           Button btncontinueorder = view.findViewById(R.id.btnCartContinueOrder);
           btncontinueorder.setVisibility(View.GONE);

           Orders_Fragment orders_fragment = new Orders_Fragment();


        }
        for (int i=0; i<realmAdapter.retrieve().size(); i++)
        {
            int price = Integer.parseInt(cartModels.get(i).getTxtprice().toString());
            one_price += price;
        }
       /*
        Orders_Fragment orders_fragment = (Orders_Fragment) getFragmentManager().findFragmentById(R.id.fragment_container);
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.detach(orders_fragment);
        fragmentTransaction.attach(orders_fragment);
        fragmentTransaction.commit();
        */


        return one_price;
    }
    private BroadcastReceiver mReceiverLocation = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            try {
                //String deletedPrice = String.valueOf(intent.getSerializableExtra("category"));
               // int deletes_price=count_price()-Integer.parseInt(deletedPrice);

                total_price.setText(""+count_price());

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };
}
