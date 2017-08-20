package com.bbeaggoo.junglee2;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by junyoung on 2017. 8. 15..
 */

public class JeongleeAndroid extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        initRealm();
    }

    /*
    초기화
    Realm을 사용하기 전에, 반드시 초기화해야 한다.
    Realm.init() 메서드를 이용해 초기화한다. 파라미터로 Context를 받는다.
     */
    private void initRealm() {
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder().build();
        Realm.setDefaultConfiguration(config);
    }
}
