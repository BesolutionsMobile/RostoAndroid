package com.besolutions.rosto.Scenarios.SenarioFive.Controller;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
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
import com.besolutions.rosto.Scenarios.SenarioFive.Model.Branch;
import com.besolutions.rosto.Scenarios.SenarioFive.Model.Model_branches;
import com.besolutions.rosto.Scenarios.SenarioFive.Pattrens.RcyMainAdapter;
import com.besolutions.rosto.Utils.TinyDB;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class Branches_Fragment extends Fragment implements NetworkInterface {
    private View view;

    Branch[] branches;

    RecyclerView recyclerView;
    ProgressBar pgb;

    List<Branch> branchesList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.branches_fragment, container, false);

        recyclerView = view.findViewById(R.id.recBranches);

        pgb= view.findViewById(R.id.pg);

        new Apicalls(getContext(),Branches_Fragment.this).get_all_branches();

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

        pgb.setVisibility(View.GONE);

        Gson gson = new Gson();

        Model_branches  model_branches = gson.fromJson(model.getResponse() , Model_branches.class);

        branches = model_branches.getBranches();

        for (int i = 0 ; i < branches.length; i++)
        {

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
        RcyMainAdapter adabter = new RcyMainAdapter(branchesList,getActivity());
        recyclerView.setAdapter(adabter);
    }

    @Override
    public void OnError(VolleyError error) {

        pgb.setVisibility(View.GONE);
    }
}
