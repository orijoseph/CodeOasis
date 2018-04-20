package com.example.ori.codeoasis.dataBase;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.support.annotation.NonNull;

import com.example.ori.codeoasis.models.Contact;

import java.util.List;

/**
 * Created by Ori on 1/25/2018.
 */

public class ContactViewModel  {

    ContactDao dataBaseDao;
    public LiveData<List<Contact>> mContacts;

//    public ContactViewModel(@NonNull Application application) {
    public ContactViewModel(@NonNull Context application) {

        dataBaseDao = ContactsDataBase.get(application).getContactDao();
//        mContacts = dataBaseDao.getContacts();
    }

    public LiveData<List<Contact>> getContacts() {
        return mContacts;
    }
}
