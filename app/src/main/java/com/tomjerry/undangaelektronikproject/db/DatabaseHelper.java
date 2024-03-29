package com.tomjerry.undangaelektronikproject.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static String DATABASE_NAME = "dbTAmu";
    private static final int DATABASE_VERSION = 1;

    private static final String SQL_CRETE_TABLE_TAMU = String.format("CREATE TABLE %s"
                    + "(%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL)",
            DatabaseContract.TABLE_TAMU,
            DatabaseContract.DaftarTamu._ID,
            DatabaseContract.DaftarTamu.NAMA,
            DatabaseContract.DaftarTamu.ALAMAT ,
            DatabaseContract.DaftarTamu.NOMOR
    );


    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CRETE_TABLE_TAMU);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ DatabaseContract.TABLE_TAMU);
        onCreate(db);
    }
}

