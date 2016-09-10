package com.android.myapplication.fragments;


import android.Manifest;
import android.content.ContentValues;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v13.app.FragmentCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.widget.PopupMenu;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.myapplication.R;
import com.android.myapplication.common.Constants;
import com.android.myapplication.db.DBHelper;
import com.android.myapplication.db.DataProviderContract;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentAddExpense extends BaseFragment {

    private Button btnAddInvoice, btnAddExpense = null;
    private Spinner spinnerType, spinnerSubType;

    public FragmentAddExpense() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_expense, container, false);
    }

    private void showAttachOptions(View view) {
        PopupMenu menu = new PopupMenu(getActivity(), view);
        menu.inflate(R.menu.menu_attachment);
        menu.setOnMenuItemClickListener(onAttachMenuItemClickListener);
        menu.setGravity(Gravity.CENTER | Gravity.BOTTOM);
        menu.show();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //mFragment = new FragmentAttachImage(getActivity(), this, 320, 480);

        btnAddInvoice = (Button) getView().findViewById(R.id.addInvoice);
        btnAddInvoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPermissionGranted(Constants.PERMISSION_REQUEST_CODE_CAMERA, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE})) {
                    showAttachOptions(btnAddInvoice);
                }
            }
        });

        btnAddExpense = (Button) getView().findViewById(R.id.btnAdd);
        btnAddExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = ((EditText) getView().findViewById(R.id.txtTitle)).getText().toString();
                String amount = ((EditText) getView().findViewById(R.id.txtAmount)).getText().toString();
                Cursor cursor = (Cursor) spinnerSubType.getAdapter().getItem(spinnerSubType.getSelectedItemPosition());
                String id = cursor.getString(1);


                ContentValues values = new ContentValues();
                values.put(DataProviderContract.Expense.TITLE, title);
                values.put(DataProviderContract.Expense.AMOUNT, amount);
                values.put(DataProviderContract.Expense.SUBTYPE_ID, id);

                DBHelper.getInstance(getActivity()).addExpense(values);

                getActivity().finish();
            }
        });

        spinnerType = (Spinner) getView().findViewById(R.id.spinType);
        spinnerSubType = (Spinner) getView().findViewById(R.id.spinSubType);

        Cursor cursor = getActivity().getContentResolver().query(DataProviderContract.ExpenseMaster.CONTENT_URI,
                DataProviderContract.ExpenseMaster.PROJECTION,
                null,
                null,
                null);

        if (cursor.getCount() > 0) {
            bindMasterExpenses(cursor);
        } else {
            cursor.close();
        }
    }

    public boolean isPermissionGranted(int permissionCode, String... permissions) {
        if (Build.VERSION.SDK_INT >= 23) {
            int length = permissions.length;
            for (int i = 0; i < length; i++) {
                if (ContextCompat.checkSelfPermission(getActivity(), permissions[i]) == PackageManager.PERMISSION_GRANTED) {
                } else {
                    FragmentCompat.requestPermissions(FragmentAddExpense.this, permissions, permissionCode);
                    return false;
                }
            }
            return true;
        } else { //permission is automatically granted on sdk<23 upon installation
            return true;
        }
    }

    PopupMenu.OnMenuItemClickListener onAttachMenuItemClickListener = new PopupMenu.OnMenuItemClickListener() {

        @Override
        public boolean onMenuItemClick(MenuItem item) {

            switch (item.getItemId()) {
                case R.id.actionCamera:
                    //mFragment.openCameraForImage(FragmentAddExpense.this);
                    break;

                case R.id.actionGallery:
                    //mFragment.openGalleryForImage(FragmentAddExpense.this);
                    break;
            }
            return true;
        }
    };

    /*@Override
    public void onImageSelected(String imagePath) {

    }*/

    private void bindMasterExpenses(Cursor cursor) {
        try {
            // Columns from DB to map into the view file
            String[] fromColumns = {DataProviderContract.ExpenseMaster.TYPE, DataProviderContract.ExpenseMaster._ID};
            // View IDs to map the columns (fetched above) into
            int[] toViews = {R.id.text1, R.id.text2};

            SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                    getActivity(), // context
                    R.layout.spinner_item, // layout file
                    cursor, // DB cursor
                    fromColumns, // data to bind to the UI
                    toViews, // views that'll represent the data from `fromColumns`
                    0
            );

            adapter.setDropDownViewResource(R.layout.spinner_item_dropdown);
            spinnerType.setAdapter(adapter);

            spinnerType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    TextView textView = (TextView) view.findViewById(R.id.text2);
                    String expenseType = textView.getText().toString();

                    Cursor cursor = getActivity().getContentResolver().query(DataProviderContract.ExpenseSubType.CONTENT_URI,
                            DataProviderContract.ExpenseSubType.PROJECTION,
                            DataProviderContract.ExpenseSubType.MASTER_ID + "= ?",
                            new String[]{expenseType},
                            null);

                    if (cursor.getCount() > 0) {
                        bindSubExpenses(cursor);
                    } else {
                        cursor.close();
                        spinnerSubType.setAdapter(null);
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void bindSubExpenses(Cursor cursor) {
        try {
            // Columns from DB to map into the view file
            String[] fromColumns = {DataProviderContract.ExpenseSubType.NAME, DataProviderContract.ExpenseSubType._ID};
            // View IDs to map the columns (fetched above) into
            int[] toViews = {R.id.text1, R.id.text2};

            SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                    getActivity(), // context
                    R.layout.spinner_item, // layout file
                    cursor, // DB cursor
                    fromColumns, // data to bind to the UI
                    toViews, // views that'll represent the data from `fromColumns`
                    0
            );

            adapter.setDropDownViewResource(R.layout.spinner_item_dropdown);
            spinnerSubType.setAdapter(adapter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
