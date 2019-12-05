package com.besolutions.rosto.Scenarios.ScenarioSeven.Pattrens;

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
import com.besolutions.rosto.Scenarios.ScenarioFour.Controller.Orders_Fragment;
import com.besolutions.rosto.Scenarios.ScenarioSeven.Model.Cart_Model;
import com.besolutions.rosto.Utils.Realm_adapter;
import com.besolutions.rosto.Utils.TinyDB;

import java.util.ArrayList;

import io.realm.Realm;

public class RcyCartAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{

    ArrayList<Cart_Model > mMainList;
    Context mContext;
    Realm realm;
    Realm_adapter adapter;

    FragmentManager fragmentManager;

    private OnItemListener mOnItemListener;

    TinyDB tinyDB;


    public RcyCartAdapter( ArrayList<Cart_Model> cartmodel, Context context) {
        this.mMainList = cartmodel;
        this.mContext = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {

        tinyDB = new TinyDB(mContext);
        FragmentManager fragmentManager;

        View ads = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_item,parent,false);
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


        final MainItemHolder mainHolder =(MainItemHolder) holder;

        Cart_Model v = mMainList.get(position);


        mainHolder.textName.setText(v.getTxtname());
        mainHolder.textPrice.setText(v.getTxtprice());
        mainHolder.textSize.setText(v.getTxtsize());
        mainHolder.textQuantity.setText(v.getTxtquntity());
        mainHolder.imgdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                AlertDialog.Builder alertDialogBulder = new AlertDialog.Builder(mContext);

                alertDialogBulder.setTitle("حذف المنتج");

                alertDialogBulder
                        .setMessage("هل انت متاكد من حذف هذا المنتج !!")
                        .setCancelable(false)
                        .setPositiveButton("نعم", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                String price = "";

                                Intent in = new Intent("delete_action");
                                in.putExtra("category", price);
                                mContext.sendBroadcast(in);


                                realm = Realm.getDefaultInstance();
                                adapter = new Realm_adapter(realm);
                                adapter.delete(position);
                                adapter.retrieve();
                                mMainList.remove(position);
                                notifyItemRemoved(position);
                                FragmentTransaction fr = ((AppCompatActivity)mContext).getSupportFragmentManager().beginTransaction();
                                fr.replace(R.id.fragment_container,new Orders_Fragment());
                                fr.commit();
                            }
                        }).setNegativeButton("لا", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.cancel();
                    }
                });

                //create it
                AlertDialog alertDialog = alertDialogBulder.create();
                // show it
                alertDialog.show();

            }
        });


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


    public class MainItemHolder extends RecyclerView.ViewHolder
    {
        TextView textName, textPrice, textSize, textQuantity;
        ImageView imgdelete;


        public MainItemHolder(@NonNull View itemView, OnItemListener onItemListener)
        {
            super(itemView);
            textName = itemView.findViewById(R.id.txtCartName);
            textPrice = itemView.findViewById(R.id.txtCartPrice);
            textQuantity = itemView.findViewById(R.id.txtCartQuantity);
            textSize = itemView.findViewById(R.id.txtCartSize);
            imgdelete = itemView.findViewById(R.id.imgCartDelete);






        }


    }
    public interface OnItemListener {
        void onItemClick(int position);
    }


}
