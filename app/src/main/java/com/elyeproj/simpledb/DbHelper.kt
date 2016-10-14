package com.elyeproj.simpledb

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*


class DbHelper(context: Context) : SQLiteOpenHelper(context, DbHelper.DATABASE_NAME, null, DbHelper.DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(DATABASE_CREATE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        Log.w(TAG, "Upgrade from version $oldVersion to $newVersion")
        Log.w(TAG, "This is version 1, no DB to update")
    }

    fun clearDbAndRecreate() {
        clearDb()
        onCreate(writableDatabase)
    }

    fun clearDb() {
        writableDatabase.execSQL("DROP TABLE IF EXISTS $DATABASE_TABLE")
    }

    fun getAllText(): String {
        var result = ""
        val cursor = writableDatabase.query(DbHelper.DATABASE_TABLE, DbHelper.RESULT_COLUMNS,
                null, null, null, null, DbHelper.KEY_ID)
        val INDEX_COLUMN_NAME = cursor.getColumnIndexOrThrow(DbHelper.KEY_NAME)
        while (cursor != null && cursor.moveToNext()) { result += "${cursor.getString(INDEX_COLUMN_NAME)}-" }

        return result
    }

    fun insertText(value: String) {
        val newValue = ContentValues()
        newValue.put(DbHelper.KEY_NAME, value)
        writableDatabase.insert(DbHelper.DATABASE_TABLE, null, newValue)

    }

    companion object {
        val KEY_ID = "_ID"
        val KEY_NAME = "NAME"
        val DATABASE_TABLE = "simpletable"
        var RESULT_COLUMNS = arrayOf(KEY_ID, KEY_NAME)

        private val TAG = DbHelper::class.java.simpleName

        private val DATABASE_NAME = "simpledatabase.sqlite"
        private val DATABASE_VERSION = 1

        private val DATABASE_CREATE =
                "CREATE TABLE $DATABASE_TABLE ($KEY_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "$KEY_NAME TEXT NOT NULL);"
    }
}
