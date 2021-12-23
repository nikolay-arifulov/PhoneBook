package com.example.phonebook.database;

public class UserDBSchema {

    public static class UserTable {

        public static final String NAME = "users";

        public static final class Column {

            public static final String UUID = "uuid";

            public static final String FIRSTNAME = "firstname";

            public static final String LASTNAME = "lastname";

            public static final String PHONE = "phone";
        }
    }
}
