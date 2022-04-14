package com.example.sqlite.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashMap;

public class DBcontroller extends SQLiteOpenHelper {
    public DBcontroller( Context context) {
        super(context, "ProdiTI", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table teman (id integer primary key,nama text,telpon text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists teman");
        onCreate(db);

    }
    public void InsertData(HashMap<String,String> queryValues) {
        SQLiteDatabase basisdata = this.getReadableDatabase();
        ContentValues nilai = new ContentValues();
        nilai.put("nama",queryValues.get("nama"));
        nilai.put("telpon",queryValues.get("telpon"));
        basisdata.insert("teman",null,nilai);
        basisdata.close();
    }
    public void UpdateData(HashMap<String,String> queryValues){
        SQLiteDatabase db = getReadableDatabase();
        ContentValues nilai = new ContentValues();
        nilai.put("nama", queryValues.get("nama"));
        nilai.put("telpon", queryValues.get("telpon"));
        db.update("teman", nilai, "id=?",new String[]{queryValues.get("id")});
        db.close();
    }
    public  void DeleteData(HashMap<String,String> queryValue){
        SQLiteDatabase db = getWritableDatabase();
        db.delete("teman", "id=?",new String[]{queryValue.get("id")});
        db.close();
    }

    public ArrayList<HashMap<String,String>> getAllTeman(){
        ArrayList<HashMap<String,String >> daftarTeman;
        daftarTeman = new ArrayList<HashMap<String,String>>();
        String selecQuery = "Select * from teman";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selecQuery,null);
        if(cursor.moveToFirst()){
            do {
                HashMap<String,String> map = new HashMap<>();
                map.put("id",cursor.getString(0));
                map.put("nama",cursor.getString(1));
                map.put("telpon",cursor.getString(2));
                daftarTeman.add(map);
            }while (cursor.moveToNext());
        }
        db.close();
        return daftarTeman;
    }
}