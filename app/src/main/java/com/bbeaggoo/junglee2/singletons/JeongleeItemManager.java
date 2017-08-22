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
        GridItem gridItem = mRealm.createObject(GridItem.class, "네이버");
        //gridItem.setTitle("네이버");
        gridItem.setUrl("naver");

        GridItem gridItem2 = mRealm.createObject(GridItem.class, "다음");
        //gridItem2.setTitle("다음");
        gridItem2.setUrl("daum");

        GridItem gridItem3 = mRealm.createObject(GridItem.class, "구글");
        //gridItem3.setTitle("구글");
        gridItem3.setUrl("google");

        GridItem gridItem4 = mRealm.createObject(GridItem.class, "야후");
        //gridItem4.setTitle("야후");
        gridItem4.setUrl("yahoo");

        GridItem gridItem5 = mRealm.createObject(GridItem.class, "오픈그록");
        //gridItem5.setTitle("오픈그록");
        gridItem5.setUrl("opengrok");

        GridItem gridItem6 = mRealm.createObject(GridItem.class, "게릿");
        //gridItem6.setTitle("게릿");
        gridItem6.setUrl("gerrit");

        GridItem gridItem7 = mRealm.createObject(GridItem.class, "콜렙");
        //gridItem7.setTitle("콜렙");
        gridItem7.setUrl("collab");

        mRealm.commitTransaction();
    }


}
