package com.n0153.fitnessnotes.db_utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBhelper extends SQLiteOpenHelper {

    public final static String DB_NAME = "Fit DB";
    public final static int DB_VER = 1;

    public static final String KEY_ID = "_id";
    public static final String KEY_NAME = "name";
    public static final String KEY_CATEGORY = "category";
    public static final String KEY_TYPE = "type";
    public static final String KEY_UNITS = "units";
    public static final String KEY_DATE = "date";
    public static final String KEY_POS_IN_A_DATE = "position_date";
    public static final String KEY_WEIGHT_DIST = "weight_dist";
    public static final String KEY_REPS_TIME = "reps_time";
    public static final String KEY_NOTES = "notes";


    public static final String TYPE_TEXT_COMMA = " text, ";
    public static final String TYPE_TEXT = " text ";
    public static final String TYPE_INTEGER = " integer, ";
    public static final String TYPE_REAL_COMMA = " real, ";
    public static final String TYPE_REAL = " real";

    public static final String TABLE_EXERISES_NAME = "EXERSISES";
    public static final String TABLE_SETS_NAME = "SETS";



    private final static String LOG_TAG = "DB Helper log";


    public DBhelper(Context context) {
        super(context, DB_NAME, null, DB_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(LOG_TAG, "DB Creating");

        db.execSQL("CREATE TABLE " + TABLE_EXERISES_NAME + " (" + KEY_ID + "integer primary key, " +
                        KEY_CATEGORY + TYPE_TEXT_COMMA + KEY_NAME + TYPE_TEXT_COMMA +  KEY_TYPE +
                        TYPE_INTEGER + KEY_UNITS + TYPE_TEXT + " )");

        db.execSQL("CREATE TABLE " + TABLE_SETS_NAME + " (" + KEY_DATE + TYPE_INTEGER + KEY_POS_IN_A_DATE +
                TYPE_INTEGER + KEY_NAME + TYPE_TEXT_COMMA + KEY_WEIGHT_DIST + TYPE_REAL_COMMA +
                KEY_REPS_TIME + TYPE_REAL_COMMA + KEY_NOTES + TYPE_TEXT + " )");

        Log.d(LOG_TAG, "DBs created");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
