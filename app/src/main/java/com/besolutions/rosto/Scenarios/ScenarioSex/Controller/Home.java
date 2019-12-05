package com.besolutions.rosto.Scenarios.ScenarioSex.Controller;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.android.volley.VolleyError;
import com.besolutions.rosto.NetworkLayar.Apicalls;
import com.besolutions.rosto.NetworkLayar.NetworkInterface;
import com.besolutions.rosto.NetworkLayar.ResponseModel;
import com.besolutions.rosto.R;
import com.besolutions.rosto.Scenarios.ScenarioSex.Model.Catrgory;
import com.besolutions.rosto.Scenarios.ScenarioSex.Model.Model_home;
import com.besolutions.rosto.Scenarios.ScenarioSex.Pattrens.RcyMainGridAdapter;
import com.besolutions.rosto.Utils.TinyDB;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;

public class Home extends Fragment implements NetworkInterface {

    TextView txtname, txtname1,txtphone;
    RecyclerView recyclerView;
    ImageView imgbranch;
    ProgressBar pg;
    TinyDB tinyDB;

    Catrgory[] catrgory;
    List<Catrgory> catrgoryList = new ArrayList<>();

    private View view;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.activity_home, container, false);

        catrgoryList.clear();
        tinyDB = new TinyDB(getContext());

        txtname = view.findViewById(R.id.txtBranchName);
        txtname1 = view.findViewById(R.id.txtBranchName1);
        txtphone = view.findViewById(R.id.txtBranchPhone);
        recyclerView = view.findViewById(R.id.recHome);
        imgbranch = view.findViewById(R.id.imgBranchImage);
        pg = view.findViewById(R.id.loading);

        txtname.setText(tinyDB.getString("name"));
        txtname1.setText(tinyDB.getString("name"));
        // txtphone.setText(tinyDB.getString("phone"));

        new Apicalls(getContext(),Home.this).get_all_shops_category();
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

        Model_home model_home = gson.fromJson(model.getResponse(),Model_home.class);

        catrgory = model_home.getCatrgories();
        for (int i = 0; i<catrgory.length; i++)
        {
            Catrgory catrgory1 = new Catrgory();

            catrgory1.setDescription(catrgory[i].getDescription());
            catrgory1.setName(catrgory[i].getName());
            catrgory1.setImage(catrgory[i].getImage());
            catrgory1.setId(catrgory[i].getId());

            catrgoryList.add(catrgory1);
        }

        RecyclerView recyclerView = view.findViewById(R.id.recHome);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        RcyMainGridAdapter adabter = new RcyMainGridAdapter(catrgoryList,getContext());
        recyclerView.setAdapter(adabter);

    }

    @Override
    public void OnError(VolleyError error) {
        pg.setVisibility(View.GONE);

    }
}
