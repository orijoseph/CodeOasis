package com.example.ori.codeoasis.adapters;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ori.codeoasis.R;
import com.example.ori.codeoasis.models.Contact;

import java.util.List;

/**
 * Created by Ori on 1/25/2018.
 */

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.MyViewHolder> {

    private final List<Contact> mContacts;
    private final Context mCtx;
    private final IRecyclerCallbacks mActivity;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView nameTV;
        TextView phoneTV;
        ConstraintLayout row;

        public MyViewHolder(View view) {
            super(view);
            row = (ConstraintLayout) view.findViewById(R.id.contact_row_item);
            nameTV = (TextView) view.findViewById(R.id.contact_row_nameTV);
            phoneTV = (TextView) view.findViewById(R.id.contact_row_phoneTV);
        }
    }

    public ContactsAdapter(Context ctx, List<Contact> contacts, IRecyclerCallbacks callbacks) {
        mContacts = contacts;
        mCtx = ctx;
        mActivity =  callbacks;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_row, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Contact contact = mContacts.get(position);

        holder.nameTV.setText(contact.getName());
        holder.phoneTV.setText(contact.getPhone().getMobile());

        holder.row.setOnClickListener(view -> mActivity.onContactClicked(contact));
    }

    @Override
    public int getItemCount() {
        return mContacts.size();
    }

    public interface IRecyclerCallbacks{
        void onContactClicked(Contact contact);
    }
}
