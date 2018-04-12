package com.example.ori.codeoasis.dagger.modules;

import com.example.ori.codeoasis.dagger.modules.scoups.ActivityScope;
import com.example.ori.codeoasis.dataBase.ContactDao;
import com.example.ori.codeoasis.screens.ISplashContact;
import com.example.ori.codeoasis.screens.SplashPresenter;
import com.example.ori.codeoasis.services.ApiContract;

import dagger.Module;
import dagger.Provides;

@Module
public class SplashActivityModule {

    private ISplashContact.View mView;

    @ActivityScope
    public SplashActivityModule(ISplashContact.View view) {
        mView = view;
    }

    @ActivityScope
    @Provides
    public SplashPresenter provideSplashPresenter(ApiContract apiCalls, ContactDao dataBase) {
        return new SplashPresenter(mView, apiCalls, dataBase);
    }
}
