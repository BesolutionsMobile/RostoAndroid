package com.besolutions.rosto.Scenarios.SenarioBranches.Controller;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.VolleyError;
import com.besolutions.rosto.NetworkLayar.Apicalls;
import com.besolutions.rosto.NetworkLayar.NetworkInterface;
import com.besolutions.rosto.NetworkLayar.ResponseModel;
import com.besolutions.rosto.R;
import com.besolutions.rosto.Scenarios.ScenarioMain.Pattrens.IFOnBackPressed;
import com.besolutions.rosto.Utils.Realm_adapter;
import com.besolutions.rosto.Scenarios.ScenarioHome.Controller.Home;
import com.besolutions.rosto.Scenarios.SenarioBranches.Model.Branch;
import com.besolutions.rosto.Scenarios.SenarioBranches.Model.Model_branches;
import com.besolutions.rosto.Scenarios.SenarioBranches.Pattrens.RcyMainAdapter;
import com.besolutions.rosto.Utils.TinyDB;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;

public class Branches_Fragment extends Fragment implements NetworkInterface, IFOnBackPressed {
    private View view;

    Branch[] branches;

    RecyclerView recyclerView;
    ProgressBar pgb;

    Branches_Fragment branches_fragment;
    Realm realm;
    TinyDB tinyDB;

    List<Branch> branchesList = new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.branches_fragment, container, false);

        Realm.init(getContext());
        branchesList.clear();
        check_cart();
        recyclerView = view.findViewById(R.id.recBranches);


        pgb = view.findViewById(R.id.pg);

        new Apicalls(getContext(), Branches_Fragment.this).get_all_branches();

/*
        branches_fragment.getView().setFocusableInTouchMode(true);
        branches_fragment.getView().requestFocus();
        branches_fragment.getView().setOnKeyListener( new View.OnKeyListener()
        {
            @Override
            public boolean onKey( View v, int keyCode, KeyEvent event )
            {
                if( keyCode == KeyEvent.KEYCODE_BACK )
                {
                    return true;
                }
                return false;
            }
        } );


 */
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

        pgb.setVisibility(View.GONE);

        Gson gson = new Gson();

        Model_branches model_branches = gson.fromJson(model.getResponse(), Model_branches.class);

        branches = model_branches.getBranches();

        for (int i = 0; i < branches.length; i++) {

            Branch branch = new Branch();

            branch.setAddress(branches[i].getAddress());
            branch.setName(branches[i].getName());
            branch.setPhone(branches[i].getPhone());
            branch.setImage(branches[i].getImage());
            branch.setId(branches[i].getId());


            branchesList.add(branch);

        }

        RecyclerView recyclerView = view.findViewById(R.id.recBranches);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        RcyMainAdapter adabter = new RcyMainAdapter(branchesList, getActivity());
        recyclerView.setAdapter(adabter);
    }

    @Override
    public void OnError(VolleyError error) {

        pgb.setVisibility(View.GONE);

//        ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
//        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.DISCONNECTED ||
//                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.DISCONNECTED) {
//            //we are connected to a network
//            Toast.makeText(getContext(), "Please Check Your Internet Connection", Toast.LENGTH_SHORT).show();
//        } else {
//
//            Toast.makeText(getContext(), "" + error.toString(), Toast.LENGTH_SHORT).show();
//        }

    }

    private void check_cart() {
        Realm_adapter adapter = new Realm_adapter(realm);
        if (adapter.retrieve().size() > 0) {
            FragmentTransaction fr = getActivity().getSupportFragmentManager().beginTransaction();
            fr.replace(R.id.fragment_container, new Home());
            fr.commit();
        }

    }


    @Override
    public boolean onBackPressed() {

        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        getActivity().startActivity(a);
        getActivity().finish();
        return true;
    }
}
