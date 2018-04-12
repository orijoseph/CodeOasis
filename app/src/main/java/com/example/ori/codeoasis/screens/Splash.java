package com.example.ori.codeoasis.screens;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.example.ori.codeoasis.MyApplication;
import com.example.ori.codeoasis.R;
import com.example.ori.codeoasis.dagger.modules.SplashActivityModule;
import com.example.ori.codeoasis.dagger.modules.component.DaggerActivityComponent;
import com.example.ori.codeoasis.dataBase.ContactDao;
import com.example.ori.codeoasis.screens.contacts.ContactsActivity;
import com.example.ori.codeoasis.services.ApiContract;
import com.wang.avi.AVLoadingIndicatorView;

import javax.inject.Inject;

public class Splash extends AppCompatActivity implements ISplashContact.View {

    @Inject
    SplashPresenter mPresenter;

    private static final int DELAY_TIME = 2000;
    private AVLoadingIndicatorView mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        DaggerActivityComponent.builder()
                .appComponent(((MyApplication) getApplication()).getmAppComponent())
                .splashActivityModule(new SplashActivityModule(this))
                .build().inject(this);

        findViews();

        mPresenter.start();
    }

    private void findViews() {
        mProgressBar = findViewById(R.id.progress);
    }

    @Override
    public void showErrorMessage() {
        Toast.makeText(this, R.string.error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgressBar(boolean showProgressBar) {
        mProgressBar.setVisibility(showProgressBar ? View.VISIBLE : View.GONE);
    }

    @Override
    public void goToNextScreen() {
        Handler handler = new Handler();
        handler.postDelayed(() -> {
            Intent intent = new Intent(Splash.this, ContactsActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
            finish();
        }, DELAY_TIME);
    }
}
