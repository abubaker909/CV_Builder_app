package com.example.cv_builder

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context) : SQLiteOpenHelper(context, "UserData", null, 1) {

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(
            "CREATE TABLE UserData (rollnumber TEXT PRIMARY KEY, name TEXT, cgpa REAL, degree TEXT, gender TEXT, dob TEXT, interests TEXT)"
        )
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS UserData")
        onCreate(db)
    }

    fun saveUserData(
        rollnumber: String, name: String, cgpa: Double,
        degree: String, gender: String, dob: String, interests: String
    ): Boolean {
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put("rollnumber", rollnumber)
        cv.put("name", name)
        cv.put("cgpa", cgpa)
        cv.put("degree", degree)
        cv.put("gender", gender)
        cv.put("dob", dob)
        cv.put("interests", interests)
        val result = db.insert("UserData", null, cv)
        db.close()
        return result != -1L
    }

    fun getUserData(): Cursor? {
        val db = this.readableDatabase
        return db.rawQuery("SELECT * FROM UserData", null)
    }
}
