package com.bbeaggoo.junglee2.common;

/**
 * Created by junyoung on 2017. 8. 14..
 */

public interface BaseMvpPresenter<T extends BaseMvpView> {
    void attachView(T view);

    void destroy();
}
