package com.example.knsi.bugbook.app.test;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;
import android.util.Log;

import com.example.knsi.bugbook.data.BugContractClass;
import com.example.knsi.bugbook.data.BugDbHelper;

/**
 * Created by Bug Book on 03-06-2015.
 */
public class TestDb extends AndroidTestCase {

    public void testCreateDb()throws Throwable{

        mContext.deleteDatabase(BugDbHelper.DATABASE_NAME);
        SQLiteDatabase db = new BugDbHelper(
                this.mContext).getWritableDatabase();
        assertEquals(true, db.isOpen());
        db.close();
    }

    public void testInsertDb()
    {

        String pro_name="Bug Book";
        String tester_id="BMSCE-IS-016";


        BugDbHelper bdb=new BugDbHelper(mContext);
        SQLiteDatabase db=bdb.getWritableDatabase();

        ContentValues values=new ContentValues();
        values.put(BugContractClass.BugBookDb.PROJECT_NAME,pro_name);
        values.put(BugContractClass.BugBookDb.TESTER_ID,tester_id);

        long locationRowId=db.insert(BugContractClass.BugBookDb.TABLE_NAME,null,values);

        assertTrue(locationRowId >-1);
        Log.d("testActivity","the row id obtained is"+locationRowId);

        String columns[]={BugContractClass.BugBookDb.PROJECT_NAME, BugContractClass.BugBookDb.TESTER_ID};

        Cursor cursor=db.query(BugContractClass.BugBookDb.TABLE_NAME,columns,null,null,null,null,null);

        if(cursor.moveToFirst()){

            int ProjectIndex=cursor.getColumnIndex(BugContractClass.BugBookDb.PROJECT_NAME);
            String ProjectValue=cursor.getString(ProjectIndex);

            int IdIndex=cursor.getColumnIndex(BugContractClass.BugBookDb.TESTER_ID);
            String TesterIdValue=cursor.getString(IdIndex);

            assertEquals(pro_name,ProjectValue);
            assertEquals(tester_id,TesterIdValue);
        }
        else{

            fail("db is not working properly...");
        }


    }


}
