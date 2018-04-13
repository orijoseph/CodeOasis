package com.example.ori.codeoasis.dagger.modules.component;

import com.example.ori.codeoasis.dagger.modules.AppModule;
import com.example.ori.codeoasis.dagger.modules.DataBaseModule;
import com.example.ori.codeoasis.dagger.modules.NetModule;
import com.example.ori.codeoasis.screens.splash.SplashActivityModule;
import com.example.ori.codeoasis.screens.contacts.ContactsActivityModel;
import com.example.ori.codeoasis.screens.contacts.ContactsComponent;
import com.example.ori.codeoasis.screens.splash.SplashActivityComponent;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, NetModule.class, DataBaseModule.class})
public interface AppComponent {

    SplashActivityComponent newActivityComponent(SplashActivityModule splashActivityModule);

    ContactsComponent newContactsComponent(ContactsActivityModel contactsActivityModule);
}
