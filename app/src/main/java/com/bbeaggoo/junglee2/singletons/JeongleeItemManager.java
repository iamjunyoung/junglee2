package com.bbeaggoo.junglee2.singletons;

import android.util.Log;

import com.bbeaggoo.junglee2.ui.main.GridItem;

import io.realm.Case;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by junyoung on 2017. 8. 18..
 */

public class JeongleeItemManager {

    /*
    public static RealmResults<Todo> getTodoList(Realm realm) {
        return realm.where(Todo.class).findAllSorted("id");
    }

    public static Todo load(Realm realm, int id) {
        return realm.where(Todo.class).equalTo("id", id).findFirst();
    }

    public static void createSamleTodo(Realm realm) {
        realm.beginTransaction();
        for (int i = 0; i < 10; i++) {
            Todo todo = realm.createObject(Todo.class, i);
            todo.setChecked(false);
            todo.setBody("Todo " + i);
            todo.setCreatedAt(Calendar.getInstance().getTime());
        }
        realm.commitTransaction();
    }

    public static int getMaxId(Realm realm) {
        return realm.where(Todo.class)
                .max("id")
                .intValue();
    }

    public static RealmResults<Todo> search(Realm realm, String text) {
        return realm.where(Todo.class)
                .contains("body", text, Case.INSENSITIVE)
                .findAll();
    }
    */

    /**
     * 데이터 초기화
     */















    /**
     * 유저 정보 데이터 리스트 반환
     */
    public static RealmResults<GridItem> getJeongleeItemList(Realm mRealm){
        return mRealm.where(GridItem.class).findAll();
    }


    /**
     * 유저 정보 데이터 DB 저장
     */
    public static void insertTestJeongleeItemData(Realm mRealm){

        mRealm.beginTransaction();
        GridItem gridItem = mRealm.createObject(GridItem.class);
        gridItem.setTitle("네이버");
        gridItem.setUrl("naver");

        GridItem gridItem2 = mRealm.createObject(GridItem.class);
        gridItem.setTitle("다음");
        gridItem.setUrl("daum");

        GridItem gridItem3 = mRealm.createObject(GridItem.class);
        gridItem.setTitle("구글");
        gridItem.setUrl("google");

        GridItem gridItem4 = mRealm.createObject(GridItem.class);
        gridItem.setTitle("야후");
        gridItem.setUrl("yahoo");

        GridItem gridItem5 = mRealm.createObject(GridItem.class);
        gridItem.setTitle("오픈그록");
        gridItem.setUrl("opengrok");

        GridItem gridItem6 = mRealm.createObject(GridItem.class);
        gridItem.setTitle("게릿");
        gridItem.setUrl("gerrit");

        GridItem gridItem7 = mRealm.createObject(GridItem.class);
        gridItem.setTitle("콜렙");
        gridItem.setUrl("collab");

        mRealm.commitTransaction();
    }


}
