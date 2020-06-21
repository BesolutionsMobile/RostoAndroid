package com.besolutions.rosto.Scenarios.ScenarioProduct.Pattrens;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.besolutions.rosto.R;
import com.besolutions.rosto.Scenarios.ScenarioProduct.Controller.Product_Details;
import com.besolutions.rosto.Scenarios.ScenarioProduct.Model.Productes;
import com.besolutions.rosto.Utils.TinyDB;
import com.besolutions.rosto.local_data.send_data;
import com.bumptech.glide.Glide;

import java.util.List;

public class RcyProductAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{

    List<Productes> mMainList;
    Context mContext;
    private OnItemListener mOnItemListener;

    TinyDB tinyDB;

    public RcyProductAdapter(List<Productes> listBranches, Context context) {
        this.mMainList = listBranches;
        this.mContext = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {

        tinyDB = new TinyDB(mContext);

        View ads = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item,parent,false);
        MainItemHolder mainHolder = new MainItemHolder(ads,mOnItemListener) ;


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
        final Productes productes = mMainList.get(position);


        MainItemHolder mainHolder =(MainItemHolder) holder;


        mainHolder.textName.setText(productes.getName());
        mainHolder.textPrice.setText(productes.getPrice());
        Glide.with(mContext)
                .load(productes.getImage())
                .placeholder(R.drawable.itembranch)
                .into(mainHolder.imgProduct);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                send_data send_data = new send_data();
                send_data.SET_PRODUCT_ID(mContext,mMainList.get(position).getProductId());

                //Intent intent = new Intent(mContext, Product_Details.class);
                //mContext.startActivity(intent);

                FragmentTransaction fr = ((AppCompatActivity)mContext).getSupportFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_container,new Product_Details());
                fr.addToBackStack(null);
                fr.commit();

            }
        });


    }

    @Override
    public int getItemCount() {
        return mMainList.size();
    }


    public class MainItemHolder extends RecyclerView.ViewHolder
    {
        TextView textName, textPrice;
        ImageView imgProduct;

        public MainItemHolder(@NonNull View itemView, OnItemListener onItemListener)
        {
            super(itemView);
            textName = itemView.findViewById(R.id.txtProductName);
            textPrice = itemView.findViewById(R.id.txtProductPrice);
            imgProduct = itemView.findViewById(R.id.imgProduct);




        }


    }
    public interface OnItemListener {
        void onItemClick(int position);
    }


}
