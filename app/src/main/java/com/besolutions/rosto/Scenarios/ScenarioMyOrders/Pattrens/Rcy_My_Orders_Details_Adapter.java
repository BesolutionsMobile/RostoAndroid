package com.besolutions.rosto.Scenarios.ScenarioMyOrders.Pattrens;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.besolutions.rosto.R;
import com.besolutions.rosto.Scenarios.ScenarioMyOrders.Model.ModelOrderProduct;
import com.besolutions.rosto.Scenarios.ScenarioMyOrders.Model.Model_My_Orders_Details;
import com.besolutions.rosto.Scenarios.ScenarioOrders.Controller.Orders_Fragment;
import com.besolutions.rosto.Scenarios.ScenarioProduct.Model.Cart_Model;
import com.besolutions.rosto.Utils.Realm_adapter;
import com.besolutions.rosto.Utils.TinyDB;

import java.util.ArrayList;

import io.realm.Realm;

public class Rcy_My_Orders_Details_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    ArrayList<ModelOrderProduct> mMainList;
    Context mContext;
    Realm realm;
    Realm_adapter adapter;

    FragmentManager fragmentManager;

    private OnItemListener mOnItemListener;

    TinyDB tinyDB;


    public Rcy_My_Orders_Details_Adapter(ArrayList<ModelOrderProduct> cartmodel, Context context) {
        this.mMainList = cartmodel;
        this.mContext = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        tinyDB = new TinyDB(mContext);

        View ads = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_order_details_item, parent, false);
        MainItemHolder mainHolder = new MainItemHolder(ads);

        return mainHolder;
    }

    public class MainHolder extends RecyclerView.ViewHolder {
        public MainHolder(View itemview) {
            super(itemview);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {

        int viewType = getItemViewType(position);


        final MainItemHolder mainHolder = (MainItemHolder) holder;

        ModelOrderProduct v = mMainList.get(position);


        mainHolder.textName.setText(v.getProductName());
        mainHolder.textPrice.setText(v.getPricePerItem());
        mainHolder.textSize.setText(v.getSize());
        mainHolder.textQuantity.setText(v.getQuantity());



/*
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(mContext, Product_Details.class);
                mContext.startActivity(intent);

            }
        });

 */


    }

    @Override
    public int getItemCount() {
        return mMainList.size();
    }


    public class MainItemHolder extends RecyclerView.ViewHolder {
        TextView textName, textPrice, textSize, textQuantity;


        public MainItemHolder(@NonNull View itemView) {
            super(itemView);
            textName = itemView.findViewById(R.id.txtCartName);
            textPrice = itemView.findViewById(R.id.txtCartPrice);
            textQuantity = itemView.findViewById(R.id.txtCartQuantity);
            textSize = itemView.findViewById(R.id.txtCartSize);


        }


    }

    public interface OnItemListener {
        void onItemClick(int position);
    }


}
