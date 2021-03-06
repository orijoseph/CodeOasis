package com.example.ori.codeoasis.dagger.modules;

import com.example.ori.codeoasis.dagger.modules.scoups.ActivityScope;
import com.example.ori.codeoasis.screens.contacts.ContactsActivity;
import com.example.ori.codeoasis.screens.contacts.ContactsActivityModel;
import com.example.ori.codeoasis.screens.splash.Splash;
import com.example.ori.codeoasis.screens.splash.SplashActivityModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilder {

    @ActivityScope
    @ContributesAndroidInjector(modules = SplashActivityModule.class)
    abstract Splash bindSplashActivity();

    @ActivityScope
    @ContributesAndroidInjector(modules = {ContactsActivityModel.class/*, DetailFragmentProvider.class*/})
    abstract ContactsActivity bindContactsActivity();

}
