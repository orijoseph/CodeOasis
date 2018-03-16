package com.example.ori.codeoasis.dataBase;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.ori.codeoasis.models.Contact;
import com.example.ori.codeoasis.models.Phone;

/**
 * Created by Ori on 1/25/2018.
 */
@Database(entities = {Contact.class, Phone.class}, version = 1, exportSchema = false)
public abstract class ContactsDataBase extends RoomDatabase {

    public abstract ContactDao getContactDao();

    private static final String DB_NAME = "contacts.db";
    private static volatile ContactsDataBase INSTANCE = null;

    public synchronized static ContactsDataBase get(Context context) {
        if (INSTANCE == null) {
            INSTANCE = create(context, false);
        }
        return (INSTANCE);
    }

    static ContactsDataBase create(Context context, boolean memoryOnly) {
        RoomDatabase.Builder<ContactsDataBase> b;

        if (memoryOnly) {
            b = Room.inMemoryDatabaseBuilder(context.getApplicationContext(), ContactsDataBase.class);
        } else {
            b = Room.databaseBuilder(context.getApplicationContext(), ContactsDataBase.class, DB_NAME);
        }

        return (b.build());
    }
}
