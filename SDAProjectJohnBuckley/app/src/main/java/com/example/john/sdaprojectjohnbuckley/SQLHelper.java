/**
 * Copyright 2013 Google Inc. All Rights Reserved.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software distributed under the
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.john.sdaprojectjohnbuckley;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 *
 * Methods that create and maintain the database and tables.
 * Citation:
 * Class contains code adapted from
 * URL:https://github.com/udacity/ud845-Pets
 * Retrieved on 11th of April 2017
 * URL: https://developer.android.com/training/basics/data-storage/databases.html
 * Retrieved on 11th of April 2017
 * URL:https://github.com/aporter/coursera-android/tree/master/Examples/DataManagementSQL
 * Retrieved on 8th of October 2016
 * Created by John on 27/03/2017.
 */
public class SQLHelper extends SQLiteOpenHelper {

    /**
     * Create Database table columns
     * Code adapted from https://developer.android.com/training/basics/data-storage/databases.html
     * Code adapted from https://github.com/udacity/ud845-Pets
     */
    private static final String SQL_GUEST_CREATE_ENTRIES =
            "CREATE TABLE " + GuestContract.GuestFeedEntry.TABLE_NAME + " (" +
                    GuestContract.GuestFeedEntry._ID + " INTEGER PRIMARY KEY," +
                    GuestContract.GuestFeedEntry.GUEST_NAME + " TEXT," +
                    GuestContract.GuestFeedEntry.GUEST_EMAIL + " TEXT," +
                    GuestContract.GuestFeedEntry.GUEST_PHONENUMBER + " TEXT)";
    /**
     * Datebase table to be deleted
     * Code adapted from https://developer.android.com/training/basics/data-storage/databases.html
     * Code adapted from https://github.com/udacity/ud845-Pets
     */
    private static final String SQL_GUEST_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + GuestContract.GuestFeedEntry.TABLE_NAME;

    // If you change the database schema, you must increment the database version.
    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "GuestsList.db";

    /**
     *
      * @param context context of current state
     */
    public SQLHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    /**
     * Called when the database is created for the first time
     * This is where the creation of tables and the initial population of the tables should happen
     * @param database The database
     */
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(SQL_GUEST_CREATE_ENTRIES);
    }

    /**
     * Called when the database needs to be upgraded
     * The implementation should use this method to drop tables, add tables,
     * or do anything else it needs to upgrade to the new schema version
     * @param database The database.
     * @param oldVersion The old database version.
     * @param newVersion The new database version.
     */
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        database.execSQL(SQL_GUEST_DELETE_ENTRIES);
        onCreate(database);
    }
}

