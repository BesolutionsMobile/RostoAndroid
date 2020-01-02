package com.besolutions.rosto.Scenarios.ScenarioThree.Controller;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.besolutions.rosto.NetworkLayar.Apicalls;
import com.besolutions.rosto.NetworkLayar.NetworkInterface;
import com.besolutions.rosto.NetworkLayar.ResponseModel;
import com.besolutions.rosto.R;
import com.besolutions.rosto.Scenarios.ScenarioSeven.Model.Productes;
import com.besolutions.rosto.Scenarios.ScenarioSeven.Pattrens.RcyProductAdapter;
import com.besolutions.rosto.Scenarios.ScenarioSex.Model.Catrgory;
import com.besolutions.rosto.Scenarios.ScenarioThree.Model.Model_FAQ;
import com.besolutions.rosto.Scenarios.ScenarioThree.Model.Question;
import com.besolutions.rosto.Scenarios.ScenarioThree.Pattrens.SimpleAdapter;
import com.google.gson.Gson;

import net.cachapa.expandablelayout.ExpandableLayout;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class FAQ extends Fragment implements NetworkInterface {

    private View view;
    Question[] questions;
    List<Question> faqLis = new ArrayList<>();
    ExpandableListView expandabletextView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_faq, container, false);

        new Apicalls(getContext(), FAQ.this).faq_question();

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

        Gson gson = new Gson();

        Model_FAQ model_faq = gson.fromJson(model.getResponse(), Model_FAQ.class);

        questions = model_faq.getQuestions();

        if (model_faq.getStatus()==1)
        {

            for (int i = 0; i<questions.length; i++)
            {
                Question question1 = new Question();

                question1.setTitle(questions[i].getTitle());
                question1.setDescription(questions[i].getDescription());
                question1.setId(questions[i].getId());

                faqLis.add(question1);

            }

        }else if (model_faq.getStatus() == 3)
        {

            Toast.makeText(getContext(), " لا توجد بيانات..", Toast.LENGTH_SHORT).show();


        }else
        {
            Toast.makeText(getContext(), ""+model_faq.getStatus() , Toast.LENGTH_SHORT).show();
        }

        RecyclerView recyclerView = view.findViewById(R.id.rcyFaq);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new SimpleAdapter(recyclerView,faqLis));


    }

    @Override
    public void OnError(VolleyError error) {
        ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.DISCONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.DISCONNECTED) {
            //we are connected to a network
            Toast.makeText(getContext(), "Please Check Your Internet Connection", Toast.LENGTH_SHORT).show();
        } else {

            Toast.makeText(getContext(), "" + error.toString(), Toast.LENGTH_SHORT).show();
        }

    }


}
