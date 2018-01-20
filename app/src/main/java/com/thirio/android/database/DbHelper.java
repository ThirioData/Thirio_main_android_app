package com.thirio.android.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DbHelper extends SQLiteOpenHelper implements DbConstants{

    Context mContext;

    public DbHelper(Context context) {
        super(context,DB_NAME, null, DB_VERSION);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createTables(db,TBL_USER,COL_USER);
        createTables(db,TBL_DIET_USER_YET_TO_BE_FINALIZED,COL_DIET_USER);
        createTables(db,TBL_DIET_USER,COL_DIET_USER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion) {
            deleteTable(db,TBL_DIET_USER_YET_TO_BE_FINALIZED);
            deleteTable(db,TBL_USER);
        }
    }




    public void createTables(SQLiteDatabase db, String tableName, String[] columns) {
        String columnString="";

        for(int i=0;i<columns.length;i++){
            columnString+= columns[i]+" , ";
        }
        columnString=columnString.substring(0,columnString.length()-2);

        String SQL_CREATE_TABlE = " CREATE TABLE "+tableName+
                " ( "
                    +columnString+
                " ); ";
        db.execSQL(SQL_CREATE_TABlE);
        Log.d("TBL CREATED",SQL_CREATE_TABlE);
    }


    public void deleteTable(SQLiteDatabase db, String tableName) {
        db.execSQL("DROP TABLE IF EXISTS "+tableName);
    }

}
