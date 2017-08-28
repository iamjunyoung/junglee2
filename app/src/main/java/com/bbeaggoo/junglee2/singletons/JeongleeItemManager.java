package com.bbeaggoo.junglee2.singletons;

import com.bbeaggoo.junglee2.ui.main.GridItem;

import java.util.Calendar;

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

    public static RealmResults<GridItem> getJeongleeItemList(Realm mRealm){
        return mRealm.where(GridItem.class).findAll();
    }


    /**
     * 유저 정보 데이터 DB 저장
     */
    public static void insertTestJeongleeItemData(Realm mRealm){

        mRealm.beginTransaction();
        //GridItem gridItem = mRealm.createObject(GridItem.class);
        GridItem gridItem = mRealm.createObject(GridItem.class, "네이버");
        //gridItem.setTitle("네이버");
        gridItem.setUrl("naver");
        gridItem.setCreatedAt(Calendar.getInstance().getTime());

        //GridItem gridItem2 = mRealm.createObject(GridItem.class);
        GridItem gridItem2 = mRealm.createObject(GridItem.class, "다음");
        //gridItem2.setTitle("다음");
        gridItem2.setUrl("daum");
        gridItem2.setCreatedAt(Calendar.getInstance().getTime());

        //GridItem gridItem3 = mRealm.createObject(GridItem.class);
        GridItem gridItem3 = mRealm.createObject(GridItem.class, "구글");
        //gridItem3.setTitle("구글");
        gridItem3.setUrl("google");
        gridItem3.setCreatedAt(Calendar.getInstance().getTime());

        //GridItem gridItem4 = mRealm.createObject(GridItem.class);
        GridItem gridItem4 = mRealm.createObject(GridItem.class, "야후");
        //gridItem4.setTitle("야후");
        gridItem4.setUrl("yahoo");
        gridItem4.setCreatedAt(Calendar.getInstance().getTime());

        //GridItem gridItem5 = mRealm.createObject(GridItem.class);
        GridItem gridItem5 = mRealm.createObject(GridItem.class, "오픈그록");
        //gridItem5.setTitle("오픈그록");
        gridItem5.setUrl("opengrok");
        gridItem5.setCreatedAt(Calendar.getInstance().getTime());

        //GridItem gridItem6 = mRealm.createObject(GridItem.class);
        GridItem gridItem6 = mRealm.createObject(GridItem.class, "게릿");
        //gridItem6.setTitle("게릿");
        gridItem6.setUrl("gerrit");
        gridItem6.setCreatedAt(Calendar.getInstance().getTime());

        GridItem gridItem7 = mRealm.createObject(GridItem.class, "콜렙");
        gridItem7.setUrl("collab");
        gridItem7.setCreatedAt(Calendar.getInstance().getTime());

        GridItem gridItem8 = mRealm.createObject(GridItem.class, "콜렙2");
        gridItem8.setUrl("collab2");
        gridItem8.setCreatedAt(Calendar.getInstance().getTime());

        GridItem gridItem9 = mRealm.createObject(GridItem.class, "콜렙3");
        gridItem9.setUrl("collab3");
        gridItem9.setCreatedAt(Calendar.getInstance().getTime());

        GridItem gridItem10 = mRealm.createObject(GridItem.class, "콜렙4");
        gridItem10.setUrl("collab4");
        gridItem10.setCreatedAt(Calendar.getInstance().getTime());

        GridItem gridItem11 = mRealm.createObject(GridItem.class, "콜렙5");
        gridItem11.setUrl("collab5");
        gridItem11.setCreatedAt(Calendar.getInstance().getTime());

        GridItem gridItem12 = mRealm.createObject(GridItem.class, "콜렙6");
        gridItem12.setUrl("collab6");
        gridItem12.setCreatedAt(Calendar.getInstance().getTime());

        mRealm.commitTransaction();
    }


}
