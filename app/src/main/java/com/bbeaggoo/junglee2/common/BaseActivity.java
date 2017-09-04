package com.bbeaggoo.junglee2.common;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bbeaggoo.junglee2.R;

public abstract class BaseActivity extends AppCompatActivity implements BaseMvpView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        inject();
        initPresenter();
    }

    /*
    public ApplicationComponent getApplicationComponet() {
        return ((KotlinWithAndroid) getApplication()).getComponent();
    }
    */
}
