package com.bbeaggoo.junglee2.ui.main;

import com.bbeaggoo.junglee2.common.BaseMvpView;
import com.bbeaggoo.junglee2.common.RxPresenter;

import io.reactivex.subjects.PublishSubject;
import io.realm.Realm;

/**
 * Created by junyoung on 2017. 9. 4..
 */

public class MainMvpPresenterImpl <MvpView extends BaseMvpView> extends RxPresenter
        implements MainMvpPresenter<MvpView> {
    private MainMvpView view;

    private Realm realm;

    private PublishSubject<String> searchTextChangeSubject = PublishSubject.create();


    @Override
    public void attachView(MvpView view) {

    }

    @Override
    public void destroy() {

    }



    @Override
    public void loadJeongleeItemList(boolean isFirst) {

    }

    @Override
    public void insert(int id) {

    }

    @Override
    public void checked(int id, boolean isChecked) {

    }

    @Override
    public void searchQuery(String text) {

    }

    @Override
    public void searchFinish() {

    }
}
