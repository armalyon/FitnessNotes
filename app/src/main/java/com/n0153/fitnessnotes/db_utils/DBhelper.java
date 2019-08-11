package com.n0153.fitnessnotes.db_utils;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

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
    public static final String KEY_CATEGORIES = "categories";


    public static final String TYPE_TEXT_COMMA = " text, ";
    public static final String TYPE_TEXT = " text ";
    public static final String TYPE_INTEGER = " integer, ";
    public static final String TYPE_REAL_COMMA = " real, ";
    public static final String TYPE_REAL = " real";

    public static final String TABLE_EXERISES_NAME = "EXERSISES";
    public static final String TABLE_SETS_NAME = "SETS";
    public static final String TABLE_CATEGORIES_NAME = "CATEGOTISES";


    private final static String LOG_TAG = "DB Helper log";


    public DBhelper(Context context) {
        super(context, DB_NAME, null, DB_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {


        db.execSQL("CREATE TABLE " + TABLE_EXERISES_NAME + " (" + KEY_ID + " integer primary key autoincrement, " +
                KEY_CATEGORY + TYPE_TEXT_COMMA + KEY_NAME + TYPE_TEXT_COMMA + KEY_TYPE +
                TYPE_TEXT_COMMA + KEY_UNITS + TYPE_TEXT + " )");

        db.execSQL("CREATE TABLE " + TABLE_SETS_NAME + " (" + KEY_DATE + TYPE_INTEGER + KEY_POS_IN_A_DATE +
                TYPE_INTEGER + KEY_NAME + TYPE_TEXT_COMMA + KEY_WEIGHT_DIST + TYPE_REAL_COMMA +
                KEY_REPS_TIME + TYPE_REAL_COMMA + KEY_NOTES + TYPE_TEXT + " )");

        db.execSQL("CREATE TABLE " + TABLE_CATEGORIES_NAME + " (" + KEY_ID + " integer primary key autoincrement, " +
                KEY_CATEGORIES + TYPE_TEXT + ")");

        Log.d(LOG_TAG, "DBs created");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //!!!!method body should be updated

        db.execSQL("drop table if exists " + TABLE_SETS_NAME);
        db.execSQL("drop table if exists " + TABLE_CATEGORIES_NAME);
        db.execSQL("drop table if exists " + TABLE_EXERISES_NAME);

    }

    public Cursor getCategories() {
        SQLiteDatabase db = getReadableDatabase();
        String[] column = new String[]{KEY_CATEGORIES};
        return db.query(TABLE_CATEGORIES_NAME, column, null, null, null, null, null);
    }


    public List getExNamesList() {
        SQLiteDatabase db = getReadableDatabase();
        List<String> exNamesList = new ArrayList<>();
        String[] column = new String[]{KEY_NAME};
        Cursor cursor = db.query(TABLE_EXERISES_NAME, column, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                int categoryIndex = cursor.getColumnIndex(KEY_NAME);
                exNamesList.add(cursor.getString(categoryIndex));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return exNamesList;
    }

    public List getExercisesList(String category) {
        SQLiteDatabase db = this.getWritableDatabase();
        Log.d(LOG_TAG, "category " + category);
        Cursor cursor = db.query(TABLE_EXERISES_NAME, new String[]{KEY_NAME},
               KEY_CATEGORY + " = ?", new String[]{category}, null, null, null );

        Log.d(LOG_TAG, "cursor size: " + cursor.getCount());


        List<String> exercisesList = new ArrayList<>();

        if (cursor.moveToNext()) {
            do {
                int index = cursor.getColumnIndex(DBhelper.KEY_NAME);
                exercisesList.add(cursor.getString(index));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return exercisesList;
    }


}
