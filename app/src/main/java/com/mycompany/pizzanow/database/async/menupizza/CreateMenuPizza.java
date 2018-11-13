package com.mycompany.pizzanow.database.async.menupizza;

import android.app.Application;
import android.os.AsyncTask;

import com.mycompany.pizzanow.BaseApp;
import com.mycompany.pizzanow.database.entity.MenuPizzaEntity;
import com.mycompany.pizzanow.util.OnAsyncEventListener;

public class CreateMenuPizza extends AsyncTask<MenuPizzaEntity, Void, Void> {

    private static final String TAG = "Create MenuPizza";

    private Application mApplication;
    private OnAsyncEventListener mCallBack;
    private Exception mException;

    public CreateMenuPizza(Application application, OnAsyncEventListener callback) {
        mApplication = application;
        mCallBack = callback;
    }

    @Override
    protected Void doInBackground(MenuPizzaEntity... params) {
        try {
            for (MenuPizzaEntity menuPizza : params)
                ((BaseApp) mApplication).getMenuPizzaRepository()
                        .insert(menuPizza);
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
