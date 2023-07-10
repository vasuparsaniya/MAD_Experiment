package com.example.experiment_7;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(@Nullable Context context) {
        super(context, "MyUserDatabase.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create Table UserRecord (ID Text primary key, NAME Text, CONTACT Text, ENROLLMENT Text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop Table if exists UserRecord");
//        onCreate(db);
    }


    //----------------------------insert records----------------------------------------------------
    public Boolean insertUserData(String id, String name, String contact, String enrollment){

        //to get database connection
        SQLiteDatabase db = this.getWritableDatabase();

        //to write content in database
        ContentValues contentvalue = new ContentValues();

        //assign values to content variable
        contentvalue.put("ID",id);
        contentvalue.put("NAME", name);
        contentvalue.put("CONTACT", contact);
        contentvalue.put("ENROLLMENT", enrollment);

        //execute insert query
        Long result = db.insert("UserRecord", null, contentvalue);

        if(result == -1){
            return false;
        }else{
            return true;
        }
    }


    //----------------------------update records----------------------------------------------------
    public Boolean updateUserData(String id, String name, String contact, String enrollment) {

        //to get database connection
        SQLiteDatabase db = this.getWritableDatabase();

        //to write content in database
        ContentValues contentvalue = new ContentValues();

        //assign values to content variable
        contentvalue.put("NAME", name);
        contentvalue.put("CONTACT", contact);
        contentvalue.put("ENROLLMENT", enrollment);

        //current user record find
        //cursor means view
        Cursor currentRecord = db.rawQuery("select * from UserRecord where ID=?", new String[]{id});

        if (currentRecord.getCount() > 0) {
            int result = db.update("UserRecord", contentvalue, "ID=?", new String[]{id});

            if (result == -1) {
                return false;
            } else {

                return true;
            }
        }
        else {
            return false;
        }
    } // end of update method


    //----------------------------delete records----------------------------------------------------
    public Boolean deleteUserData(String id) {

        //to get database connection
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor findRecord = db.rawQuery("select * from UserRecord where ID=?", new String[]{id});

        if(findRecord.getCount()>0){
            int result = db.delete("UserRecord", "ID=?", new String[]{id});

            if(result == -1){
                return false;
            }else{
                return true;
            }
        }
        else{
            return false;
        }
    } // end of delete method

    //----------------------------view records----------------------------------------------------
    public Cursor viewUserData() {

        //to get database connection
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor findAllRecord = db.rawQuery("select * from UserRecord", null);
        return findAllRecord;
    } // end of view method
}
