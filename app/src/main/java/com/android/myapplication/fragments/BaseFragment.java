package com.android.myapplication.fragments;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

public abstract class BaseFragment extends Fragment {

    private ProgressDialog progressDialog = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    protected void showToast(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    protected void redirectTo(Class c) {
        Intent i = new Intent(getActivity(), c);
        startActivity(i);
    }

    protected void redirectTo(Class c, Bundle args) {
        Intent i = new Intent(getActivity(), c);
        i.putExtras(args);
        startActivity(i);
    }

    protected void redirectTo(Class c, Bundle args, int flag) {
        Intent i = new Intent(getActivity(), c);
        i.setFlags(flag);
        i.putExtras(args);
        startActivity(i);
    }


    public interface ICommunication {
        public void replaceFragment(int type);
    }

    public void applyColorFilter(Drawable drawable, int color) {
        drawable.setColorFilter(color, PorterDuff.Mode.SRC_ATOP);
    }

    protected void hideKeyboard() {
        InputMethodManager mgr = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        mgr.hideSoftInputFromWindow(
                getActivity().getWindow().getDecorView().getWindowToken(), 0);
    }

    protected void showProgress(boolean show, String msg) {
        if (show) {
            if (progressDialog == null) {
                progressDialog = new ProgressDialog(getActivity());
            }

            progressDialog.setMessage(msg);
            progressDialog.setCancelable(false);
            progressDialog.show();
        } else {
            progressDialog.dismiss();
        }
    }
}
