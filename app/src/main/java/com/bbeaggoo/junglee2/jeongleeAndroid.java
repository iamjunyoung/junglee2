package com.bbeaggoo.junglee2;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by junyoung on 2017. 8. 15..
 */

public class jeongleeAndroid extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        initRealm();
    }

    private void initRealm() {
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder().build();
        Realm.setDefaultConfiguration(config);
    }
}
