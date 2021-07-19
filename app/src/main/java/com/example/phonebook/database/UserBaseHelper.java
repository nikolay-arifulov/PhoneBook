package com.example.phonebook.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.phonebook.database.UserDBSchema.*;

// Данный класс создаёт БД при первом запуске приложения, когда БД ещё не создана
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

    // Если БД существует и версии различаются запускается метод onUpgrade()
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}