package com.example.phonebook;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.example.phonebook.database.UserDbSchema;

import java.util.UUID;

public class UserCursorWrapper extends CursorWrapper {
    public UserCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public User getUser(){
        String uuidString = getString(getColumnIndex(UserDbSchema.UserTable.Cols.UUID));
        String userName = getString(getColumnIndex(UserDbSchema.UserTable.Cols.FIRSTNAME));
        String userLastName = getString(getColumnIndex(UserDbSchema.UserTable.Cols.LASTNAME));
        String phone = getString(getColumnIndex(UserDbSchema.UserTable.Cols.PHONE));

        User user = new User(UUID.fromString(uuidString));
        user.setUserName(userName);
        user.setUserLastName(userLastName);
        user.setPhone(phone);
        return user;
    }
}