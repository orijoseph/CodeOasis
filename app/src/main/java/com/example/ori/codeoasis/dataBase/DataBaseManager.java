package com.example.ori.codeoasis.dataBase;

import android.content.Context;
import android.os.AsyncTask;

import com.example.ori.codeoasis.models.Contact;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by Ori on 1/25/2018.
 */

public class DataBaseManager {

    ContactDao mDataBase;

    public DataBaseManager(ContactDao DataBase) {
        mDataBase = DataBase;
    }

    public void insert(List<Contact> contacts) {
        final Executor executor = Executors.newFixedThreadPool(2);

        executor.execute(() -> {
            mDataBase.insert(contacts);
        });
    }

    public void removeContact(Contact contact) {
        final Executor executor = Executors.newFixedThreadPool(2);

        executor.execute(() -> {
            mDataBase.removeContact(contact.getId());
        });
    }

    public void addContact(Contact contact) {
        final Executor executor = Executors.newFixedThreadPool(2);

        executor.execute(() -> {
            mDataBase.addContact(contact);
        });
    }

    public void getContacts(Context context, IDataBaseListener listener) {
        new GetContacts(context, listener).execute();
    }

    private static class GetContacts extends AsyncTask<Void, Void, List<Contact>> {

        IDataBaseListener _listener;
        Context mContext;

        GetContacts(Context context, IDataBaseListener listener) {
            _listener = listener;
            mContext = context;
        }

        @Override
        protected List<Contact> doInBackground(Void... params) {
            ContactDao mDataBase = ContactsDataBase.get(mContext.getApplicationContext()).getContactDao();
            return mDataBase.getAllContacts();
        }

        @Override
        protected void onPostExecute(List<Contact> contacts) {
            _listener.getContacts(contacts);
        }
    }

    public interface IDataBaseListener {
        void getContacts(List<Contact> contacts);
    }
}
