package com.example.ori.codeoasis.dagger.modules.component;

import android.app.Application;

import com.example.ori.codeoasis.MyApplication;
import com.example.ori.codeoasis.dagger.modules.ActivityBuilder;
import com.example.ori.codeoasis.dagger.modules.AppModule;
import com.example.ori.codeoasis.dagger.modules.DataBaseModule;
import com.example.ori.codeoasis.dagger.modules.NetModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {AppModule.class,
        NetModule.class,
        DataBaseModule.class,
        AndroidSupportInjectionModule.class,
        ActivityBuilder.class})
public interface AppComponent extends AndroidInjector<DaggerApplication>{

    void inject(MyApplication app);

    @Override
    void inject(DaggerApplication instance);

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);
        AppComponent build();
    }


//    SplashActivityComponent newActivityComponent(SplashActivityModule splashActivityModule);
//
//    ContactsComponent newContactsComponent(ContactsActivityModel contactsActivityModule);
}
