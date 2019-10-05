package com.n0153.fitnessnotes.db_utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.n0153.fitnessnotes.db_utils.models.ExOptionsDataModel;
import com.n0153.fitnessnotes.db_utils.models.SetOptionsDataModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
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
    public static final String KEY_WEIGHT_DIST = "weight_dist";
    public static final String KEY_REPS_TIME = "reps_time";
    public static final String KEY_NOTES = "notes";
    public static final String KEY_CATEGORIES = "categories";

    public static final String TYPE_TEXT_COMMA = " text, ";
    public static final String TYPE_TEXT = " text ";
    public static final String TYPE_INTEGER = " integer, ";
    public static final String TYPE_REAL_COMMA = " real, ";

    public static final String TABLE_EXERISES_NAME = "EXERSISES";
    public static final String TABLE_SETS_NAME = "SETS";
    public static final String TABLE_CATEGORIES_NAME = "CATEGOTISES";


    private final static String LOG_TAG = "DB Helper log";
    public SQLiteDatabase db;


    public DBhelper(Context context) {
        super(context, DB_NAME, null, DB_VER);
        db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {


        db.execSQL("CREATE TABLE " + TABLE_EXERISES_NAME + " (" + KEY_ID + " integer primary key autoincrement, " +
                KEY_CATEGORY + TYPE_TEXT_COMMA + KEY_NAME + TYPE_TEXT_COMMA + KEY_TYPE +
                TYPE_TEXT_COMMA + KEY_UNITS + TYPE_TEXT + " )");

        db.execSQL("CREATE TABLE " + TABLE_SETS_NAME + " (" + KEY_DATE + TYPE_INTEGER +
                KEY_NAME + TYPE_TEXT_COMMA + KEY_WEIGHT_DIST + TYPE_REAL_COMMA +
                KEY_REPS_TIME + TYPE_TEXT_COMMA + KEY_NOTES + TYPE_TEXT + " )");

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

    public ArrayList<String> getCategories() {

        String[] column = new String[]{KEY_CATEGORIES};
        Cursor cursor = db.query(TABLE_CATEGORIES_NAME, column, null, null, null, null, null);
        ArrayList<String> categoriesList = new ArrayList<>();
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    int categoryIndex = cursor.getColumnIndex(DBhelper.KEY_CATEGORIES);
                    categoriesList.add(cursor.getString(categoryIndex));
                } while (cursor.moveToNext());
            }

        }
        cursor.close();

        return categoriesList;
    }


    public List getExNamesList() {

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

        Log.d(LOG_TAG, "category " + category);
        Cursor cursor = db.query(TABLE_EXERISES_NAME, new String[]{KEY_NAME},
                KEY_CATEGORY + " = ?", new String[]{category}, null, null, null);

        Log.d(LOG_TAG, "Exercises list size: " + cursor.getCount());


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

    public ExOptionsDataModel getExOptionsData(String exercise) {

        String[] columns = new String[]{KEY_TYPE, KEY_UNITS};

        Cursor cursor = db.query(TABLE_EXERISES_NAME, columns, KEY_NAME + " = ?",
                new String[]{exercise}, null, null, null);

        Log.d(LOG_TAG, "Cursor size" + cursor.getCount());
        String type = null, units = null;

        cursor.moveToFirst();

        type = cursor.getString(cursor.getColumnIndex(KEY_TYPE));
        units = cursor.getString(cursor.getColumnIndex(KEY_UNITS));

        cursor.close();
        return new ExOptionsDataModel(type, units);
    }


    public void saveSet(String name, float weighOrDist, String repsOrTime, String notes) {
        long dateMills = new Date().getTime();


        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_REPS_TIME, repsOrTime);
        contentValues.put(KEY_WEIGHT_DIST, weighOrDist);
        contentValues.put(KEY_DATE, dateMills);
        contentValues.put(KEY_NOTES, notes);
        contentValues.put(KEY_NAME, name);

        db.insert(TABLE_SETS_NAME, null, contentValues);

    }

    public String getExeriseType(String exerciseName) {

        Cursor cursor = db.query(TABLE_EXERISES_NAME, new String[]{KEY_TYPE}, KEY_NAME + " = ?",
                new String[]{exerciseName}, null, null, null);
        cursor.moveToFirst();

        int index = cursor.getColumnIndex(KEY_TYPE);

        String type = cursor.getString(index);
        cursor.close();

        return type;
    }

    public ArrayList<SetOptionsDataModel> getSetOptionsSortedList(String exerciseName) {
        Cursor cursor = db.query(TABLE_SETS_NAME, new String[]{KEY_DATE, KEY_WEIGHT_DIST, KEY_REPS_TIME, KEY_NOTES},
                KEY_NAME + " = ?", new String[]{exerciseName}, null, null, null);

        ArrayList<SetOptionsDataModel> list = new ArrayList<>();

        String units = getExerciseUnits(exerciseName);

        if (cursor.moveToFirst()) {
            do {

                Date date = new Date(cursor.getLong(cursor.getColumnIndex(KEY_DATE)));
                String weightOrDist = cursor.getString(cursor.getColumnIndex(KEY_WEIGHT_DIST));
                String repsOrTime = cursor.getString(cursor.getColumnIndex(KEY_REPS_TIME));
                String note = cursor.getString(cursor.getColumnIndex(KEY_NOTES));
                list.add(new SetOptionsDataModel(null, date, repsOrTime, weightOrDist, note, units));
            } while (cursor.moveToNext());

        }

        Log.d(LOG_TAG, " setOpionsList  size = " + list.size());

        cursor.close();

        //sort by date newes irst
        Collections.sort(list, new Comparator<SetOptionsDataModel>() {
            @Override
            public int compare(SetOptionsDataModel o1, SetOptionsDataModel o2) {
                return o2.getDate().compareTo(o1.getDate());
            }
        });
        return list;
    }

    public void deleteSetByDate(long date) {
        db.delete(TABLE_SETS_NAME, KEY_DATE + " = " + date, null);

    }


    public boolean isSetSetListEmpty(String exercise) {
        Cursor cursor = db.query(TABLE_SETS_NAME, new String[]{KEY_NAME}, KEY_NAME + " = ?", new String[]{exercise}, null, null, null);
        boolean result = cursor.getCount() == 0;
        cursor.close();
        return result;
    }

    public SetOptionsDataModel getSetByDate(long dateLong) {
        Cursor cursor = db.query(TABLE_SETS_NAME, new String[]{KEY_DATE, KEY_WEIGHT_DIST, KEY_REPS_TIME, KEY_NOTES},
                KEY_DATE + " = ?", new String[]{String.valueOf(dateLong)}, null, null, null);
        cursor.moveToFirst();
        Date date = new Date(cursor.getLong(cursor.getColumnIndex(KEY_DATE)));
        String weightOrDist = cursor.getString(cursor.getColumnIndex(KEY_WEIGHT_DIST));
        String repsOrTime = cursor.getString(cursor.getColumnIndex(KEY_REPS_TIME));
        String note = cursor.getString(cursor.getColumnIndex(KEY_NOTES));
        cursor.close();

        return new SetOptionsDataModel(null, date, repsOrTime, weightOrDist, note, null);
    }


    public void updateSet(long dateLong, String weightOrDist, String repsOrTime, String note) {
        ContentValues cv = new ContentValues();
        cv.put(KEY_REPS_TIME, repsOrTime);
        cv.put(KEY_WEIGHT_DIST, weightOrDist);
        cv.put(KEY_NOTES, note);
        db.update(TABLE_SETS_NAME, cv, KEY_DATE + " = ?", new String[]{String.valueOf(dateLong)});
    }



    //get sets for particular day
    public ArrayList<SetOptionsDataModel> getSetsByDay(long date) {
        Date dateThis = new Date(date);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = sdf.format(dateThis);
        long day = 0;
        try {
            day = sdf.parse(dateString).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        long endOfDay = day + 86400000;
        Cursor cursor = db.query(TABLE_SETS_NAME, new String[]{KEY_NAME, KEY_DATE, KEY_WEIGHT_DIST, KEY_REPS_TIME, KEY_NOTES}, KEY_DATE + " >= ?" + " AND " + KEY_DATE + " < ?",
                new String[]{String.valueOf(day), String.valueOf(endOfDay)}, null, null, null);
        ArrayList<SetOptionsDataModel> list = new ArrayList<>();

        Log.d(LOG_TAG, "setsByDay size: " + cursor.getCount());

        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(cursor.getColumnIndex(KEY_NAME));
                Date dateOutput = new Date(cursor.getLong(cursor.getColumnIndex(KEY_DATE)));
                String weightOrDist = cursor.getString(cursor.getColumnIndex(KEY_WEIGHT_DIST));
                String repsOrTime = cursor.getString(cursor.getColumnIndex(KEY_REPS_TIME));
                String note = cursor.getString(cursor.getColumnIndex(KEY_NOTES));
                String units = getExerciseUnits(name);
                list.add(new SetOptionsDataModel(name, dateOutput, repsOrTime, weightOrDist, note, units));
            } while (cursor.moveToNext());

        }

        //sort list by date newest first
        Collections.sort(list, new Comparator<SetOptionsDataModel>() {
            @Override
            public int compare(SetOptionsDataModel o1, SetOptionsDataModel o2) {
                return o1.getDate().compareTo(o2.getDate());
            }
        });

        cursor.close();
        return list;
    }

    private String getExerciseUnits(String exerciseName){
        Cursor cursor = db.query(TABLE_EXERISES_NAME, new String[]{KEY_UNITS}, KEY_NAME + " = ?",
                new String[]{exerciseName}, null, null, null);

        cursor.moveToFirst();
        String units = cursor.getString(cursor.getColumnIndex(KEY_UNITS));
        cursor.close();
        return units;

    }

}
