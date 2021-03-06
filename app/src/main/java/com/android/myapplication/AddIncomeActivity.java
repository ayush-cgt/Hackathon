package com.android.myapplication;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.myapplication.common.ThresholdEditText;
import com.android.myapplication.module.Type;
import com.android.myapplication.db.DBHelper;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by sushil on 10/9/16.
 */
public class AddIncomeActivity extends BaseActivity {
    private DatePicker datePicker;
    private Calendar calendar;
    private TextView dateView;
    private int year, month, day;
    private TextView textSelectDate;
    private Spinner income, income_type, client, currency, mode;
    private EditText projectName;
    ThresholdEditText foreignCurrency, amountINR;
    private Button save;
    private TextView exchange;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_add_income;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setUpTool();
        setUI();
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        showDate(year);
    }

    private void setUI() {
        dateView = (TextView) findViewById(R.id.year);
        textSelectDate = (TextView) findViewById(R.id.selectYear);
        income_type = (Spinner) findViewById(R.id.income_type);
        client = (Spinner) findViewById(R.id.client);
        mode = (Spinner) findViewById(R.id.mode);
        currency = (Spinner) findViewById(R.id.currency);
        income = (Spinner) findViewById(R.id.income);
        projectName = (EditText) findViewById(R.id.input_project);
        foreignCurrency = (ThresholdEditText) findViewById(R.id.input_forign);
        amountINR = (ThresholdEditText) findViewById(R.id.input_amountinr);
        save = (Button) findViewById(R.id.saveIncome);
        exchange = (TextView) findViewById(R.id.exchange);
        textSelectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(999);
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()) {

                }
            }
        });

        foreignCurrency.setThresholdMillis(300);
        foreignCurrency.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        amountINR.setThresholdMillis(300);
        amountINR.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        CustomAdapter customAdapter = new CustomAdapter(getApplicationContext(), DBHelper.getInstance(AddIncomeActivity.this).getTypes());
        income.setAdapter(customAdapter);

        income.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void setUpTool() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.back_arrow));
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(getString(R.string.add_income));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                hideKeyBoard();
                AddIncomeActivity.this.finish();
            }
        });

    }


    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            return new DatePickerDialog(this, myDateListener, year, month, day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
            // TODO Auto-generated method stub
            // arg1 = year
            // arg2 = month
            // arg3 = day
            showDate(arg1);
        }
    };

    private void showDate(int year) {
        dateView.setText(new StringBuilder().append(year));
    }


    private boolean validate() {
        String project = projectName.getText().toString().trim();
        String foreignC = foreignCurrency.getText().toString().trim();
        String amountinr = amountINR.getText().toString().trim();


        if (TextUtils.isEmpty(project)) {
            showToast("Please enter project name");
            return false;
        } else if (TextUtils.isEmpty(foreignC)) {
            showToast("Please enter foreign currency");
            return false;
        } else if (TextUtils.isEmpty(amountinr)) {
            showToast("Please enter amount INR");
            return false;
        }

        return true;
    }

    public class CustomAdapter extends BaseAdapter {
        Context context;
        ArrayList<Type> type;
        LayoutInflater inflter;

        public CustomAdapter(Context applicationContext, ArrayList<Type> type) {
            this.context = applicationContext;
            this.type = type;
            inflter = (LayoutInflater.from(applicationContext));
        }

        @Override
        public int getCount() {
            return type.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            view = inflter.inflate(R.layout.customspinner, null);
            TextView names = (TextView) view.findViewById(R.id.textView);
            names.setText(type.get(i).getType());
            return view;
        }
    }

    public class CustomAdapterSubType extends BaseAdapter {
        Context context;
        ArrayList<Type> type;
        LayoutInflater inflter;

        public CustomAdapterSubType(Context applicationContext, ArrayList<Type> type) {
            this.context = applicationContext;
            this.type = type;
            inflter = (LayoutInflater.from(applicationContext));
        }

        @Override
        public int getCount() {
            return type.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            view = inflter.inflate(R.layout.customspinner, null);
            TextView names = (TextView) view.findViewById(R.id.textView);
            names.setText(type.get(i).getType());
            return view;
        }
    }
}
