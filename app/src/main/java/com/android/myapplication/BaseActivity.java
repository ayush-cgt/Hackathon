package com.android.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.android.myapplication.utils.NetworkUtil;


/**
 * Created by Manish
 */
public abstract class BaseActivity extends AppCompatActivity {

    private MyBroadcastReceiver mReceiver = null;
    private IntentFilter intentFilter = null;
    private Snackbar snackbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResourceId());

        mReceiver = new MyBroadcastReceiver();
        intentFilter = new IntentFilter();
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        intentFilter.addAction(LocationManager.PROVIDERS_CHANGED_ACTION);
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(mReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(mReceiver);
    }


    protected abstract int getLayoutResourceId();

    protected android.support.v7.widget.Toolbar setUpToolBar() {
        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        return toolbar;
    }

    protected void navigationDrawerSetUp() {
        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, setUpToolBar(), R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer.setDrawerListener(toggle);
        toggle.syncState();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        if (drawer != null && drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    protected void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    protected void redirectTo(Class c) {
        Intent i = new Intent(this, c);
        startActivity(i);
    }

    protected void redirectTo(Class c, Bundle args) {
        Intent i = new Intent(this, c);
        i.putExtras(args);
        startActivity(i);
    }

    protected void redirectTo(Class c, Bundle args, int flag) {
        Intent i = new Intent(this, c);
        i.setFlags(flag);
        i.putExtras(args);
        startActivity(i);
    }


    class MyBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(final Context context, final Intent intent) {

            if (intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
                int conn = NetworkUtil.getConnectivityStatus(context);
                if (conn == NetworkUtil.TYPE_NOT_CONNECTED) {
                    snackbar = Snackbar
                            .make(getWindow().getDecorView(), "No internet connection!", Snackbar.LENGTH_INDEFINITE);

                    // Changing message text color
                    snackbar.setActionTextColor(Color.RED);
                    snackbar.show();
                } else if (snackbar != null) {
                    snackbar.dismiss();
                }
            }
        }
    }

    protected void hideKeyBoard() {
        InputMethodManager mgr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        mgr.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
    }

    protected void hideKeyBoard(View view) {
        InputMethodManager mgr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        mgr.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}