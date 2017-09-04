package com.bbeaggoo.junglee2.ui.main;

import com.bbeaggoo.junglee2.common.BaseMvpView;
import com.bbeaggoo.junglee2.datas.GridItem;

import java.util.List;

/**
 * Created by junyoung on 2017. 9. 4..
 */

public interface MainMvpView extends BaseMvpView {
    void onUpdateJeongleeItemList(List<GridItem> jeongleeList);

    void onCreatedJeongleeItem(GridItem gridItem);

    void showEmtpyView();

    void onSuccessCreateSampes();
}
