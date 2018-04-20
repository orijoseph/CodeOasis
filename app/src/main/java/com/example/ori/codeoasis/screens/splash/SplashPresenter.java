package com.example.ori.codeoasis.screens.splash;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;

import com.example.ori.codeoasis.dataBase.ContactDao;
import com.example.ori.codeoasis.dataBase.DataBaseManager;
import com.example.ori.codeoasis.models.ResponseServer;
import com.example.ori.codeoasis.presenters.BasePresenter;
import com.example.ori.codeoasis.screens.splash.ISplashContact;
import com.example.ori.codeoasis.services.ApiContract;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
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

    @SuppressLint("CheckResult")
    private void getContactsFromServer() {
        mView.showProgressBar(true);

        Observable<ResponseServer> contacts = mApiService.getList();
        contacts.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(responseServer -> {
                    mView.showProgressBar(false);
                    mDataBaseManager.insert(responseServer.getContacts());
                    mView.goToNextScreen();
                }, error ->
                {
                    mView.showProgressBar(false);
                    mView.showErrorMessage();
                });
    }
}
