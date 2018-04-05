package com.example.ori.codeoasis.presenters;

import android.util.Log;

public class BasePresenter<V> {

    protected final V view;
    public String text;

    protected BasePresenter(V view) {
        this.view = view;
    }


    public void start() {
        Log.e(view.getClass().getSimpleName(), "is running");
    }

    public void stop() {
    }
}
