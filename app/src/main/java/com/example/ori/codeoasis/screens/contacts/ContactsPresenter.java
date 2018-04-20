package com.example.ori.codeoasis.screens.contacts;

import android.annotation.SuppressLint;

import com.example.ori.codeoasis.dataBase.DataBaseManager;
import com.example.ori.codeoasis.models.Contact;
import com.example.ori.codeoasis.models.ResponseServer;
import com.example.ori.codeoasis.presenters.BasePresenter;
import com.example.ori.codeoasis.services.ApiContract;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Ori on 1/26/2018.
 */

public class ContactsPresenter extends BasePresenter<IContactsContract.View>
        implements IContactsContract.Presenter {

    private DataBaseManager mDataBaseManager;
    private IContactsContract.View mView;
    private ApiContract mApiService;
    private List<Contact> mContacts;
    private Disposable mDisposable;

    public ContactsPresenter(IContactsContract.View view,
                             ApiContract apiService,
                             DataBaseManager dataBaseManager) {
        super(view);
        mView = view;
        mDataBaseManager = dataBaseManager;
        mApiService = apiService;
    }

    @Override
    public void start() {
        super.start();
        mView.startObserving();
    }

    @Override
    public void removeContact(Contact contact) {
        mDataBaseManager.removeContact(contact);
    }

    @Override
    public void setContactList(List<Contact> contacts) {
        mContacts = contacts;
    }

    @Override
    public List<Contact> getContacts() {
        return mContacts;
    }

    @SuppressLint("CheckResult")
    @Override
    public void loadNewContacts() {
        mView.showProgressBar(true);

        Observable<ResponseServer> contacts = mApiService.getList();
        contacts.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(responseServer -> {
                    mView.showProgressBar(false);
                    mDataBaseManager.insert(responseServer.getContacts());
                }, error -> {
                    mView.showProgressBar(false);
                    mView.showErrorMessage();
                });
    }

    @Override
    public void addContact(Contact contact) {
        mDataBaseManager.addContact(contact);
    }
}
