package com.example.ori.codeoasis.screens.splash;

import com.example.ori.codeoasis.dagger.modules.scoups.ActivityScope;

import dagger.Subcomponent;

@ActivityScope
@Subcomponent(modules = {SplashActivityModule.class/*, ContactsActivityModel.class*/})
public interface SplashActivityComponent {

    void inject(Splash splashActivity);

}
