package com.thirio.android.database;

public interface DbConstants {
    String DB_NAME= "thirio.db";
    int DB_VERSION = 1 ;

    String INTEGER_PRIMARY_KEY_AUTOINCREMENT = " INTEGER PRIMARY KEY AUTOINCREMENT ";
    String INTEGER = " INTEGER ";
    String TEXT = " TEXT ";
    String REAL =  " REAL ";
    String FLOAT = " FLOAT ";
    String DEFAULT = " DEFAULT ";


    String TBL_USER = "tbl_user";
    String COL_ID = "_id";
    String COL_SEX = "col_sex";
    String COL_NAME = "col_name";
    String COL_CONTACT = "col_contact";
    String COL_HEIGHT = "col_height";
    String COL_WEIGHT = "col_weight";
    String COL_AGE = "col_age";
    String COL_USER[] = new String[] {
            COL_ID + INTEGER_PRIMARY_KEY_AUTOINCREMENT,
            COL_NAME + TEXT,
            COL_SEX + TEXT,
            COL_AGE + INTEGER,
            COL_CONTACT + TEXT,
            COL_HEIGHT + FLOAT,
            COL_WEIGHT + INTEGER
    };

    String TBL_DIET_USER_YET_TO_BE_FINALIZED="tbl_diet_user_yet_to_be_finalized";
    String TBL_DIET_USER="tbl_diet_user";
    String COL_USERID="col_user";
    String COL_MAIN_COURSE="col_main_course";
    String COL_SIDES="col_sides";
    String COL_SALAD="col_salads";
    String COL_BREADS="col_breads";
    String COL_DIET="col_diet";
    String COL_DIET_USER[]= new String[]{
            COL_ID + INTEGER_PRIMARY_KEY_AUTOINCREMENT,
            COL_USERID + INTEGER,
            COL_MAIN_COURSE + TEXT,
            COL_SIDES + TEXT,
            COL_SALAD + TEXT,
            COL_BREADS + TEXT,
            COL_DIET + INTEGER
    };
}
