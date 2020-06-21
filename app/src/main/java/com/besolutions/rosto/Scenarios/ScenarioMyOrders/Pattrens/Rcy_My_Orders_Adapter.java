package com.besolutions.rosto.Scenarios.ScenarioMyOrders.Pattrens;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.besolutions.rosto.R;
import com.besolutions.rosto.Scenarios.ScenarioMyOrders.Controller.My_Orders_Details;
import com.besolutions.rosto.Scenarios.ScenarioMyOrders.Model.ModelOrder;

import com.besolutions.rosto.Utils.TinyDB;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Rcy_My_Orders_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<ModelOrder> rcyHomelList;
    private Context context;
    private TinyDB tinyDB;

    public Rcy_My_Orders_Adapter(List<ModelOrder> rcypreviouslList, Context context) {

        this.context = context;
        this.rcyHomelList = rcypreviouslList;

    }


    @NonNull
    @Override
    public ItemepreviousHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View ads = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_orders_items, parent, false);
        ItemepreviousHolder holder = new ItemepreviousHolder(ads);
        return holder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        int viewType = getItemViewType(position);
        final ModelOrder songs = rcyHomelList.get(position);

        tinyDB = new TinyDB(context);

        String insertdate = songs.getCreatedAt();
        String[] items1 = insertdate.split(" ");
        String datess = items1[0];
        String time = items1[1];

        SimpleDateFormat inFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date date = null;
        try {
            date = inFormat.parse(datess);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Log.e("dateMyorder",""+date);
        SimpleDateFormat outFormat = new SimpleDateFormat("EEE");
        String goal = outFormat.format(date);


        String[] splitingdate = datess.split("-");
        String year = splitingdate[0];
        String month = splitingdate[1];
        String day = splitingdate[2];

        Log.e("year",""+year);

        ItemepreviousHolder itemepreviousHolder = (ItemepreviousHolder) holder;
        itemepreviousHolder.txtorderscode.setText(songs.getId());
        itemepreviousHolder.txtorderDate.setText(goal+"  "+day);
        itemepreviousHolder.txtdate.setText(datess);


//        Glide.with(context)
//                .load(songs.getImghome())
//                .placeholder(R.drawable.img1)
//                .into(itemeCartHolder.imgitemhome);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tinyDB.putString("MyOrderId",rcyHomelList.get(position).getId());

                FragmentTransaction fr = ((AppCompatActivity) context).getSupportFragmentManager().beginTransaction();
                fr.replace(R.id.fragment_container, new My_Orders_Details());
                fr.addToBackStack(null);
                fr.commit();

                tinyDB.putString("MyOrderId",rcyHomelList.get(position).getId());

//                tinyDB.putString("MyOrderID", String.valueOf(rcyHomelList.get(position).getId()));
//                context.startActivity(new Intent(context, Order_Details.class));


            }
        });

//             EnglishSongHolder songHolder =(EnglishSongHolder)Holder;

    }


    @Override
    public int getItemCount() {
        return rcyHomelList.size();
    }


    public static class ItemepreviousHolder extends RecyclerView.ViewHolder {
        private final TextView txtorderscode, txtorderDate, txtdate;


        public ItemepreviousHolder(@NonNull View itemView) {
            super(itemView);
            txtorderscode = itemView.findViewById(R.id.txtOrderCode);
            txtorderDate = itemView.findViewById(R.id.txtOrderPrice);
            txtdate = itemView.findViewById(R.id.txttOrderDate);


        }
    }
}