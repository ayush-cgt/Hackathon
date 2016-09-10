package com.android.myapplication.fragments;


import android.app.LoaderManager;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;

import com.android.myapplication.R;
import com.android.myapplication.common.Constants;
import com.android.myapplication.db.DataProviderContract;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentExpenses extends BaseFragment implements LoaderManager.LoaderCallbacks<Cursor> {

    private ListView mListView;
    private MyAdapter myAdapter = null;

    public FragmentExpenses() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_expenses, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mListView = (ListView) getView().findViewById(R.id.listExpenses);
        mListView.setEmptyView(getView().findViewById(android.R.id.empty));

        myAdapter = new MyAdapter(getActivity());
        mListView.setAdapter(myAdapter);
        mListView.setOnItemClickListener(onItemClickListener);
    }

    @Override
    public void onResume() {
        super.onResume();
        getLoaderManager().initLoader(Constants.LOADER_EXPENSES, null, this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(
                getActivity(),                                     // Context
                DataProviderContract.Expense.CONTENT_URI,       // Table to query
                DataProviderContract.Expense.PROJECTION,              // Projection to return
                null,                                              // No selection clause
                null,                                              // No selection arguments
                DataProviderContract.Expense.TITLE + " ASC"                                              // Default sort order
        );
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        //if (data.getCount() > 0) {
        myAdapter.changeCursor(data);
        //}
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        myAdapter.changeCursor(null);
    }

    private class MyAdapter extends CursorAdapter {
        private LayoutInflater mInflater;

        public MyAdapter(Context context) {
            super(context, null, false);
            mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public View newView(Context context, Cursor cursor, ViewGroup parent) {
            return mInflater.inflate(R.layout.fragment_expenses_item, null, false);
        }

        @Override
        public void bindView(View view, Context context, Cursor cursor) {

        }
    }

    AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        }
    };

}
