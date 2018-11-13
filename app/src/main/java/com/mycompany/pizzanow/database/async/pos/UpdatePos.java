package com.mycompany.pizzanow.database.async.pos;

import android.app.Application;
import android.os.AsyncTask;

import com.mycompany.pizzanow.BaseApp;
import com.mycompany.pizzanow.database.entity.PosEntity;
import com.mycompany.pizzanow.util.OnAsyncEventListener;

public class UpdatePos extends AsyncTask<PosEntity, Void, Void> {

    private static final String TAG = "UpdateClient";

    private Application mApplication;
    private OnAsyncEventListener mCallBack;
    private Exception mException;

    public UpdatePos(Application application, OnAsyncEventListener callback) {
        mApplication = application;
        mCallBack = callback;
    }

    @Override
    protected Void doInBackground(PosEntity... params) {
        try {
            for (PosEntity pos : params)
                ((BaseApp) mApplication).getPosRepository()
                        .update(pos);
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
