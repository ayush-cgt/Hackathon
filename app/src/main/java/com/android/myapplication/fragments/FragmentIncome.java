package com.android.myapplication.fragments;

import android.app.LoaderManager;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.myapplication.R;
import com.android.myapplication.common.Constants;
import com.android.myapplication.common.Preference;
import com.android.myapplication.db.DataProviderContract;

/**
 * Created by sushil on 10/9/16.
 */
public class FragmentIncome extends BaseFragment implements LoaderManager.LoaderCallbacks<Cursor> {

    private ListView mListView;
    private NotificationAdapter notificationAdapter = null;

    public FragmentIncome() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_income, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        mListView = (ListView) getView().findViewById(R.id.listView);
        mListView.setEmptyView(getView().findViewById(android.R.id.empty));

        notificationAdapter = new NotificationAdapter(getActivity());
        mListView.setAdapter(notificationAdapter);
        mListView.setOnItemClickListener(onItemClickListener);
    }

    @Override
    public void onResume() {
        super.onResume();
        getLoaderManager().initLoader(Constants.LOADER_CLIENTS, null, this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(
                getActivity(),                                     // Context
                DataProviderContract.Income.CONTENT_URI,       // Table to query
                DataProviderContract.Income.PROJECTION,              // Projection to return
                null,                                              // No selection clause
                null,                                              // No selection arguments
                null                                              // Default sort order
        );
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        if (data != null && data.getCount() > 0) {
            notificationAdapter.changeCursor(data);

            // Reload notification when new push arrives
            boolean reload = Preference.getInstance(getActivity()).getBoolean(Constants.PREF_KEY_RELOAD_NOTIFICATIONS);
            if (reload) {
                //showLoader();
                //fetchData();
                Preference.getInstance(getActivity()).put(Constants.PREF_KEY_RELOAD_NOTIFICATIONS, false);
            }
        } else {
            //showLoader();
            //fetchData();
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        notificationAdapter.changeCursor(null);
    }


    private class NotificationAdapter extends CursorAdapter {
        private LayoutInflater mInflater;

        public NotificationAdapter(Context context) {
            super(context, null, false);
            mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public View newView(Context context, Cursor cursor, ViewGroup parent) {
            return mInflater.inflate(R.layout.fragment_client_item, null, false);
        }

        @Override
        public void bindView(View view, Context context, Cursor cursor) {


            TextView tv_clientName = (TextView) view.findViewById(R.id.tv_UserName);
            TextView tv_company = (TextView) view.findViewById(R.id.tv_UserDetail);

            String name = cursor.getString(cursor.getColumnIndex(DataProviderContract.Income.PROJECT));
            String company = cursor.getString(cursor.getColumnIndex(DataProviderContract.Income.AMOUNT_INR));

            tv_clientName.setText(name);
            tv_company.setText(company);

        }
    }

    AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        }
    };


}