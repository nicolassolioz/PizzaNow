package com.mycompany.pizzanow.database.async.pizza;

import android.app.Application;

import com.mycompany.pizzanow.BaseApp;
import com.mycompany.pizzanow.database.entity.PizzaEntity;
import com.mycompany.pizzanow.util.OnAsyncEventListener;
import android.os.AsyncTask;

public class UpdatePizza extends AsyncTask<PizzaEntity, Void, Void>{

    private static final String TAG = "UpdateClient";

    private Application mApplication;
    private OnAsyncEventListener mCallBack;
    private Exception mException;

    public UpdatePizza(Application application, OnAsyncEventListener callback) {
        mApplication = application;
        mCallBack = callback;
    }

    @Override
    protected Void doInBackground(PizzaEntity... params) {
        try {
            for (PizzaEntity pizza : params)
                ((BaseApp) mApplication).getPizzaRepository()
                        .update(pizza);
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
