package com.cardiomood.hoanglong;

import com.cardiomood.hoanglong.db.DatabaseHelperFactory;

public class Application extends android.app.Application {

    @Override
    public void onCreate() {
        super.onCreate();

        DatabaseHelperFactory.obtainHelper(this);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();

        DatabaseHelperFactory.releaseHelper();
    }
}
