package com.bbeaggoo.junglee2.ui.main;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.bbeaggoo.junglee2.R;
import com.bbeaggoo.junglee2.common.BaseActivity;
import com.bbeaggoo.junglee2.datas.GridItem;
import com.bbeaggoo.junglee2.ui.CustomRecyclerView;
import com.bbeaggoo.junglee2.ui.DividerItemDecoration;
import com.bbeaggoo.junglee2.ui.GridItemLayoutManger;
import com.bbeaggoo.junglee2.ui.ItemAdapter;

import java.util.List;

import io.realm.Realm;


public class MainActivity extends BaseActivity implements MainMvpView {

    private final String TAG = "JYN";
    private ItemAdapter adapter;

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private Context mContext;
    private CustomRecyclerView recyclerView;
    private Realm mRealm;

    Boolean longTouched = false;
    Toolbar toolbar;
    Toolbar toolbarLongTouched;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main_grid);

        recyclerView = (CustomRecyclerView) findViewById(R.id.recyclerview);
        mContext = this;

        adapter = new ItemAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(this));

        //recyclerView.setLayoutManager(new ItemLayoutManger(this));
        recyclerView.setLayoutManager(new GridItemLayoutManger(this, 2));

        recyclerView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Log.i("JYN", "RecyclerView onClick");
                hideKeyboard(view);
            }
        });

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbarLongTouched = (Toolbar)findViewById(R.id.toolbarlongtouched);

        setSupportActionBar(toolbar);
    }

    public void hideKeyboard(View view) {
        InputMethodManager imm =(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    private RecyclerView getRecyclerView() {
        return recyclerView;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //getMenuInflater().inflate(R.menu.menu, menu);
        MenuInflater inflater = getMenuInflater();

        int color;
        int scrollFlg;
        int menuRes;

        Toolbar collapsingToolbar = (Toolbar) findViewById(R.id.toolbar);
        AppBarLayout.LayoutParams params = (AppBarLayout.LayoutParams) collapsingToolbar.getLayoutParams();

        if(!longTouched) {
            color = Color.parseColor("#0000FF");
            scrollFlg = AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS|AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL|AppBarLayout.LayoutParams.SCROLL_FLAG_SNAP; // list other flags here by |
            menuRes = R.menu.menu;
        } else {
            color = Color.parseColor("#FF00FF");
            scrollFlg = 0;
            menuRes = R.menu.menu_long_touched;
        }
        params.setScrollFlags(scrollFlg);
        collapsingToolbar.setLayoutParams(params);

        toolbar.setBackgroundColor(color);
        inflater.inflate(menuRes, menu);

        return super.onCreateOptionsMenu(menu);
    }

    public void setLongTouched(boolean longTouched) {
        this.longTouched = longTouched;
        invalidateOptionsMenu();
    }

    public boolean getLongTouched() {
        return this.longTouched;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        //menu.findItem(R.menu.menu).setVisible(false);
        Log.i("JYN", "onPrepareOptionsMenu");
        /*
        if (longTouched) {
            menu.findItem(R.id.action_settings).setVisible(false);
            menu.findItem(R.id.plus).setVisible(false);
            menu.findItem(R.id.share).setVisible(false);
            menu.findItem(R.id.trash).setVisible(false);
        }
        */
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        /*
        ArrayList<ListItem> selectedData = new ArrayList<>();
        for (int i = 0; i < listData.size(); i++) {
            if (listData.get(i).getChecked()) {
                selectedData.add(listData.get(i));
            }
        }
        */
        if (id == R.id.plus) {
            Toast.makeText(this, "plus 이벤트", Toast.LENGTH_SHORT).show();
            //categoryManager.showDialogForCategoryList(this);
            return true;
        } else if (id == R.id.share) {
            Toast.makeText(this, "share 이벤트", Toast.LENGTH_SHORT).show();
            //shareremoveSelectedItem(selectedData);
            return true;
        } else if (id == R.id.trash) {
            Toast.makeText(this, "trash 이벤트", Toast.LENGTH_SHORT).show();
            //removeSelectedItem(selectedData);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (!longTouched) {
            super.onBackPressed();
        } else {
            setLongTouched(false);
            adapter.longTouchSelectedItems.clear();
            adapter.notifyDataSetChanged();
        }
    }
    GestureDetectorCompat mGestureDetector = new GestureDetectorCompat(mContext, new GestureDetector.SimpleOnGestureListener() {

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {

            View childView = getRecyclerView().findChildViewUnder(e.getX(), e.getY());
            int position = getRecyclerView().getChildPosition(childView);

            Log.i(TAG, "hihi : " + position);
            //ItemClick

            return super.onSingleTapConfirmed(e);
        }

        @Override
        public void onLongPress(MotionEvent e) {

            View childView = getRecyclerView().findChildViewUnder(e.getX(), e.getY());
            int position = getRecyclerView().getChildPosition(childView);

            //LongClick

            super.onLongPress(e);
        }
    });

    @Override
    public void inject() {

    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void onUpdateJeongleeItemList(List<GridItem> jeongleeList) {

    }

    @Override
    public void onCreatedJeongleeItem(GridItem gridItem) {

    }

    @Override
    public void showEmtpyView() {

    }

    @Override
    public void onSuccessCreateSampes() {

    }


}

