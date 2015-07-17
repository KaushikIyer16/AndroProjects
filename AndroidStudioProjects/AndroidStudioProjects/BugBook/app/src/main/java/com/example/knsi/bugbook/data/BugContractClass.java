package com.example.knsi.bugbook.data;

import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by Bug Book on 03-06-2015.
 */
public class BugContractClass{

    public static final String CONTENT_AUTHORITY="com.example.knsi.bugbook.app";

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://"+CONTENT_AUTHORITY);

    public static final String PATH_BUG="bug";


    public static final class BugBookDb implements BaseColumns{

        public static final Uri CONTENT_URI=BASE_CONTENT_URI.buildUpon().appendPath(PATH_BUG).build();

        public static final String CONTENT_TYPE =
                "vnd.android.cursor.dir/" + CONTENT_AUTHORITY + "/" + PATH_BUG;
        public static final String CONTENT_ITEM_TYPE =
                "vnd.android.cursor.item/" + CONTENT_AUTHORITY + "/" + PATH_BUG;

        // The base columns in my app. They represent the columns or entry fields in my app
        public static final String TABLE_NAME="bugs";
        public static final String KEY_ID="_id";
        public static final String PROJECT_NAME="project_name";
        public static final String TESTER_ID="tester_id";



    }


}
