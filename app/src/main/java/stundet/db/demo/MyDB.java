package stundet.db.demo;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Vector;

public class MyDB extends SQLiteOpenHelper {
    final static String db="jnec.db";
    int version=1;
    public MyDB(Context context) {
        super(context, db, null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
       try {
           String tbl_demo = "CREATE TABLE tbl_demo (id integer,name text,mobile text)";
           db.execSQL(tbl_demo);
           Log.i("Query ", tbl_demo);
       }catch(Exception e)
       {
           Log.i("Error ",e.getMessage());
       }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }

    public boolean insertDemo(String id, String name, String mobile)
    {
        try{
            String ins_demo="insert into tbl_demo values("+id+",'"+name+"','"+mobile+"');";
            SQLiteDatabase db;
            db=this.getReadableDatabase();
            db.execSQL(ins_demo);

            Log.i("Result","Data Saved");
            return true;

        }catch (Exception e)
        {
            Log.i("Error ","Data Saved"+e.getMessage());
            return false;
        }
    }

    public boolean deleteRecord(int id)
    {
        try{
            String ins_demo="delete from tbl_demo where id="+id;
            SQLiteDatabase db;
            db=this.getReadableDatabase();
            db.execSQL(ins_demo);

            Log.i("Result","Data Deleted");
            return true;

        }catch (Exception e)
        {
            Log.i("Error ","Data Saved"+e.getMessage());
            return false;
        }
    }



    public Vector[] ShowRecords()
    {
        try{
            Vector temp[];
            String sel_demo="select * from tbl_demo";
            SQLiteDatabase db;
            db=this.getReadableDatabase();
            Cursor cursor=db.rawQuery(sel_demo,null);
            temp=new Vector[cursor.getCount()];
            int i=0;
            while(cursor.moveToNext())
            {
                temp[i]=new Vector();
                temp[i].add(cursor.getInt(0));
                temp[i].add(cursor.getString(1));
                temp[i].add(cursor.getString(2));
                i++;
                String data=cursor.getInt(0)+" "+cursor.getString(1)+" "+cursor.getString(2);
                Log.i("Data:",data);
            }

            return  temp;

        }catch (Exception e)
        {
            Log.i("Error ","Data Saved"+e.getMessage());
             return null;
        }
    }


}
