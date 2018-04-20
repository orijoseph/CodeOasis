package com.example.ori.codeoasis.screens.contacts;

import com.example.ori.codeoasis.dataBase.ContactDao;
import com.example.ori.codeoasis.dataBase.DataBaseManager;
import com.example.ori.codeoasis.services.ApiContract;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class ContactsActivityModel {
//    private IContactsContract.View mView;
//
//    @ActivityScope
//    public ContactsActivityModel(IContactsContract.View view) {
//        mView = view;
//    }
//
//    @ActivityScope
//    @Provides
//    public ContactsPresenter contactsPresenter(ApiContract apiCalls, ContactDao dataBase, DataBaseManager dataBaseManager) {
//        return new ContactsPresenter(mView, dataBase, apiCalls, dataBaseManager);
//    }

    @Provides
    static ContactsPresenter provideSplashPresenter(IContactsContract.View mainView,
                                                    ApiContract apiCalls,
                                                    ContactDao dataBase,
                                                    DataBaseManager dataBaseManager) {
        return new ContactsPresenter(mainView, dataBase, apiCalls, dataBaseManager);
    }

    @Binds
    abstract IContactsContract.View provideMainView(ContactsActivity contactsActivity);
}
