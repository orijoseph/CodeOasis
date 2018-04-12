package com.example.ori.codeoasis.dagger.modules.component;

import com.example.ori.codeoasis.dagger.modules.ContactsActivityModel;
import com.example.ori.codeoasis.dagger.modules.SplashActivityModule;
import com.example.ori.codeoasis.dagger.modules.scoups.ActivityScope;
import com.example.ori.codeoasis.screens.Splash;
import com.example.ori.codeoasis.screens.contacts.ContactsActivity;

import dagger.Component;

@ActivityScope
@Component(dependencies = AppComponent.class, modules = {SplashActivityModule.class})
public interface ActivityComponent {

    void inject(Splash splashActivity);

//    void inject(ContactsActivity contactsActivity);
}
