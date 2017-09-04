package com.bbeaggoo.junglee2.ui;

/**
 * Created by wlsdud.choi on 2016-04-04.
 */
public interface ItemTouchHelperListener {
    boolean onItemMove(int fromPosition, int toPosition);
    void onItemRemove(int position);

    void onItemSwipe(int position);
}
