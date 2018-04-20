package com.example.ori.codeoasis.screens.contacts;

import com.example.ori.codeoasis.dataBase.DataBaseManager;
import com.example.ori.codeoasis.services.ApiContract;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class ContactsActivityModel {

    @Provides
    static ContactsPresenter provideSplashPresenter(IContactsContract.View mainView,
                                                    ApiContract apiCalls,
                                                    DataBaseManager dataBaseManager) {
        return new ContactsPresenter(mainView, apiCalls, dataBaseManager);
    }

    @Binds
    abstract IContactsContract.View provideMainView(ContactsActivity contactsActivity);
}
