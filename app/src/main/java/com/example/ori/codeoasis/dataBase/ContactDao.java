package com.example.ori.codeoasis.dataBase;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.ori.codeoasis.models.Contact;

import java.util.List;

/**
 * Created by Ori on 1/25/2018.
 */
@Dao
public interface ContactDao {

    @Query("SELECT * FROM Contact ORDER BY name ASC")
    LiveData<List<Contact>> getContacts();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<Contact> contacts);

    @Query("SELECT * FROM Contact")
    List<Contact> getAllContacts();

    @Query("delete from Contact where id like :contactId")
    void removeContact(String contactId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addContact(Contact contact);
}
