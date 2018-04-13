package com.example.ori.codeoasis.screens.contacts;

import android.support.annotation.NonNull;

import com.example.ori.codeoasis.dataBase.ContactDao;
import com.example.ori.codeoasis.dataBase.DataBaseManager;
import com.example.ori.codeoasis.models.Contact;
import com.example.ori.codeoasis.models.ResponseServer;
import com.example.ori.codeoasis.presenters.BasePresenter;
import com.example.ori.codeoasis.services.ApiContract;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Ori on 1/26/2018.
 */

public class ContactsPresenter extends BasePresenter<IContactsContract.View>
        implements IContactsContract.Presenter{

    private DataBaseManager mDataBaseManager;
    private ContactDao mContactDao;
    private IContactsContract.View mView;
    private ApiContract mApiService;
    private List<Contact> mContacts;

    public ContactsPresenter(IContactsContract.View view,
                             ContactDao contactDao,
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

    @Override
    public void loadNewContacts() {
        mView.showProgressBar(true);
        Call<ResponseServer> call = mApiService.getList();
        call.enqueue(new Callback<ResponseServer>() {
            @Override
            public void onResponse(@NonNull Call<ResponseServer> call, @NonNull Response<ResponseServer> response) {
                mView.showProgressBar(false);
                if (response.body() != null && response.body().getContacts() != null) {
                    mDataBaseManager.insert(response.body().getContacts());
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

    @Override
    public void addContact(Contact contact) {
        mDataBaseManager.addContact(contact);
    }
}
