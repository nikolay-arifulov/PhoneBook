package com.example.phonebook.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.phonebook.database.UserDBSchema.*;

public class UserBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "userBase.db";

    public UserBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+ UserTable.NAME+ "(" +
                "_id integer primary key autoincrement, " +
                UserTable.Column.UUID+"," +
                UserTable.Column.FIRSTNAME+"," +
                UserTable.Column.LASTNAME+"," +
                UserTable.Column.PHONE+")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) { // Если база существует и версии разичаются onUpgrade()

    }
}