package com.android.myapplication.fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.android.myapplication.R;
import com.android.myapplication.db.DBHelper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by Gaurav Gupta
 */
public class ClientDetailFragment extends BaseFragment {

    private EditText input_client_name, input_company, input_country, input_city, input_zip,
            input_address, input_phone, input_email;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_client_detail, container, false);

        return mView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        input_client_name = (EditText) getView().findViewById(R.id.input_client_name);
        input_company = (EditText) getView().findViewById(R.id.input_company);
        input_email = (EditText) getView().findViewById(R.id.input_email);
        input_country = (EditText) getView().findViewById(R.id.input_country);
        input_city = (EditText) getView().findViewById(R.id.input_city);
        input_zip = (EditText) getView().findViewById(R.id.input_zip);
        input_address = (EditText) getView().findViewById(R.id.input_address);
        input_phone = (EditText) getView().findViewById(R.id.input_phone);



    }


}
