package com.mycompany.pizzanow.database.async.collaborateur;

import android.app.Application;
import android.os.AsyncTask;

import com.mycompany.pizzanow.BaseApp;
import com.mycompany.pizzanow.database.entity.CollaborateurEntity;
import com.mycompany.pizzanow.util.OnAsyncEventListener;

public class CreateCollaborateur extends AsyncTask<CollaborateurEntity, Void, Void> {

    private static final String TAG = "CreatePizza";

    private Application mApplication;
    private OnAsyncEventListener mCallBack;
    private Exception mException;

    public CreateCollaborateur(Application application, OnAsyncEventListener callback) {
        mApplication = application;
        mCallBack = callback;
    }

    @Override
    protected Void doInBackground(CollaborateurEntity... params) {
        try {
            for (CollaborateurEntity collab : params)
                ((BaseApp) mApplication).getCollaborateurRepository()
                        .insert(collab);
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
