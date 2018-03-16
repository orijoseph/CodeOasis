package com.example.ori.codeoasis.helpers;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

/**
 * Created by Ori on 1/25/2018.
 */

public class Utils {
    public static void callNumber(Activity sender, String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        sender.startActivity(intent);
    }
}
