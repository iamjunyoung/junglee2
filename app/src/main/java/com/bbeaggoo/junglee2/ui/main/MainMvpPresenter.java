package com.bbeaggoo.junglee2.ui.main;

import com.bbeaggoo.junglee2.common.BaseMvpPresenter;
import com.bbeaggoo.junglee2.common.BaseMvpView;

/**
 * Created by junyoung on 2017. 9. 4..
 */

public interface MainMvpPresenter<MvpView extends BaseMvpView> extends BaseMvpPresenter<MvpView> {

    void loadJeongleeItemList(boolean isFirst);

    void insert(int id);//id여야 하니? title등 다른거여야 하니?

    void checked(int id, boolean isChecked); //필요하니?

    void searchQuery(String text);

    void searchFinish();

}
