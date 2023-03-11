package stundet.db.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SQLightDemo extends AppCompatActivity {
Button btn_save;
MyDB db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlight_demo);
        btn_save=findViewById(R.id.btn_save);
        db=new MyDB(SQLightDemo.this);
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              boolean b=db.insertDemo(2,"Kapil","909090920");
               if(b)
               {
                   Toast.makeText(SQLightDemo.this, "Record Saved Successfully.", Toast.LENGTH_SHORT).show();
               }
            }
        });

    }
}