package com.mycompany.pizzanow.database.async.pizza;

import android.app.Application;
import android.os.AsyncTask;

import com.mycompany.pizzanow.BaseApp;
import com.mycompany.pizzanow.database.entity.PizzaEntity;
import com.mycompany.pizzanow.util.OnAsyncEventListener;

public class CreatePizza extends AsyncTask<PizzaEntity, Void, Void> {

    private static final String TAG = "CreatePizza";

    private Application mApplication;
    private OnAsyncEventListener mCallBack;
    private Exception mException;

    public CreatePizza(Application application, OnAsyncEventListener callback) {
        mApplication = application;
        mCallBack = callback;
    }

    @Override
    protected Void doInBackground(PizzaEntity... params) {
        try {
            for (PizzaEntity pizza : params)
                ((BaseApp) mApplication).getPizzaRepository()
                        .insert(pizza);
        } catch (Exception e) {
            mException = e;
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        if (mCallBack != null) {
            if (mException == null) {
                mCallBack.onSuccess();
            } else {
                mCallBack.onFailure(mException);
            }
        }
    }
}