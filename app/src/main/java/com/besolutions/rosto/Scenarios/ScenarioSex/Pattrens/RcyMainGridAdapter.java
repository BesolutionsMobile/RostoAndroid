package com.besolutions.rosto.Scenarios.ScenarioSex.Pattrens;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.besolutions.rosto.R;
import com.besolutions.rosto.Scenarios.ScenarioFour.Controller.Orders_Fragment;
import com.besolutions.rosto.Scenarios.ScenarioSeven.Controller.Product;
import com.besolutions.rosto.Scenarios.ScenarioSex.Controller.Home;
import com.besolutions.rosto.Scenarios.ScenarioSex.Model.Catrgory;
import com.besolutions.rosto.Utils.TinyDB;
import com.bumptech.glide.Glide;

import java.util.List;
import java.util.Locale;

public class RcyMainGridAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{

    TinyDB tinyDB;
    List<Catrgory> mMainGridList;
    Context mContext;


    public RcyMainGridAdapter(List<Catrgory> songsList, Context context) {
        this.mMainGridList = songsList;
        this.mContext = context;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View ads = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_item,parent,false);
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

        tinyDB = new TinyDB(mContext);
        int viewType = getItemViewType(position);
        final Catrgory catrgory  = mMainGridList.get(position);


        MainItemHolder mainHolder =(MainItemHolder) holder;


        mainHolder.textname.setText(catrgory.getName());
        mainHolder.textdescription.setText(catrgory.getDescription());
        Glide.with(mContext)
                .load(catrgory.getImage())
                .placeholder(R.drawable.rostologo)
                .into(mainHolder.imageView);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tinyDB.putString("id_home",mMainGridList.get(position).getId());
                //mContext.startActivity(new Intent(mContext, Product.class).putExtra("id_home",mMainGridList.get(position).getId()));
                FragmentTransaction fr = ((AppCompatActivity)mContext).getSupportFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_container,new Product());
                fr.commit();
            }
        });


    }

    @Override
    public int getItemCount() {
        return mMainGridList.size();
    }


    public class MainItemHolder extends RecyclerView.ViewHolder
    {
        TextView textname,textdescription;
        ImageView imageView;
        LinearLayout lineasuggest;
        OnItemListener onItemListener;

        public MainItemHolder(@NonNull View itemView)
        {
            super(itemView);
            textname = itemView.findViewById(R.id.txtNameHome);
            textdescription = itemView.findViewById(R.id.txtDescriptionHome);
            imageView = itemView.findViewById(R.id.imgHome);

        }

    }
    public interface OnItemListener {
        void onItemClick(int position);
    }


}
