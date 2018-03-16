package com.example.ori.codeoasis.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ori on 1/25/2018.
 */

public class ResponseServer {
    List<Contact> contacts = new ArrayList<>();

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(ArrayList<Contact> contacts) {
        this.contacts = contacts;
    }
}
