package com.mycompany.pizzanow.util;

public interface OnAsyncEventListener {
    void onSuccess();
    void onFailure(Exception e);
}
