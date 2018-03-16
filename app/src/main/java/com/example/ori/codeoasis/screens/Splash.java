package com.example.ori.codeoasis.screens;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.example.ori.codeoasis.R;
import com.example.ori.codeoasis.dataBase.ContactViewModel;
import com.example.ori.codeoasis.dataBase.ContactsDataBase;
import com.example.ori.codeoasis.models.Contact;
import com.example.ori.codeoasis.repo.UserRepo;
import com.example.ori.codeoasis.screens.contacts.ContactsActivity;
import com.example.ori.codeoasis.services.ApiContract;
import com.example.ori.codeoasis.services.WebService;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.List;

public class Splash extends AppCompatActivity implements ISplashContact.View {

    private static final int DELAY_TIME = 2000;
    private SplashPresenter mPresenter;
    private AVLoadingIndicatorView mProgressBar;
    private ContactViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        findViews();

        mPresenter = new SplashPresenter(this,
                UserRepo.getInstance(this, this),
                WebService.getClient().create(ApiContract.class),
                ContactsDataBase.get(this).getContactDao());

        mViewModel = ViewModelProviders.of(this).get(ContactViewModel.class);

        mViewModel.mContacts.observe(this, this::setRecycler);

        mPresenter.init();
    }

    private void findViews() {
        mProgressBar = findViewById(R.id.progress);
    }

    @Override
    public void showErrorMessage() {
        Toast.makeText(this, R.string.error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setRecycler(List<Contact> contacts) {
        if (contacts.size() == 0) return;

        Handler handler = new Handler();
        handler.postDelayed(() -> {
            Intent intent = new Intent(Splash.this, ContactsActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
            finish();
        }, DELAY_TIME);
    }

    @Override
    public void showProgressBar(boolean showProgressBar) {
        mProgressBar.setVisibility(showProgressBar ? View.VISIBLE : View.GONE);
    }
}
