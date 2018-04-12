package com.example.ori.codeoasis.dagger.modules.component;

import com.example.ori.codeoasis.dagger.modules.ContactsActivityModel;
import com.example.ori.codeoasis.dagger.modules.SplashActivityModule;
import com.example.ori.codeoasis.dagger.modules.scoups.ActivityScope;
import com.example.ori.codeoasis.screens.Splash;
import com.example.ori.codeoasis.screens.contacts.ContactsActivity;

import dagger.Subcomponent;

@ActivityScope
@Subcomponent(modules = {SplashActivityModule.class, ContactsActivityModel.class})
public interface ActivityComponent {

    void inject(Splash splashActivity);

    void inject(ContactsActivity contactsActivity);
}
