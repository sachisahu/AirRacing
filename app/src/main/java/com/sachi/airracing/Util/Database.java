package com.sachi.airracing.Util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.sachi.airracing.DataClass.RaceDataClass;

import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteOpenHelper {
    static String DatabaseName = "air.db";

    public Database(@Nullable Context context) {
        super(context, DatabaseName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("Create Table airData(id Integer primary key,Racename text,dateTime text,type text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) { }

    public boolean insert(String RaceName,String datetime,String type){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Racename",RaceName);
        values.put("dateTime",datetime);
        values.put("type",type);
        db.insert("airdata",null,values);
        return true;
    }
    public List<RaceDataClass> list = new ArrayList<>();
    public void featch(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from airData",null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            String RaceName = cursor.getString(cursor.getColumnIndexOrThrow("Racename"));
            String datetime = cursor.getString(cursor.getColumnIndexOrThrow("dateTime"));
            String type = cursor.getString(cursor.getColumnIndexOrThrow("type"));
            list.add(new RaceDataClass(RaceName,datetime,type));
            cursor.moveToNext();

        }
    }
}
