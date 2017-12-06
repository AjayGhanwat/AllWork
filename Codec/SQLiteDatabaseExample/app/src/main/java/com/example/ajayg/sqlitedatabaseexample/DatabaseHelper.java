package com.example.ajayg.sqlitedatabaseexample;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static android.R.attr.x;

public class DatabaseHelper extends SQLiteOpenHelper{

    private static final String DATABASE_NAME = "notes.db";
    private static final String TABLE_NAME = "note";

    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_Desc = "desc";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase){

        sqLiteDatabase.execSQL("create table " + TABLE_NAME + " ( id Text, title TEXT, desc TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXITS " + TABLE_NAME);
        onCreate(sqLiteDatabase);

    }

    public boolean insertData(DataModel dataModel){

        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_TITLE, dataModel.getTitle());
        values.put(KEY_Desc, dataModel.getDesc());

        long result = db.insert(TABLE_NAME, null, values);

        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllData() {

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " +TABLE_NAME, null);
        return cursor;
    }

    public boolean updateData(DataModel dataModel) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_TITLE, dataModel.getTitle());
        values.put(KEY_Desc, dataModel.getDesc());

        long isUpdated = db.update(TABLE_NAME, values, KEY_ID + " = ?",new String[] { String.valueOf(dataModel.getId()) });
        // updating row
        if(isUpdated == -1 )
            return false;
        else
            return true;
    }
    String alter;

    public boolean deleteData(DataModel dataModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        long isDeleted = db.delete(TABLE_NAME, KEY_ID + " = ?", new String[] { String.valueOf(dataModel.getId()) });
        db.close();

        if(isDeleted == -1)
            return false;
        else
            return true;
    }
}
