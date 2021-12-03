package com.example.makeupexams;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DB_Name = "Login.db";

    public DBHelper(@Nullable Context context) {
        super(context, DB_Name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("Create Table users(username TEXT primary key, password TEXT, firstname TEXT, lastname TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("Drop Table if exists users");
    }

    //crud operations
    public Boolean insertData (String username, String password){
        SQLiteDatabase sqLiteDatabase= this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);

        long result = sqLiteDatabase.insert("users", null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    //submit data
    public Boolean submitData ( String firstname, String lastname, String username, String password) {
        SQLiteDatabase sqLiteDatabase= this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        contentValues.put("firstname", firstname);
        contentValues.put("lastname", lastname);

        Cursor cursor = sqLiteDatabase.rawQuery("Select * from users where username = ? ", new String[]{username});
        if (cursor.getCount() > 0) {
            long result = sqLiteDatabase.update("users ", contentValues, "firstname = ?", new String[]{firstname} );
            if (result == -1) {
                return false;
            }else  {
                return true;
            }
        }
        else {
            return false;
        }
    }

    //checking if user exists in the table
        public Boolean checkUserName (String username) {
        SQLiteDatabase sqLiteDatabase= this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("Select * from users where username = ?" , new String[]{username});

        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }

    //checking user password
    public Boolean checkUserPassword (String username, String password) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("Select * from users where username = ? and password = ?" , new String[]{username , password});
        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }

    //view database details
    public Cursor getData(){
        SQLiteDatabase DB = this.getWritableDatabase();

        Cursor cursor = DB.rawQuery("Select * from users", null);
        return cursor;
    }
}
