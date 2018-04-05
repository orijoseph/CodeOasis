package com.example.ori.codeoasis;

import android.app.Application;

import com.example.ori.codeoasis.dagger.modules.AppModule;
import com.example.ori.codeoasis.dagger.modules.DataBaseModule;
import com.example.ori.codeoasis.dagger.modules.NetModule;
import com.example.ori.codeoasis.dagger.modules.component.AppComponent;
import com.example.ori.codeoasis.dagger.modules.component.DaggerAppComponent;

public class MyApplication extends Application {

    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .netModule(new NetModule())
                .dataBaseModule(new DataBaseModule())
                .build();
    }

    public AppComponent getmAppComponent() {
        return mAppComponent;
    }
}
