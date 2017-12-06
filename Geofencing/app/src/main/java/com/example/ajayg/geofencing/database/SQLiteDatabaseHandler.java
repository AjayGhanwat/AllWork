package com.example.ajayg.geofencing.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.ajayg.geofencing.datamodel.AllData;
import com.example.ajayg.geofencing.datamodel.EnterExit;

import java.util.ArrayList;

public class SQLiteDatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    public static String DATABASE_NAME = "Stud.db";

    public static final String TABLE_NAME = "EnterExit";
    public static final String TABLE_NAME_AllData = "AllData";

    public SQLiteDatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("create table " + TABLE_NAME + "(Enter VARCHAR, Exit VARCHAR, Interval VARCHAR, Date VARCHAR)");

        sqLiteDatabase.execSQL("create table " + TABLE_NAME_AllData + "(Date VARCHAR, Enter VARCHAR, Exit VARCHAR,Times VARCHAR, Interval VARCHAR)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_AllData);
        onCreate(sqLiteDatabase);
    }

    SQLiteDatabase sqLiteDatabase;

    public void insertRecordEnterExit(EnterExit dataModel){

        sqLiteDatabase = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("Enter", dataModel.getEnter());
        contentValues.put("Exit", dataModel.getExit());
        contentValues.put("Interval", dataModel.getInterval());
        contentValues.put("Date", dataModel.getDate());

        sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
        sqLiteDatabase.close();

    }

    public void insertRecordAllData(AllData dataModel){

        sqLiteDatabase = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("Date", dataModel.getDate());
        contentValues.put("Enter", dataModel.getEnter());
        contentValues.put("Exit", dataModel.getExit());
        contentValues.put("Interval", dataModel.getInterval());

        sqLiteDatabase.insert(TABLE_NAME_AllData, null, contentValues);
        sqLiteDatabase.close();

    }

    public void updateRecordEnterExit(EnterExit dataModel){

        sqLiteDatabase = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("Enter", dataModel.getEnter());
        contentValues.put("Exit", dataModel.getExit());
        contentValues.put("Interval", dataModel.getInterval());
        contentValues.put("Date", dataModel.getDate());

        sqLiteDatabase.update(TABLE_NAME, contentValues,"Enter = ? ", new String[] {dataModel.getEnter()});
        sqLiteDatabase.close();

    }

    public void updateRecordAllData(AllData dataModel){

        sqLiteDatabase = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("Date", dataModel.getDate());
        contentValues.put("Enter", dataModel.getEnter());
        contentValues.put("Exit", dataModel.getExit());
        contentValues.put("Interval", dataModel.getInterval());

        sqLiteDatabase.update(TABLE_NAME_AllData, contentValues,"Date = ? ", new String[] {dataModel.getDate()});        sqLiteDatabase.close();

    }

    public void deleteRecordEnterExit(EnterExit dataModel){
        sqLiteDatabase = this.getReadableDatabase();
        sqLiteDatabase.delete(TABLE_NAME, "Enter = ? ", new String[] {dataModel.getEnter()});
        sqLiteDatabase.close();
    }

    public void deleteRecordAllData(AllData dataModel){
        sqLiteDatabase = this.getReadableDatabase();
        sqLiteDatabase.delete(TABLE_NAME_AllData, "Date = ? ", new String[] {dataModel.getDate()});
        sqLiteDatabase.close();
    }

    public ArrayList<EnterExit> getAllRecordEnterExit(){
        sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query(TABLE_NAME, null, null, null, null, null, null);
        ArrayList<EnterExit> data = new ArrayList<>();
        EnterExit dataModel;
        if (cursor.getCount() > 0) {
            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.moveToNext();
                dataModel = new EnterExit();
                dataModel.setEnter(cursor.getString(0));
                dataModel.setExit(cursor.getString(1));
                dataModel.setInterval(cursor.getString(2));
                dataModel.setDate(cursor.getString(3));
                data.add(dataModel);
            }
        }
        cursor.close();
        sqLiteDatabase.close();
        return data;
    }

    public ArrayList<AllData> getAllRecordAllData(){
        sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query(TABLE_NAME_AllData, null, null, null, null, null, null);
        ArrayList<AllData> data = new ArrayList<>();
        AllData dataModel;
        if (cursor.getCount() > 0) {
            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.moveToNext();
                dataModel = new AllData();
                dataModel.setDate(cursor.getString(0));
                dataModel.setEnter(cursor.getString(1));
                dataModel.setExit(cursor.getString(2));
                dataModel.setInterval(cursor.getString(4));

                data.add(dataModel);
            }
        }
        cursor.close();
        sqLiteDatabase.close();
        return data;
    }

    public void deleteAllEnterExit()
    {
        sqLiteDatabase = this.getReadableDatabase();
        sqLiteDatabase.execSQL("delete from "+ TABLE_NAME);
        sqLiteDatabase.close();
    }

    public void deleteAllAllData()
    {
        sqLiteDatabase = this.getReadableDatabase();
        sqLiteDatabase.execSQL("delete from "+ TABLE_NAME_AllData);
        sqLiteDatabase.close();
    }
}