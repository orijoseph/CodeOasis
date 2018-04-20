package com.example.ori.codeoasis.screens.splash;

import com.example.ori.codeoasis.dataBase.ContactDao;
import com.example.ori.codeoasis.services.ApiContract;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class SplashActivityModule {

    @Provides
    static SplashPresenter provideSplashPresenter(ISplashContact.View mainView,
                                                  ApiContract apiCalls,
                                                  ContactDao dataBase) {
        return new SplashPresenter(mainView, apiCalls, dataBase);
    }

    @Binds
    abstract ISplashContact.View provideMainView(Splash mainActivity);
}
