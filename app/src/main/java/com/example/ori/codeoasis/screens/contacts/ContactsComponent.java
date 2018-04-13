package com.example.ori.codeoasis.screens.contacts;

import com.example.ori.codeoasis.dagger.modules.scoups.ActivityScope;

import dagger.Subcomponent;

@ActivityScope
@Subcomponent(modules = {ContactsActivityModel.class})
public interface ContactsComponent {

    void inject(ContactsActivity contactsActivity);
}
