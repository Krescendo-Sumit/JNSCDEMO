package stundet.db.demo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Vector;

public class SQLightDemo extends AppCompatActivity {
Button btn_save;
Button btn_show;
TextView tv_id,tv_name,tv_mobile;
String str_id, str_name, str_mobile;
MyDB db;
TableLayout tbl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlight_demo);
        btn_save=findViewById(R.id.btn_save);
        tv_id=findViewById(R.id.tv_id);
        tv_name=findViewById(R.id.tv_name);
        tv_mobile=findViewById(R.id.tv_mobile);
        btn_show=findViewById(R.id.btn_show);
        tbl=findViewById(R.id.tbl);

        db=new MyDB(SQLightDemo.this);
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str_id=tv_id.getText().toString().trim();
                str_name=tv_name.getText().toString().trim();
                str_mobile=tv_mobile.getText().toString().trim();
              boolean b=db.insertDemo(str_id,str_name,str_mobile);
               if(b)
               {
                   Toast.makeText(SQLightDemo.this, "Record Saved Successfully.", Toast.LENGTH_SHORT).show();
               }
                showData();
            }
        });
        btn_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showData();
            }
        });

    }
    public void showData()
    {
        try {
             tbl.removeAllViews();
            Vector v[] = db.ShowRecords();
            TableRow row_title = new TableRow(SQLightDemo.this);
            TextView txtid_title = new TextView(SQLightDemo.this);
            TextView txtname_title = new TextView(SQLightDemo.this);
            TextView txtmobile_title = new TextView(SQLightDemo.this);
            TextView txt_del = new TextView(SQLightDemo.this);

            txt_del.setText("");
            txtid_title.setText("Id");
            txtname_title.setText("Name");
            txtmobile_title.setText("Mobile" );


            txtid_title.setBackgroundColor(Color.GRAY);
            txtname_title.setBackgroundColor(Color.GRAY);
            txtmobile_title.setBackgroundColor(Color.GRAY);

            row_title.addView(txt_del);
            row_title.addView(txtid_title);
            row_title.addView(txtname_title);
            row_title.addView(txtmobile_title);

            tbl.addView(row_title);

            Log.i("V[0]",v[0].toString());
            for (int i = 0; i < v.length; i++) {
                TableRow row = new TableRow(SQLightDemo.this);
                TextView txtid = new TextView(SQLightDemo.this);
                TextView txtname = new TextView(SQLightDemo.this);
                TextView txtmobile = new TextView(SQLightDemo.this);
                Button btndel = new Button(SQLightDemo.this);
                btndel.setId(Integer.parseInt(v[i].elementAt(0).toString().trim()));
                btndel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(SQLightDemo.this, ""+v.getId(), Toast.LENGTH_SHORT).show();

                        AlertDialog alertDialog=new AlertDialog.Builder(SQLightDemo.this)
                                .setMessage("Do you want to delete?")
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        if(db.deleteRecord(v.getId()))
                                        {
                                            Toast.makeText(SQLightDemo.this, "Record Deleted Successfully.", Toast.LENGTH_SHORT).show();
                                        }
                                        showData();
                                    }
                                })
                                .setNegativeButton("No thanks", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                })
                                .show();



                    }
                });

                txtid.setText("" + v[i].elementAt(0).toString());
                txtname.setText("" + v[i].elementAt(1).toString());
                txtmobile.setText("" + v[i].elementAt(2).toString());
                btndel.setText("X");

                row.addView(btndel);
                row.addView(txtid);
                row.addView(txtname);
                row.addView(txtmobile);

                tbl.addView(row);
            }

            Log.i("Retrive CNT", "" + v.length);
        }catch (Exception e)
        {
            Log.i("Error is ",e.getMessage());
        }
    }
}