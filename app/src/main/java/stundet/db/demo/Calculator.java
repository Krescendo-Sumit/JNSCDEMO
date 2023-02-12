package stundet.db.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Calculator extends AppCompatActivity implements View.OnClickListener {
    EditText et_num1,et_num2;
    Button btnadd;
    String strnum1,strnum2;
    TextView txtresult;
    Button btn2;
    Button btn3;
    Button btn4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        et_num1=findViewById(R.id.num1);
        et_num2=findViewById(R.id.num2);
        btnadd=findViewById(R.id.btnadd);
        txtresult=findViewById(R.id.txtresult);

        btn2=findViewById(R.id.btnadd1);
        btn3=findViewById(R.id.btnadd2);
        btn4=findViewById(R.id.btnadd3);

        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);


        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                strnum1=et_num1.getText().toString().trim();
                strnum2=et_num2.getText().toString().trim();
                if(strnum1.equals(""))
                {
                    et_num1.setError("Empty");
                }
                else if(strnum2.equals(""))
                {
                    et_num2.setError("Empty");
                }
                else {
                    int total = Integer.parseInt(strnum1) + Integer.parseInt(strnum2);
                    txtresult.setText("Addition is " + total);
                    Log.i("Value:", "Clicked");
                }
            }
        });

    }
    public void add(View v)
    {
        strnum1=et_num1.getText().toString().trim();
        strnum2=et_num2.getText().toString().trim();
        if(strnum1.equals(""))
        {
            et_num1.setError("Empty");
        }
        else if(strnum2.equals(""))
        {
            et_num2.setError("Empty");
        }
        else {
            int total = Integer.parseInt(strnum1) + Integer.parseInt(strnum2);
            txtresult.setText("Addition is " + total);
            Log.i("Value:", "Clicked");
        }
    }

    @Override
    public void onClick(View view) {
        int id=view.getId();
        switch(id)
        {
            case R.id.btnadd2:
                txtresult.setText("Third Method called");

                 Intent intent=new Intent(Calculator.this,WidgetsInAndroid.class);
                 intent.putExtra("type","10");
                 intent.putExtra("id","JN001");
                 startActivity(intent);

                break;
            case R.id.btnadd3:
                txtresult.setText("Forth Method called");
                Toast.makeText(this,"Hello",Toast.LENGTH_LONG).show();
                break;
        }
    }
}