package com.example.ori.codeoasis.dagger.modules;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class AppModule {

//    Application mApplication;
//
//    public AppModule(Application application) {
//        mApplication = application;
//    }
//
//    @Provides
//    @Singleton
//    Application providesApplication() {
//        return mApplication;
//    }

    @Binds
    abstract Context provideContext(Application application);
}
