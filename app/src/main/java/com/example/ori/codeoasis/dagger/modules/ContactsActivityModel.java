package com.example.ori.codeoasis.dagger.modules;

import com.example.ori.codeoasis.dagger.modules.scoups.ActivityScope;
import com.example.ori.codeoasis.dataBase.ContactDao;
import com.example.ori.codeoasis.screens.contacts.ContactsPresenter;
import com.example.ori.codeoasis.screens.contacts.IContactsContract;
import com.example.ori.codeoasis.services.ApiContract;

import dagger.Module;
import dagger.Provides;

@Module
public class ContactsActivityModel {

    private IContactsContract.View mView;

    @ActivityScope
    public ContactsActivityModel(IContactsContract.View view) {
        mView = view;
    }

    @ActivityScope
    @Provides
    public ContactsPresenter contactsPresenter(ApiContract apiCalls, ContactDao dataBase) {
        return new ContactsPresenter(mView, dataBase, apiCalls);
    }
}
