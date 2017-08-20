package com.bbeaggoo.junglee2.ui.main;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.bbeaggoo.junglee2.R;

import io.realm.Realm;
import io.realm.RealmResults;


public class MainActivity extends AppCompatActivity {

    private final String TAG = "JYN";
    private ItemAdapter adapter;

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

    }

    public void hideKeyboard(View view) {
        InputMethodManager imm =(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_for_category_edit, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.add_category) {
            Toast.makeText(this, "Add category", Toast.LENGTH_SHORT).show();

            return true;
        }

        return super.onOptionsItemSelected(item);
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

}

