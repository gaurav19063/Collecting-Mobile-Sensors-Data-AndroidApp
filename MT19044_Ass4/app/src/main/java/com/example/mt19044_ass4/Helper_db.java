package com.example.mt19044_ass4;



import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Helper_db extends SQLiteOpenHelper {
    public static final String Database_name="myDb";
    public static final String Table_name1="acc";
    public static final String Table_name2="loc";
    public static final String Table_name3="wifi";
    public static final String Table_name4="MIC";
    public static final String col2="Ax";
    public static final String col3="Ay";
    public static final String col4="Az";
    public static final String col5="time_stamp";
    public static final String col6="latitude";
    public static final String col7="longitude";
    public static final String col8="time_stamp";
    public static final String col9="ssid";
    public static final String col10="name";
    public static final String col11="time_stamp";
    public static final String col12="link";
    public static final String col13="time_stamp";





    public Helper_db(@Nullable Context context) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("DROP TABLE IF EXISTS "+Table_name1);
        db.execSQL("DROP TABLE IF EXISTS "+"loc");
        db.execSQL("DROP TABLE IF EXISTS "+"wifi");
        db.execSQL("DROP TABLE IF EXISTS "+"MIC");

        db.execSQL("Create table "+Table_name1+"(ID INTEGER PRIMARY KEY AUTOINCREMENT ,Ax, Ay, Az, time_stamp)");
        db.execSQL("Create table "+Table_name2+"(ID INTEGER PRIMARY KEY AUTOINCREMENT ,latitude, longitude, time_stamp)");
        db.execSQL("Create table "+Table_name3+"(ID INTEGER PRIMARY KEY AUTOINCREMENT ,ssid, name, time_stamp)");
        db.execSQL("Create table "+Table_name4+"(ID INTEGER PRIMARY KEY AUTOINCREMENT ,link, time_stamp)");

    }
    @Override
    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion)
    {
        ;
        onCreate(db);
    }




    public boolean addData(String Ax,String Ay,String Az, String time_stamp)
    {
        SQLiteDatabase DB=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(col2,Ax);
        contentValues.put(col3,Ay);
        contentValues.put(col4, Az);
        contentValues.put(col5, time_stamp);
        long result = DB.insert(Table_name1,null,contentValues);
        if(result==-1)
            return false;
        else
            return true;
    }
    public boolean insertData(String ssid,String name,String time_stamp)
    {
        SQLiteDatabase  DB=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(col6,ssid);
        contentValues.put(col7,name);
        contentValues.put(col8, time_stamp);
        long result = DB.insert(Table_name2,null,contentValues);
        if(result==-1)
            return false;
        else
            return true;
    }

    public boolean insertData_wifi(String ssid,String name,String time_stamp)
    {
        SQLiteDatabase  DB=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(col9,ssid);
        contentValues.put(col10,name);
        contentValues.put(col11, time_stamp);
        long result = DB.insert(Table_name3,null,contentValues);
        if(result==-1)
            return false;
        else
            return true;
    }
    public boolean insertData_mic(String link,String time_stamp)
    {
        SQLiteDatabase  DB=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(col12,link);
//        contentValues.put(col13,name);
        contentValues.put(col13, time_stamp);
        long result = DB.insert(Table_name4,null,contentValues);
        if(result==-1)
            return false;
        else
            return true;
    }


    public Cursor getAllData_acc()
    {
        SQLiteDatabase  DB=this.getWritableDatabase();
        Cursor result= DB.rawQuery("Select * from "+Table_name1,null);
        return result;
    }
    public Cursor getAllData_loc()
    {
        SQLiteDatabase  DB=this.getWritableDatabase();
        Cursor result= DB.rawQuery("Select * from "+Table_name2,null);
        return result;
    }
    public Cursor getAllData_wifi()
    {
        SQLiteDatabase  DB=this.getWritableDatabase();
        Cursor result= DB.rawQuery("Select * from "+Table_name3,null);
        return result;
    }
    public Cursor getAllData_micro() {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor result = DB.rawQuery("Select * from " + Table_name4, null);
        return result;
    }


}
