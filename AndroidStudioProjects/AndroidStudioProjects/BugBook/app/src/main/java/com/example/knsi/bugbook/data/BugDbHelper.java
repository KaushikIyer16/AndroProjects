package com.example.knsi.bugbook.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.knsi.bugbook.data.BugContractClass.BugBookDb;
/**
 * Created by Bug Book on 03-06-2015.
 */
public class BugDbHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME="BugBook.db";
    public static final int DATABASE_VERSION=1;

    public BugDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {


        final String CREATE_TABLE_QUERY="CREATE TABLE "+BugBookDb.TABLE_NAME+" ("+BugBookDb.KEY_ID+
                " INTEGER AUTO INCREMENT,"+BugBookDb.PROJECT_NAME+" TEXT NOT NULL,"+BugBookDb.TESTER_ID+" TEXT NOT NULL);";

        db.execSQL(CREATE_TABLE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS "+BugBookDb.TABLE_NAME);

    }
}
