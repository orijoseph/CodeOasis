package com.example.ori.codeoasis.screens.splash;

import android.content.Context;
import android.support.annotation.NonNull;

import com.example.ori.codeoasis.dataBase.ContactDao;
import com.example.ori.codeoasis.dataBase.DataBaseManager;
import com.example.ori.codeoasis.models.ResponseServer;
import com.example.ori.codeoasis.presenters.BasePresenter;
import com.example.ori.codeoasis.screens.splash.ISplashContact;
import com.example.ori.codeoasis.services.ApiContract;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Ori on 1/25/2018.
 */

public class SplashPresenter extends BasePresenter<ISplashContact.View>
        implements ISplashContact.Presenter {


    private ApiContract mApiService;
    private ISplashContact.View mView;
    private DataBaseManager mDataBaseManager;

    public SplashPresenter(ISplashContact.View view,
                           ApiContract apiService,
                           ContactDao contactDao) {
        super(view);
        mView = view;
        mApiService = apiService;
        mDataBaseManager = new DataBaseManager(contactDao);
    }


    @Override
    public void start() {
        super.start();

//        mView.startObserving();

        mDataBaseManager.getContacts((Context) mView, contacts -> {
            if (contacts.size() == 0) {
                getContactsFromServer();
            } else {
                mView.goToNextScreen();
            }
        });
    }

    private void getContactsFromServer() {
        mView.showProgressBar(true);
        Call<ResponseServer> call = mApiService.getList();
        call.enqueue(new Callback<ResponseServer>() {
            @Override
            public void onResponse(@NonNull Call<ResponseServer> call, @NonNull Response<ResponseServer> response) {
                mView.showProgressBar(false);
                if (response.body() != null && response.body().getContacts() != null) {
                    mDataBaseManager.insert(response.body().getContacts());
                    mView.goToNextScreen();
                } else {
                    mView.showErrorMessage();
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseServer> call, @NonNull Throwable t) {
                mView.showProgressBar(false);
                mView.showErrorMessage();
            }
        });
    }
}
