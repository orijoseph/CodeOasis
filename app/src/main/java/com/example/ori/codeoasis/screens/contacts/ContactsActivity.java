package com.example.ori.codeoasis.screens.contacts;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.Toast;

import com.example.ori.codeoasis.R;
import com.example.ori.codeoasis.adapters.ContactsAdapter;
import com.example.ori.codeoasis.dataBase.ContactViewModel;
import com.example.ori.codeoasis.dataBase.ContactsDataBase;
import com.example.ori.codeoasis.helpers.Utils;
import com.example.ori.codeoasis.models.Contact;
import com.example.ori.codeoasis.services.ApiContract;
import com.example.ori.codeoasis.services.WebService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

public class ContactsActivity extends AppCompatActivity implements ContactsAdapter.IRecyclerCallbacks, IContactsContract.View {

    private RecyclerView mContactsRV;
    private ContactsAdapter mAdapter;
    private ContactViewModel mViewModel;
    private ContactsPresenter mPresenter;
    private AVLoadingIndicatorView mProgress;
    private ConstraintLayout mRoot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();

        Timber.plant(new Timber.DebugTree());

        HttpLoggingInterceptor httpLoggingInterceptor = new
                HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(@NonNull String message) {
                Timber.i(message);
            }
        });

        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient()
                .newBuilder()
                .addInterceptor(httpLoggingInterceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://randomuser.me/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        findViews();

        mPresenter = new ContactsPresenter(this,
                ContactsDataBase.get(this).getContactDao(),
                WebService.getClient().create(ApiContract.class));

        mViewModel = ViewModelProviders.of(this).get(ContactViewModel.class);

        mViewModel.mContacts.observe(this, this::setRecycler);
    }

    private void findViews() {
        mRoot = findViewById(R.id.contacts_root);
        mProgress = findViewById(R.id.progress);
        mContactsRV = findViewById(R.id.contactsRV);
        mContactsRV.setLayoutManager(new LinearLayoutManager(this));
    }

    public void setRecycler(List<Contact> contacts) {
        if (contacts.size() == 0) showReloadContactsDialog();

        mPresenter.setContactList(contacts);
        DividerItemDecoration dividerDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        mContactsRV.addItemDecoration(dividerDecoration);
        mAdapter = new ContactsAdapter(this, contacts, this);
        mContactsRV.setAdapter(mAdapter);
        itemTouchHelper.attachToRecyclerView(mContactsRV);
    }

    private void showReloadContactsDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage(R.string.load_new_message)
                .setTitle(R.string.no_contacts_title);
        builder.setNegativeButton(android.R.string.cancel, (dialogInterface, i) -> dialogInterface.dismiss());
        builder.setPositiveButton(android.R.string.ok, (dialogInterface, i) -> {
            mPresenter.loadNewContacts();
            dialogInterface.dismiss();
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public void onContactClicked(Contact contact) {
        Utils.callNumber(this, contact.getPhone().getMobile());
    }

    @Override
    public void showProgressBar(boolean showProgress) {
        mProgress.setVisibility(showProgress ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showErrorMessage() {
        Toast.makeText(this, R.string.error, Toast.LENGTH_SHORT).show();
    }

    ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
            showUndoSnackBar(mPresenter.getContacts().get(viewHolder.getAdapterPosition()));
            mPresenter.removeContact(mPresenter.getContacts().get(viewHolder.getAdapterPosition()));
            mAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());

        }
    });

    private void showUndoSnackBar(Contact contact) {
        Snackbar snackbar = Snackbar
                .make(mRoot, contact.getName() + " has been deleted", Snackbar.LENGTH_LONG)
                .setAction("UNDO",
                        view -> mPresenter.addContact(contact)).addCallback(new Snackbar.Callback() {
                });
        snackbar.show();
    }
}
