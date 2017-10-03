package com.cardiomood.hoanglong;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.cardiomood.hoanglong.fragments.TranslationFragment;


public class MainActivity extends ActionBarActivity {

    private boolean suppressBackButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        TranslationFragment inputFrag= new TranslationFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, inputFrag,"translationFragment")
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onBackPressed() {
        if (!suppressBackButton) {
            super.onBackPressed();
        }
    }

}
