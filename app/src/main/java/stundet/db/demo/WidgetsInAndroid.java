package stundet.db.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class WidgetsInAndroid extends AppCompatActivity {
 CheckBox chk1,chk2,chk3;
 TextView txt;
 RadioButton rb1,rb2;
 RadioGroup rg;
 Switch aSwitch;
 ToggleButton toggleButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_widgets_in_android);

         chk1=findViewById(R.id.checkBox1);
         chk2=findViewById(R.id.checkBox2);
         chk3=findViewById(R.id.checkBox3);
        txt=findViewById(R.id.textView);
        rb1=findViewById(R.id.radioButton1);
        rb2=findViewById(R.id.radioButton2);
        rg=findViewById(R.id.rbgr);
        aSwitch=findViewById(R.id.switch1);
        toggleButton=findViewById(R.id.toggleButton);

        aSwitch.setChecked(true);
        chk1.setChecked(true);
        rb1.setChecked(true);
        toggleButton.setTextOff("De-Active");
        toggleButton.setTextOn("Active");


        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        String str=bundle.getString("type");
        String id=bundle.getString("id");


        Toast.makeText(this, ""+str+" "+id, Toast.LENGTH_SHORT).show();


         chk1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
             @Override
             public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                 setTextViewData();
             }
         });

        chk2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                setTextViewData();
            }
        });
        chk3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                setTextViewData();
            }
        });

/*        rb1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b)
                    Toast.makeText(WidgetsInAndroid.this, "MALE Selected", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(WidgetsInAndroid.this,"MALE Not Selected",Toast.LENGTH_SHORT).show();
            }
        });
        rb2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b)
                    Toast.makeText(WidgetsInAndroid.this, "FEMALE Selected", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(WidgetsInAndroid.this,"FEMALE Not Selected",Toast.LENGTH_SHORT).show();
            }
        });*/
        
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                switch (i)
                {
                
                    case R.id.radioButton1:
                        Toast.makeText(WidgetsInAndroid.this, "Male", Toast.LENGTH_SHORT).show();
                        break;
                
                    case R.id.radioButton2:
                        Toast.makeText(WidgetsInAndroid.this, "Female", Toast.LENGTH_SHORT).show();
                        break;
                
                }

            }
        });
        
        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b)
                    Toast.makeText(WidgetsInAndroid.this, "Active", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(WidgetsInAndroid.this, "De Active", Toast.LENGTH_SHORT).show();
            }
        });

        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b)
                    Toast.makeText(WidgetsInAndroid.this, ""+toggleButton.getTextOn(), Toast.LENGTH_SHORT).show();

                else
                Toast.makeText(WidgetsInAndroid.this, ""+toggleButton.getTextOff(), Toast.LENGTH_SHORT).show();


            }
        });

    }
    public void setTextViewData()
    {   String strchk1,strchk2,strchk3;
        strchk1=strchk2=strchk3="";
        if(chk1.isChecked())
         strchk1=chk1.getText().toString().trim();

        if(chk2.isChecked())
            strchk2=chk2.getText().toString().trim();

        if(chk3.isChecked())
            strchk3=chk3.getText().toString().trim();


         txt.setText(""+strchk1+" "+strchk2+" "+strchk3);
    }



}