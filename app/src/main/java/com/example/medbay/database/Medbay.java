package com.example.medbay.database;


public class Medbay {
    private Medbay() {

    }
    public static final class Patient{
        public static final String P_TABLE = "patient";
        public static final String P_NAME = "name";
        public static final String P_DOB = "dob";
        public static final String P_EMAIL = "email";
        public static final String P_PASSWORD = "password";
        public static final String P_PHONE = "phone_no";

    }
    public static final class Special{
        public static final String S_TABLE = "specialization";
        public static final String S_NAME = "name";
    }

    public static final class Doctor{
        public static final String D_TABLE = "doctor";
        public static final String D_NAME = "d_name";
        public static final String D_EMAIL = "d_email";
        public static final String D_PASSWORD = "d_password";
        public static final String D_PHONE = "d_phone";
        public static final String DS_ID = "ds_id";
    }

    public static final class Appoint {
        public static final String A_TABLE = "appointment";
        public static final String A_PID = "pid";
        public static final String A_DID = "did";
        public static final String A_DATE = "app_date";
        public static final String A_TIME = "app_time";
    }

    public static final class Medicine {
        public static final String M_TABLE = "medicine";
        public static final String M_NAME = "m_name";
    }

    public static final class Presc {
        public static final String P_TABLE = "prescription";
        public static final String P_PID = "pid";
        public static final String P_MID = "mid";
        public static final String P_NAME = "m_name";
        public static final String P_DOS = "dosage";
        public static final String P_DAY = "r_days";
    }
}
