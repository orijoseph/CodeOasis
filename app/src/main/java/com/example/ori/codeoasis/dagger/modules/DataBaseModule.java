package com.example.ori.codeoasis.dagger.modules;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.example.ori.codeoasis.dataBase.ContactDao;
import com.example.ori.codeoasis.dataBase.ContactsDataBase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DataBaseModule {

    @Singleton
    @Provides
    public ContactsDataBase provideMyDatabase(Application context) {
        return Room.databaseBuilder(context, ContactsDataBase.class, "contacts.db").build();
    }

    @Singleton
    @Provides
    public ContactDao provideUserDao(ContactsDataBase myDatabase) {
        return myDatabase.getContactDao();
    }
}
