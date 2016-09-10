package com.android.myapplication;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.android.myapplication.fragments.AddClientFragment;
import com.android.myapplication.fragments.ClientDetailFragment;

/**
 * Created by Abhishek Madhur on 10-09-2016.
 */
public class ClientDetailActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.back_arrow));
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(getString(R.string.client_detail));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                hideKeyBoard();
                ClientDetailActivity.this.finish();
            }
        });

        FragmentManager frManager = getFragmentManager();
        ClientDetailFragment fragment = new ClientDetailFragment();
        frManager.beginTransaction().add(R.id.container, fragment).commit();
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_common;
    }
}