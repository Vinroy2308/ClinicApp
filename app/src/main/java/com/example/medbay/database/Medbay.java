package com.example.medbay.database;

import android.provider.BaseColumns;

public class Medbay {
    private Medbay() {

    }
    public static final class Patient implements BaseColumns {
        public static final String P_TABLE = "patient";
        public static final String P_NAME = "name";
        public static final String P_DOB = "dob";
        public static final String P_EMAIL = "email";
        public static final String P_PASSWORD = "password";
        public static final String P_PHONE = "phone_no";

    }
    public static final class Special implements BaseColumns {
        public static final String S_TABLE = "specialization";
        public static final String S_NAME = "name";
    }
}
