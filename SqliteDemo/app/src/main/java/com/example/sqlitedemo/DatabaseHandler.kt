package com.example.sqlitedemo

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.sqlitedemo.Constants.*
import com.example.sqlitedemo.Models.EmpModelClass

//creating the database logic, extending the SQLiteOpenHelper base class
class DatabaseHandler(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    /*
    Called when the database is created for the first time.
    This is where the creation of tables and the initial population of the tables should happen.
     */
    override fun onCreate(db: SQLiteDatabase?) {
        //creating table with fields
        //same as: create table EmployeeTable(_id integer primary key, name text, email text)
        //A column declared INTEGER PRIMARY KEY will autoincrement in sqlite.
        val CREATE_EXERCISE_TABLE = ("CREATE TABLE " + TABLE_NAME + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_EMAIL + " TEXT" + ")")
        db?.execSQL(CREATE_EXERCISE_TABLE)
    }

    /*
    Called when we upgrade our table, eg: if we add another column we'll have to change the DATABASE_VERSION.
     */
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
        onCreate(db)
    }


    /**
     * Function to insert data
     */
    fun addEmployee(emp: EmpModelClass): Long {

        //this.writableDatabase : Create and/or open a database that will be used for reading and writing. The first time this is called, the database will be opened and onCreate, onUpgrade and/or onOpen will be called.
        //Once opened successfully, the database is cached, so you can call this method every time you need to write to the database. (Make sure to call close when you no longer need the database.)
        val db = this.writableDatabase  //get the database
        val contentValues = ContentValues()
        contentValues.put(KEY_NAME, emp.name) // EmpModelClass Name        (Not providing the id as it's autoincrement)
        contentValues.put(KEY_EMAIL, emp.email) // EmpModelClass Email

        // Inserting Row(returns the row ID of the newly inserted row, or -1 if an error occurred)
        val success = db.insert(TABLE_NAME, null, contentValues)
        //2nd argument is String containing nullColumnHack

        db.close() // Closing database connection
        return success
    }

    /**
     * Function to read data
     */
    //method to read data
    fun viewEmployee(): ArrayList<EmpModelClass> {

        val empList: ArrayList<EmpModelClass> = ArrayList<EmpModelClass>()

        val selectQuery = "SELECT  * FROM $TABLE_NAME"

        val db = this.readableDatabase
        var cursor: Cursor? = null

        try {
            //Runs the provided SQL and returns a Cursor over the result set.
            cursor = db.rawQuery(selectQuery, null)//returns A Cursor object, which is positioned before the first entry.

        } catch (e: SQLiteException) {
//            db.execSQL(selectQuery)
            Log.e("SQL ERROR", "viewEmployee: ${e.stackTrace}")
            return ArrayList()
        }

        var id: Int
        var name: String
        var email: String

        if (cursor.moveToFirst()) {
            do {
                id = cursor.getInt(cursor.getColumnIndex(KEY_ID))
                name = cursor.getString(cursor.getColumnIndex(KEY_NAME))
                email = cursor.getString(cursor.getColumnIndex(KEY_EMAIL))

                val emp = EmpModelClass(id = id, name = name, email = email)
                empList.add(emp)

            } while (cursor.moveToNext())
        }
        return empList
    }

    /**
     * Function to update record
     */
    fun updateEmployee(emp: EmpModelClass): Int {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(KEY_NAME, emp.name) // EmpModelClass Name
        contentValues.put(KEY_EMAIL, emp.email) // EmpModelClass Email

        // Updating Row
        val success = db.update(TABLE_NAME, contentValues, KEY_ID + "=" + emp.id, null)
        //OR db.execSql("UPDATE TABLE EmployeeTable SET email='something@gmail.com' WHERE id=1")
        //2nd argument is String containing nullColumnHack

        db.close() // Closing database connection
        return success
    }

    /**
     * Function to delete record
     */
    fun deleteEmployee(emp: EmpModelClass): Int {
        val db = this.writableDatabase
//        val contentValues = ContentValues()
//        contentValues.put(KEY_ID, emp.id) // EmpModelClass id
        // Deleting Row
        val success = db.delete(TABLE_NAME, KEY_ID + "=" + emp.id, null)
        //2nd argument is String containing nullColumnHack
        db.close() // Closing database connection
        return success
    }
}