package com.example.covidpersonaltracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    public  static String DATABASE_NAME="CovidDb.db";
    public static String TABLE_NAME="CovidData";
    public static String COLUMN_TEMP="temperature";
    public static String COLUMN_DATETIME="datetime";
    public static String COLUMN_LOCATION="location";

    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //create the table with the columns.
        db.execSQL("create table CovidData (id integer primary key,temperature text,datetime text,location text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //makes a new version copy of existing db
    }

    public String getDbName(){
        return this.getDatabaseName();
    }

    public boolean insertData(String temp, String datetime,String location){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COLUMN_TEMP,temp);
        contentValues.put(COLUMN_DATETIME,datetime);
        contentValues.put(COLUMN_LOCATION,location);
        db.insert(TABLE_NAME,null,contentValues);
        return true;
    }


    public List<CovidDataModel> fetchData(){
        List<CovidDataModel> covidDataModelList=new ArrayList<CovidDataModel>();
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("SELECT * from CovidData",null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            covidDataModelList.add(new CovidDataModel(cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TEMP)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DATETIME)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_LOCATION))));
            cursor.moveToNext();
        }
        cursor.close();
        return covidDataModelList;
    }

    public CovidDataModel fetchIndividualData(int id){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("SELECT * from CovidData where id="+id+"",null);
        return new CovidDataModel(cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TEMP)),
                cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DATETIME)),
                cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_LOCATION)));
    }


}
