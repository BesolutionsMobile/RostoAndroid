package com.besolutions.rosto.Scenarios.SenarioFive.Pattrens;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.besolutions.rosto.R;
import com.besolutions.rosto.Scenarios.ScenarioFour.Controller.Orders_Fragment;
import com.besolutions.rosto.Scenarios.ScenarioSex.Controller.Home;
import com.besolutions.rosto.Scenarios.SenarioFive.Model.Branch;
import com.besolutions.rosto.Scenarios.SenarioFive.Model.Model_branches;
import com.besolutions.rosto.Utils.TinyDB;
import com.besolutions.rosto.local_data.send_data;
import com.bumptech.glide.Glide;

import java.util.List;

public class RcyMainAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{

    List<Branch> mMainList;
    Context mContext;

    TinyDB tinyDB;

    public RcyMainAdapter(List<Branch> listBranches, Context context) {
        this.mMainList = listBranches;
        this.mContext = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {

        tinyDB = new TinyDB(mContext);

        View ads = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_branches,parent,false);
        MainItemHolder mainHolder = new MainItemHolder(ads) ;


        return mainHolder;
    }

    public class MainHolder extends RecyclerView.ViewHolder{
        public MainHolder(View itemview) {
            super(itemview);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {

        int viewType = getItemViewType(position);
        final Branch branches  = mMainList.get(position);


        MainItemHolder mainHolder =(MainItemHolder) holder;


        mainHolder.textTitle.setText(branches.getName());
        mainHolder.textPhone.setText(branches.getPhone());
        mainHolder.textAdress.setText(branches.getAddress());
       // mainHolder.poster.setImageResource(Integer.parseInt(main.getImage()));
        Glide.with(mContext)
                .load(branches.getImage())
                .placeholder(R.drawable.itembranch)
                .into(mainHolder.poster);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                send_data send_data = new send_data();
                send_data.id_branches(mContext,mMainList.get(position).getId());
                //Toast.makeText(mContext, ""+mMainList.get(position).getId(), Toast.LENGTH_SHORT).show();
                FragmentTransaction fr = ((AppCompatActivity)mContext).getSupportFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_container,new Home());
                fr.addToBackStack(null);
                fr.commit();
                //Intent intent = new Intent(mContext, Home.class);
                //mContext.startActivity(intent);

            }
        });


    }

    @Override
    public int getItemCount() {
        return mMainList.size();
    }


    public class MainItemHolder extends RecyclerView.ViewHolder
    {
        TextView textTitle, textAdress, textPhone;
        ImageView poster;

        public MainItemHolder(@NonNull View itemView)
        {
            super(itemView);
            textTitle = itemView.findViewById(R.id.txtTitle);
            textAdress = itemView.findViewById(R.id.txtAdress);
            textPhone = itemView.findViewById(R.id.txtPhone);
            poster = itemView.findViewById(R.id.poster);




        }


    }

}
