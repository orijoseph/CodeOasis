package com.example.ori.codeoasis.repo;

import android.content.Context;

/**
 * Created by Ori on 1/25/2018.
 */

public class UserRepo {

    private static UserRepo _INSTANCE = null;
    private static Context _context;

    private UserRepo(Context context) {
        _context = context.getApplicationContext();
    }

    public static UserRepo getInstance(Context context, Object tag) {
        synchronized (UserRepo.class) {
            if (_INSTANCE == null) {
                _context = context;
                _INSTANCE = new UserRepo(context.getApplicationContext());
            }
            return _INSTANCE;
        }
    }
}
