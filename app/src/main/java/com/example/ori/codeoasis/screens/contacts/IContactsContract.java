package com.example.ori.codeoasis.screens.contacts;

import com.example.ori.codeoasis.models.Contact;

import java.util.List;

/**
 * Created by Ori on 1/26/2018.
 */

public interface IContactsContract {
    interface View {

        void showProgressBar(boolean showProgress);

        void showErrorMessage();
    }

    interface Presenter {

        void removeContact(Contact contact);

        void setContactList(List<Contact> contacts);

        List<Contact> getContacts();

        void loadNewContacts();

        void addContact(Contact contact);
    }
}
