package com.cardiomood.hoanglong.db;

import android.content.Context;

import com.j256.ormlite.android.apptools.OpenHelperManager;

public class DatabaseHelperFactory {

    private static DatabaseHelper databaseHelper;

    public static DatabaseHelper getHelper() {
        return databaseHelper;
    }

    public synchronized static void obtainHelper(Context context) {
        databaseHelper = OpenHelperManager.getHelper(context, DatabaseHelper.class);
    }

    public synchronized static void releaseHelper() {
        OpenHelperManager.releaseHelper();
        databaseHelper = null;
    }

}
