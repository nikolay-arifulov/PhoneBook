package com.example.phonebook;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.phonebook.database.UserBaseHelper;
import com.example.phonebook.database.UserDBSchema;

import java.util.ArrayList;

public class Users {
    private static Users users;
    private SQLiteDatabase database;

    public static Users get(Context context) {
        if (users == null) users = new Users(context);
        return users;
    }

    private Users(Context context) {
        this.database = new UserBaseHelper(context).getWritableDatabase();
    }

    public void addUser(User user) {
        ContentValues values = getContentValues(user);
        database.insert(UserDBSchema.UserTable.NAME,null, values);
    }

    public void deleteUser(User user) {
        database.delete(UserDBSchema.UserTable.NAME, UserDBSchema.UserTable.Column.UUID + "=?", new String[]{user.getUuid().toString()});
    }

    public void changeUser(User user) {
        ContentValues values = getContentValues(user);
        database.update(UserDBSchema.UserTable.NAME, values, UserDBSchema.UserTable.Column.UUID + "=?", new String[]{user.getUuid().toString()});
    }

    private static ContentValues getContentValues(User user){
        ContentValues values = new ContentValues();
        values.put(UserDBSchema.UserTable.Column.UUID, user.getUuid().toString());
        values.put(UserDBSchema.UserTable.Column.FIRSTNAME, user.getFirstName());
        values.put(UserDBSchema.UserTable.Column.LASTNAME, user.getLastName());
        values.put(UserDBSchema.UserTable.Column.PHONE, user.getPhone());
        return values;
    }

    private UserCursorWrapper queryUsers() {
        Cursor cursor = database.query(UserDBSchema.UserTable.NAME,null,null,null,null,null,null);
        return new UserCursorWrapper(cursor);
    }

    public ArrayList<User> getUserList() {
        ArrayList<User> userList = new ArrayList<>();
        UserCursorWrapper cursorWrapper = queryUsers();
        try {
            cursorWrapper.moveToFirst();
            while (!cursorWrapper.isAfterLast()){
                User user = cursorWrapper.getUser();
                userList.add(user);
                cursorWrapper.moveToNext();
            }
        } finally {
            cursorWrapper.close();
        }

        return userList;
    }
}