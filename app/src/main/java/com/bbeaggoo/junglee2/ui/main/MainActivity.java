package com.bbeaggoo.junglee2.ui.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.bbeaggoo.junglee2.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_grid);


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
