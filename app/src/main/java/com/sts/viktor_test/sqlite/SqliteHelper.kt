package com.sts.viktor_test.sqlite

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class SqliteHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION){

    override fun onCreate(db: SQLiteDatabase) {
        val SQL_CREATE_ENTRIES =
            "CREATE TABLE ${TABLE_NAME} (" +
                    "${COL_ID} INTEGER PRIMARY KEY," +
                    "${COL_SEARCH_KEY} TEXT)"

        db.execSQL(SQL_CREATE_ENTRIES)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS ${TABLE_NAME}"
        db.execSQL(SQL_DELETE_ENTRIES)
        onCreate(db)
    }

    override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }

    fun addSearchHistory(searchKey : String) {

        if (searchKey.isEmpty()) return

        val values = ContentValues().apply {
            put(COL_SEARCH_KEY, searchKey)
        }

        val selection = "${COL_SEARCH_KEY} LIKE ?"
        val selectionArgs = arrayOf(searchKey)

        val db = this.writableDatabase
        var newRowId = db.update(TABLE_NAME, values, selection, selectionArgs)

        if (newRowId == 0){
            newRowId = db.insert(TABLE_NAME, null, values).toInt()
        }
    }

    fun getSearchHistory() : Cursor{
        val db = this.readableDatabase
        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null)
    }

    fun deleteSearchKey(searchKey: String) {
        val db = writableDatabase
        val selection = COL_SEARCH_KEY + " LIKE ?"
        val selectionArgs = arrayOf(searchKey)
        db.delete(TABLE_NAME, selection, selectionArgs)
    }

    companion object {
        val DATABASE_NAME = "database_news"
        val DATABASE_VERSION = 1
        val TABLE_NAME = "tbl_news"
        val COL_ID = "id"
        val COL_SEARCH_KEY = "search_key"
    }
}