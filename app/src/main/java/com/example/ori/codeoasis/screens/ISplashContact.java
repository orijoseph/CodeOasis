package com.example.ori.codeoasis.screens;

import android.content.Context;

import com.example.ori.codeoasis.models.Contact;

import java.util.List;

/**
 * Created by Ori on 1/25/2018.
 */

public interface ISplashContact {

    interface View {

        void showErrorMessage();

        void setRecycler(List<Contact> contacts);

        void showProgressBar(boolean showProgressBar);
    }

    interface Presenter {

        void init();

    }
}
