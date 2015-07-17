package com.example.knsi.bugbook.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

//import com.example.knsi.bugbook.MainActivity;
//import com.example.knsi.bugbook.PostBugsActivity;

//This is the all important content provider for my app

public class BugContentProvider extends ContentProvider{

    private static final int one_row=1;

    public static final UriMatcher uriMatcher;
    static final String authority= BugContractClass.CONTENT_AUTHORITY;
    static {
        uriMatcher=new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(authority, BugContractClass.PATH_BUG+"/#",one_row);
    }

    private BugDbHelper mOpenHelper;
    @Override
    public boolean onCreate() {
       // PostBugsActivity p=new PostBugsActivity();
     //   String project_name=p.pName;

       // MainActivity m=new MainActivity();
       // String tester_id=m.TesterId.toString();

        mOpenHelper=new BugDbHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {

        Cursor cursor;

        switch(uriMatcher.match(uri)) {
            case one_row:
                cursor = mOpenHelper.getWritableDatabase().query(BugContractClass.BugBookDb.TABLE_NAME, projection, null, null, null, null, null);
                break;

            default:
                throw new UnsupportedOperationException("uri string is" + uri);
        }

        cursor.setNotificationUri(getContext().getContentResolver(),uri);
        return cursor;
    }

    @Override
    public String getType(Uri uri) {
        final int match=uriMatcher.match(uri);
        switch (match){

            case one_row:return BugContractClass.BugBookDb.CONTENT_ITEM_TYPE;

            default:throw new UnsupportedOperationException("uri string is"+uri);
        }

    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {


        final SQLiteDatabase db=mOpenHelper.getWritableDatabase();

        long Id=db.insert(BugContractClass.BugBookDb.TABLE_NAME,null,values);
        if(Id>-1) {

            Uri insertId= ContentUris.withAppendedId(BugContractClass.BugBookDb.CONTENT_URI,Id);
            getContext().getContentResolver().notifyChange(insertId,null);
            return insertId;
        }
        else
        {
            return null;
        }


    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
