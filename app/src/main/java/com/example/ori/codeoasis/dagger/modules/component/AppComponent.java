package com.example.ori.codeoasis.dagger.modules.component;

import com.example.ori.codeoasis.dagger.modules.AppModule;
import com.example.ori.codeoasis.dagger.modules.DataBaseModule;
import com.example.ori.codeoasis.dagger.modules.NetModule;
import com.example.ori.codeoasis.dataBase.ContactDao;
import com.example.ori.codeoasis.screens.Splash;
import com.example.ori.codeoasis.screens.contacts.ContactsActivity;
import com.example.ori.codeoasis.services.ApiContract;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, NetModule.class, DataBaseModule.class})
public interface AppComponent {

    ContactDao getContactDao();

    ApiContract getAppApi();



//    void inject(Splash splash);
    void inject(ContactsActivity contactsActivity);
}
