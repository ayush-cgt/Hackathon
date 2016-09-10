package com.android.myapplication.common;

/**
 * Created by Manish Rupani
 */

public class Constants {

    public static final int SUCCESS = 1;
    public static final int INVALID_REQUEST = 2;
    public static final int ALREADY_ADDED = 3;
    public static final int DATA_NOT_FOUND = 9;
    public static final int TOKEN_EXPIRED = 12;
    public static final int BLANK_TOKEN = 13;
    public static final int INCORRECT_OLD_PASSWORD = 15;
    public static final int USER_BLOCK = 19;
    public static final int NO_RESULT = 504;
    public static final int WRONG_CREDENTIALS = 506;
    public static final int SESSION_EXPIRE = 507;
    public static final int REQUEST_TIMEOUT = 508;

    public static final int FAILURE_RESULT = 3;
    public static final int SUCCESS_RESULT = 4;
    public static final String KEY_CURRENT_LAT = "current_lat";
    public static final String KEY_CURRENT_LONG = "current_long";

    public static final String KEY_DEVICE_TYPE_VALUE = "1";
    public static final String KEY_LOGIN_AS_GUEST = "1";
    public static final String KEY_LOGIN_AS_APP = "2";
    public static final String KEY_LOGIN_AS_FB = "3";


    // User API
    public static final String KEY_NAME = "name";
    public static final String KEY_FIRST_NAME = "first_name";
    public static final String KEY_LAST_NAME = "last_name";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_GENDER = "gender";
    public static final String KEY_DEVICE_ID = "device_id";
    public static final String KEY_DEVICE_TYPE = "device_type";
    public static final String KEY_PUSH_TOKEN = "push_token";
    public static final String KEY_FACEBOOK_ID = "facebook_id";
    public static final String KEY_USER_STATUS = "user_status";
    public static final String KEY_USER_IN_CIRCLE = "add_circle";
    public static final String KEY_USER_IS_BLOCK = "is_block";


    // Json response keys
    public static final String KEY_CODE = "code";
    public static final String KEY_SUCCESS = "success";
    public static final String KEY_MESSAGE = "message";
    public static final String KEY_DATA = "data";
    public static final String KEY_DELETED = "deleted";
    public static final String KEY_RESET = "reset";

    public static final String PREF_KEY_RELOAD_NOTIFICATIONS = "reload_notifications";
    public static final String KEY_TERMS_ACCEPTED = "terms_accepted";

    public static final int OPEN_TERMS_AND_CONDITION = 18;
    public static final int FACEBOOK_LOGIN = 19;

    public static final String KEY_CALLING_COMPONENT = "calling_component";

    public static final String SENT_TOKEN_TO_SERVER = "sentTokenToServer";
    public static final String REGISTRATION_COMPLETE = "registrationComplete";

    // Loaders
    public static final int LOADER_CLIENTS = 1;
    public static final int LOADER_EXPENSES = 2;

    public static final String DIR_IMAGE = "image";

    public static final String EXT_JPG = ".jpg";
    public static final String PREFIX_IMG = "Img_";

    public static final int PERMISSION_REQUEST_CODE_CAMERA = 1;
}
