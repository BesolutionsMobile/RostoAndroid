package com.besolutions.rosto.Scenarios.ScenarioSeven.Pattrens;

import com.besolutions.rosto.Scenarios.ScenarioSeven.Model.Cart_Model;

import java.util.ArrayList;
import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmResults;

public class Realm_adapter {
    Realm realm;


    public Realm_adapter(Realm realm) {
        this.realm = realm;
    }

    public void save(final Cart_Model cartModel) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {


                Cart_Model c = realm.copyToRealm(cartModel);

/*
                //int x = (int) UUID.randomUUID().getMostSignificantBits();
                //c.setId(x);
                // increment index
                Number currentIdNum = realm.where(Cart_Model.class).max("id");
                int nextId;
                if(currentIdNum == null) {
                    nextId = currentIdNum.intValue() + 1;
                } else {
                    nextId = currentIdNum.intValue() + 1;
                }

                Cart_Model z = new Cart_Model();
                z.setId(nextId);

 */

                //...
                //realm.insertOrUpdate(user); // using insert API

            }
        });

    }

    public void delete(final int i) {

        realm = Realm.getDefaultInstance();

        final RealmResults<Cart_Model> results = realm.where(Cart_Model.class).findAll();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                // remove single match
                //results.deleteFirstFromRealm();
                //results.deleteLastFromRealm();

                // remove a single object


                results.deleteFromRealm(i);

                // Delete all matches
                // results.deleteAllFromRealm();
            }
        });
    }


    public ArrayList<Cart_Model> retrieve() {
        ArrayList<Cart_Model> cart_data = new ArrayList<>();

        realm = Realm.getDefaultInstance();
        RealmResults<Cart_Model> cart_models = realm.where(Cart_Model.class).findAll();

        for (Cart_Model s : cart_models) {

            cart_data.add(s);


        }
        return cart_data;

    }

}
