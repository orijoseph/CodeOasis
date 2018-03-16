package com.example.ori.codeoasis.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

/**
 * Created by Ori on 1/25/2018.
 */
@Entity
public class Phone implements Parcelable {
    @PrimaryKey
    @NonNull
    String phoneId;
    String mobile;
    String home;
    String office;

//    public Phone(String mobile, String home, String office) {
//        this.mobile = mobile;
//        this.home = home;
//        this.office = office;
//    }


    @NonNull
    public String getPhoneId() {
        return phoneId;
    }

    public void setPhoneId(@NonNull String phoneId) {
        this.phoneId = phoneId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mobile);
        dest.writeString(this.home);
        dest.writeString(this.office);
    }

    public Phone() {
    }

    protected Phone(Parcel in) {
        this.mobile = in.readString();
        this.home = in.readString();
        this.office = in.readString();
    }

    public static final Parcelable.Creator<Phone> CREATOR = new Parcelable.Creator<Phone>() {
        @Override
        public Phone createFromParcel(Parcel source) {
            return new Phone(source);
        }

        @Override
        public Phone[] newArray(int size) {
            return new Phone[size];
        }
    };
}
