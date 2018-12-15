package com.mycompany.pizzanow.database.repository;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Transaction;
import com.mycompany.pizzanow.database.AppDatabase;

import com.mycompany.pizzanow.database.entity.PizzaEntity;
import com.mycompany.pizzanow.database.firebase.PizzaListLiveData;
import com.mycompany.pizzanow.database.firebase.PizzaLiveData;
import com.mycompany.pizzanow.util.OnAsyncEventListener;

import java.util.List;

public class PizzaRepository {
    private static final String TAG = "PizzaRepository";
    private static PizzaRepository sInstance;

    public static PizzaRepository getInstance() {
        if (sInstance == null) {
            synchronized (PizzaRepository.class) {
                if (sInstance == null) {
                    sInstance = new PizzaRepository();
                }
            }
        }
        return sInstance;
    }

    public LiveData<PizzaEntity> getPizza(final int pizzaId) {
        String id = Integer.toString(pizzaId);
        DatabaseReference reference = FirebaseDatabase.getInstance()
                .getReference("Pizza")
                .child(id);
        return new PizzaLiveData(reference);
    }

    public LiveData<List<PizzaEntity>> getAllPizza() {
        DatabaseReference reference = FirebaseDatabase.getInstance()
                .getReference("All Pizzas");
        return new PizzaListLiveData(reference);
    }

    /*
    public LiveData<List<PizzaEntity>> getPizzasByIdMenu(int id) {
        return mDatabase.pizzaDao().getPizzasByIdMenu(1);
    }

    public LiveData<List<PizzaEntity>> getAllByIdMenu(List<Integer> ids) {
        return mDatabase.pizzaDao().getAllByIdMenu(ids);
    }

    public LiveData<List<ClientWithAccounts>> getOtherClientsWithAccounts(final String owner) {
        return mDatabase.pizzaDao().getOtherClientsWithAccounts(owner);
    }*/

    public void insert(final PizzaEntity pizza) {
        DatabaseReference reference = FirebaseDatabase.getInstance()
                .getReference("Pizzas");
        String key = reference.push().getKey();
        FirebaseDatabase.getInstance()
                .getReference("pizzas")
                .child(key)
                .setValue(pizza, (databaseError, databaseReference) -> {
                    if (databaseError != null) {
                        Log.d(TAG, "Insert failure!", databaseError.toException());
                    } else {
                        Log.i(TAG, "Insert successful!");
                    }
                });

    }

    public void update(final PizzaEntity pizza) {
        String id = Integer.toString(pizza.getIdPizza());
        FirebaseDatabase.getInstance()
                .getReference("pizzas")
                .child(id)
                .updateChildren(pizza.toMap(), (databaseError, databaseReference) -> {
                    if (databaseError != null) {
                        Log.d(TAG, "Insert failure!", databaseError.toException());
                    } else {
                        Log.i(TAG, "Insert successful!");
                    }
                });
    }

    public void delete(final PizzaEntity pizza) {
        String id = Integer.toString(pizza.getIdPizza());
        FirebaseDatabase.getInstance()
                .getReference("pizzas")
                .child(id)
                .removeValue((databaseError, databaseReference) -> {
                    if (databaseError != null) {
                        Log.d(TAG, "Delete failure!", databaseError.toException());
                    } else {
                        Log.d(TAG, "Delete successful!");
                    }
                });
    }

    public void transaction(final PizzaEntity sender, final PizzaEntity recipient){
        String senderId = Integer.toString(sender.getIdPizza());
        String recipientId = Integer.toString(recipient.getIdPizza());
        final DatabaseReference rootReference = FirebaseDatabase.getInstance().getReference();
        rootReference.runTransaction(new Transaction.Handler() {
            @NonNull
            @Override
            public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                rootReference
                        .child("pizzas")
                        .child(senderId)
                        .updateChildren(sender.toMap());

                rootReference
                        .child("pizzas")
                        .child(recipientId)
                        .updateChildren(recipient.toMap());

                return Transaction.success(mutableData);
            }

            @Override
            public void onComplete(DatabaseError databaseError, boolean b, DataSnapshot dataSnapshot) {
                if (databaseError != null) {
                    Log.d(TAG, "Transaction failure!", databaseError.toException());
                } else {
                    Log.d(TAG, "Transaction successful!");
                }
            }
        });
    }
}
