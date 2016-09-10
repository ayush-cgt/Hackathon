package com.liveeasy.db;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.text.TextUtils;

import com.liveeasy.R;
import com.liveeasy.common.Constants;
import com.liveeasy.common.DateTimeUtil;
import com.liveeasy.common.Preference;
import com.liveeasy.common.Util;
import com.liveeasy.downloader.DownloadManager;
import com.liveeasy.model.Comment;
import com.liveeasy.model.FeedBean;
import com.liveeasy.utils.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Manish
 */
public class DBHelper {

    private Context context = null;
    private static DBHelper instance = null;

    private DBHelper(Context context) {
        this.context = context.getApplicationContext();
    }

    public static DBHelper getInstance(Context context) {
        if (instance == null) {
            instance = new DBHelper(context);
        }

        return instance;
    }


}
