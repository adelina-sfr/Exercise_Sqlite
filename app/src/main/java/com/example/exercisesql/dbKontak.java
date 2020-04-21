package com.example.exercisesql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class dbKontak extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "MyDBName.db";
    public static final String MHS_TABLE_NAME = "Kontak";
    public static final String MHS_COLUMN_NAMA = "Nama";
    public static final String MHS_COLUMN_ID = "id";
    public static final String MHS_COLUMN_NOMOR_TELEPON = "Nomor_Telepon";
    public static final String MHS_COLUMN_EMAIL = "Email";
    public static final String MHS_COLUMN_ALAMAT = "Alamat";
    private HashMap hp;

    public dbKontak(Context context) {super(context, DATABASE_NAME, null, 1);}
    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(
                "create table Kontak " +
                        "(id integer primary key, Nama text,Nomor_Telepon text,Email text,Alamat text)"
        );
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int
            newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS Kontak");
        onCreate(db);
    }
    public boolean insertContact (String Nama, String Email, String Alamat, String Nomer_Telepon)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Nama",Nama );
        contentValues.put("Nomer Telepon", Nomer_Telepon);
        contentValues.put("Email", Email);
        contentValues.put("Alamat", Alamat);
        db.insert("Kontak", null, contentValues);
        return true;
    }
    public Cursor getData(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery( "select * from Kontak where id="+id+"", null );
        return res;
    }
    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db,
                MHS_TABLE_NAME);
        return numRows;
    }
    public ArrayList<String> getAllCotacts() {
        ArrayList<String> array_list = new ArrayList<String>();
        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery( "select * from Kontak", null );
        res.moveToFirst();
        while(res.isAfterLast() == false){

            array_list.add(res.getString(res.getColumnIndex(MHS_COLUMN_NAMA)));
            res.moveToNext();
        }
        return array_list;
    }
}
