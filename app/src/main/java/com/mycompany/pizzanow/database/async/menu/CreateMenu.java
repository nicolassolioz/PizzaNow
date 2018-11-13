package com.mycompany.pizzanow.database.async.menu;

import android.app.Application;
import android.os.AsyncTask;

import com.mycompany.pizzanow.BaseApp;
import com.mycompany.pizzanow.database.entity.MenuEntity;
import com.mycompany.pizzanow.util.OnAsyncEventListener;

public class CreateMenu extends AsyncTask<MenuEntity, Void, Void>
{

    private static final String TAG = "CreatePizza";

    private Application mApplication;
    private OnAsyncEventListener mCallBack;
    private Exception mException;

    public CreateMenu(Application application, OnAsyncEventListener callback) {
        mApplication = application;
        mCallBack = callback;
    }

    @Override
    protected Void doInBackground(MenuEntity... params) {
        try {
            for (MenuEntity menu : params)
                ((BaseApp) mApplication).getMenuRepository()
                        .insert(menu);
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
