package com.android.myapplication.fragments;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.Snackbar;
import android.support.v13.app.FragmentCompat;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import com.android.myapplication.R;
import com.android.myapplication.db.DBHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by Gaurav Gupta
 */
public class AddClientFragment extends BaseFragment {

    private EditText input_client_name, input_company, input_country, input_city, input_zip,
            input_address, input_phone, input_email;
    private AppCompatButton btn_create;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_add_client, container, false);

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

        btn_create = (AppCompatButton) getView().findViewById(R.id.btn_create);

        btn_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate();
            }
        });
    }

    private class insertTask extends AsyncTask<String, Void, String> {
        protected String doInBackground(String... urls) {
            // insert data here
            DBHelper.getInstance(getActivity()).insertClient(urls[0], urls[1], urls[2], urls[3],urls[4],
                    urls[5], urls[6], urls[7]);
            return "";
        }

        protected void onPostExecute(String result) {
            getActivity().finish();
        }
    }

    private boolean validate() {
        String client_name = input_client_name.getText().toString().trim();
        String company = input_company.getText().toString().trim();
        String email = input_email.getText().toString().trim();
        String country = input_country.getText().toString().trim();
        String city = input_city.getText().toString().trim();
        String zip = input_zip.getText().toString().trim();
        String address = input_address.getText().toString().trim();
        String phone = input_phone.getText().toString();

        if (TextUtils.isEmpty(client_name)) {
            showToast("Please enter client name");
            return false;
        } else if (TextUtils.isEmpty(company)) {
            showToast("Please enter company");
            return false;
        } else if (TextUtils.isEmpty(email)) {
            showToast("Please enter email address");
            return false;
        } else if (!varifyEmail(email)) {
            showToast("Please enter valid email address");
            return false;
        } else if (TextUtils.isEmpty(country)) {
            showToast("Please enter country");
            return false;
        } else if (TextUtils.isEmpty(city)) {
            showToast("Please enter city");
            return false;
        } else if (TextUtils.isEmpty(zip)) {
            showToast("Please enter zip");
            return false;
        } else if (TextUtils.isEmpty(address)) {
            showToast("Please enter address");
            return false;
        } else if (TextUtils.isEmpty(phone)) {
            showToast("Please enter phone");
            return false;
        }

        new insertTask().execute(client_name, company, country, city, zip, address, phone, email);

        return true;
    }


    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    private boolean varifyEmail(String email) {
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

}
