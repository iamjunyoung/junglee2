package com.bbeaggoo.junglee2.ui.main;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.Toast;

import com.bbeaggoo.junglee2.R;

import io.realm.Realm;


public class MainActivity extends AppCompatActivity {

    private final String TAG = "JYN";
    private ItemAdapter adapter;

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    private Context mContext;
    private CustomRecyclerView recyclerView;
    private Realm mRealm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main_grid);

        recyclerView = (CustomRecyclerView) findViewById(R.id.recyclerview);
        mContext = this;

        adapter = new ItemAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(this));

        //
        //recyclerView.setLayoutManager(new ItemLayoutManger(this));
        recyclerView.setLayoutManager(new GridItemLayoutManger(this, 2));

        recyclerView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Log.i("JYN", "RecyclerView onClick");
                hideKeyboard(view);
            }
        });

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        CollapsingToolbarLayout mCollapseToolbar = (CollapsingToolbarLayout)findViewById(R.id.collapseBar);
        mCollapseToolbar.setTitle("마이 타이틀");

    }

    public void hideKeyboard(View view) {
        InputMethodManager imm =(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    private RecyclerView getRecyclerView() {
        return recyclerView;
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
    public boolean onCreateOptionsMenu(Menu menu) {
        //super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu2, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        //menu.findItem(R.menu.menu2).setVisible(false);
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
}

