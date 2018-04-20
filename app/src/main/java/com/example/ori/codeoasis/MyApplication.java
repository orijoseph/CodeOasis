package com.example.ori.codeoasis;

import android.app.Application;

import com.example.ori.codeoasis.dagger.modules.AppModule;
import com.example.ori.codeoasis.dagger.modules.DataBaseModule;
import com.example.ori.codeoasis.dagger.modules.NetModule;
import com.example.ori.codeoasis.dagger.modules.component.AppComponent;
import com.example.ori.codeoasis.dagger.modules.component.DaggerAppComponent;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

public class MyApplication extends DaggerApplication {

    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();

//        mAppComponent = DaggerAppComponent.builder()
//                .appModule(new AppModule(this))
//                .netModule(new NetModule())
//                .dataBaseModule(new DataBaseModule())
//                .build();
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        AppComponent appComponent = DaggerAppComponent.builder().application(this).build();
        appComponent.inject(this);
        return appComponent;
    }

    public AppComponent getmAppComponent() {
        return mAppComponent;
    }
}
