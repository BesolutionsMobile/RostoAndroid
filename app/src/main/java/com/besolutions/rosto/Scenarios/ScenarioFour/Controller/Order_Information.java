package com.besolutions.rosto.Scenarios.ScenarioFour.Controller;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.VolleyError;
import com.besolutions.rosto.MyProgressDialog;
import com.besolutions.rosto.NetworkLayar.Apicalls;
import com.besolutions.rosto.NetworkLayar.NetworkInterface;
import com.besolutions.rosto.NetworkLayar.ResponseModel;
import com.besolutions.rosto.R;
import com.besolutions.rosto.Scenarios.ScenarioFour.Model.Model_Cart;
import com.besolutions.rosto.Scenarios.ScenarioSeven.Model.Cart_Model;
import com.besolutions.rosto.Scenarios.ScenarioThree.Controller.Edit_Profile;
import com.besolutions.rosto.Utils.Realm_adapter;
import com.besolutions.rosto.local_data.saved_data;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.gson.Gson;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;
import io.realm.Realm;

public class Order_Information extends Fragment implements NetworkInterface {


    private View view;
    Realm realm;
    ProgressBar pg;
    Button btnfinish;
    EditText editname, editarea, editstreet, editphone, editnotes, editbuilding, editfloor, editflat;
    String id_user, id_branches;
    String a = "";


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.order_information, container, false);
        realm = Realm.getDefaultInstance();

        pg = view.findViewById(R.id.loading);
        pg.setVisibility(View.GONE);
        btnfinish = view.findViewById(R.id.btnOrderInformationFinish);
        editname = view.findViewById(R.id.editOrderInformationName);
        editarea = view.findViewById(R.id.editOrderInformationArea);
        editflat = view.findViewById(R.id.editOrderInformationApartment);
        editbuilding = view.findViewById(R.id.editOrderInformationBuilding);
        editfloor = view.findViewById(R.id.editOrderInformationFloor);
        editnotes = view.findViewById(R.id.editOrderInformationNotes);
        editphone = view.findViewById(R.id.editOrderInformationPhoneNumber);
        editstreet = view.findViewById(R.id.editOrderInformationStreet);

        saved_data saved_data = new saved_data();
        id_user = saved_data.get_user_id(getContext());
        id_branches = saved_data.get_Branch_id(getContext());


        btnfinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (editname.getText().toString().equals("")) {

                    YoYo.with(Techniques.Flash)
                            .duration(800)
                            .repeat(1)
                            .playOn(view.findViewById(R.id.editOrderInformationName));

                    editname.setError("من فضلك ادخل الاسم !");
                    Toasty.error(getContext(), "من فضلك ادخل الاسم!", Toast.LENGTH_SHORT).show();

                } else if (editarea.getText().toString().equals("")) {

                    editarea.setError("من فضلك ادخل المنطقة!");
                    YoYo.with(Techniques.Flash)
                            .duration(800)
                            .repeat(1)
                            .playOn(view.findViewById(R.id.editOrderInformationArea));

                    Toasty.error(getContext(), "من فضلك ادخل المنطقة!", Toast.LENGTH_SHORT).show();


                } else if (editflat.getText().toString().equals("")) {
                    editflat.setError("من فضلك ادخل رقم الشقة الخاص بك!");

                    YoYo.with(Techniques.Flash)
                            .duration(800)
                            .repeat(1)
                            .playOn(view.findViewById(R.id.editOrderInformationApartment));

                    Toasty.error(getContext(), "من فضلك ادخل رقم الشقة الخاص بك!", Toast.LENGTH_SHORT).show();

                } else if (editbuilding.getText().toString().equals("")) {
                    editbuilding.setError("من فضلك ادخل رقم البناية الخاص بك!");

                    YoYo.with(Techniques.Flash)
                            .duration(800)
                            .repeat(1)
                            .playOn(view.findViewById(R.id.editOrderInformationBuilding));

                    Toasty.error(getContext(), "من فضلك ادخل رقم البناية الخاص بك!", Toast.LENGTH_SHORT).show();

                } else if (editfloor.getText().toString().equals("")) {

                    editfloor.setError("من فضلك ادخل رقم الطابق الخاص بك!");
                    YoYo.with(Techniques.Flash)
                            .duration(800)
                            .repeat(1)
                            .playOn(view.findViewById(R.id.editOrderInformationFloor));

                    Toasty.error(getContext(), "من فضلك ادخل رقم الطابق الخاص بك!", Toast.LENGTH_SHORT).show();


                } else if (editnotes.getText().toString().equals("")) {
                    editnotes.setError("من فضلك ادخل الملاحظات علي طلبك !");

                    YoYo.with(Techniques.Flash)
                            .duration(800)
                            .repeat(1)
                            .playOn(view.findViewById(R.id.editOrderInformationNotes));

                    Toasty.error(getContext(), "من فضلك ادخل الملاحظات علي طلبك !", Toast.LENGTH_SHORT).show();

                } else if (editphone.getText().toString().equals("")) {
                    editphone.setError("من فضلك ادخل الرقم السري !");

                    YoYo.with(Techniques.Flash)
                            .duration(800)
                            .repeat(1)
                            .playOn(view.findViewById(R.id.editOrderInformationPhoneNumber));

                    Toasty.error(getContext(), "من فضلك ادخل الرقم السري!", Toast.LENGTH_SHORT).show();

                } else if (editstreet.getText().toString().equals("")) {

                    editstreet.setError("من فضلك ادخل اسم الشارع الخاص بك!");
                    YoYo.with(Techniques.Flash)
                            .duration(800)
                            .repeat(1)
                            .playOn(view.findViewById(R.id.editOrderInformationStreet));

                    Toasty.error(getContext(), "من فضلك ادخل اسم الشارع الخاص بك!", Toast.LENGTH_SHORT).show();


                } else {

                    Realm_adapter adapter = new Realm_adapter(realm);
                    ArrayList<Cart_Model> cartModel = adapter.retrieve();

                    a = "";
                    for (int i = 0; i < adapter.retrieve().size(); i++) {

                        if (i + 1 == adapter.retrieve().size()) {

                            a += "[" + cartModel.get(i).getIdproduct().toString() + "," + cartModel.get(i).getTxtquntity().toString() + "," + cartModel.get(i).getTxtprice().toString() + "," + cartModel.get(i).getSize_id().toString() + "]";

                        } else {

                            a += "[" + cartModel.get(i).getIdproduct().toString() + "," + cartModel.get(i).getTxtquntity().toString() + "," + cartModel.get(i).getTxtprice().toString() + "," + cartModel.get(i).getSize_id().toString() + "]" + ",";

                        }

                    }
                    Log.e("allalalalala", "" + "[" + a + "]");

                    new Apicalls(getContext(), Order_Information.this).sendOrder(editname.getText().toString(), editstreet.getText().toString(), editbuilding.getText().toString(), editfloor.getText().toString(), editflat.getText().toString(), editphone.getText().toString(), "0.0", "0.0", editnotes.getText().toString(), id_user, id_branches, "[" + a + "]");
                    pg.setVisibility(View.VISIBLE);
                }


            }
        });

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

        pg.setVisibility(View.GONE);
        Realm_adapter realmAdapter = new Realm_adapter(realm);
        Gson gson = new Gson();
        Model_Cart model_cart = gson.fromJson(model.getResponse(), Model_Cart.class);

        if (model_cart.getStatus() == 1) {
            Toasty.success(getContext(), "" + model_cart.getMessage(), Toast.LENGTH_SHORT).show();

            realmAdapter.delete_all();

            MyProgressDialog myProgressDialog = new MyProgressDialog();
            myProgressDialog.dialog(getContext(), R.layout.myprogressdialog, .90);
            /*String price = "";
            Intent in = new Intent("send_order_action");
            in.putExtra("category", price);
            getContext().sendBroadcast(in);*/
        } else if (model_cart.getStatus() == 2) {

            Toasty.error(getContext(), "" + model_cart.getMessage(), Toast.LENGTH_LONG).show();

        } else if (model_cart.getStatus() == 3) {

            Toasty.error(getContext(), "" + model_cart.getMessage(), Toast.LENGTH_LONG).show();

        }

    }

    @Override
    public void OnError(VolleyError error) {
        pg.setVisibility(View.GONE);
        boolean connected = false;
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
