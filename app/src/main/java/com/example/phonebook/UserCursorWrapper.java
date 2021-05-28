package com.example.phonebook;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.example.phonebook.database.UserDBSchema;

import java.util.UUID;

public class UserCursorWrapper extends CursorWrapper {
    public UserCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public User getUser(){
        String uuidString = getString(getColumnIndex(UserDBSchema.UserTable.Column.UUID));
        String userName = getString(getColumnIndex(UserDBSchema.UserTable.Column.FIRSTNAME));
        String userLastName = getString(getColumnIndex(UserDBSchema.UserTable.Column.LASTNAME));
        String phone = getString(getColumnIndex(UserDBSchema.UserTable.Column.PHONE));

        User user = new User(UUID.fromString(uuidString));
        user.setFirstName(userName);
        user.setLastName(userLastName);
        user.setPhone(phone);
        return user;
    }
}