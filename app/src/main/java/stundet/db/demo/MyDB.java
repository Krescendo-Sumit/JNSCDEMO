package stundet.db.demo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

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

    public boolean insertDemo(int id,String name,String mobile)
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
}
