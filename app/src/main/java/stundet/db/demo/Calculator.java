package stundet.db.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Calculator extends AppCompatActivity {
    EditText et_num1,et_num2;
    Button btnadd;
    String strnum1,strnum2;
    TextView txtresult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        et_num1=findViewById(R.id.num1);
        et_num2=findViewById(R.id.num2);
        btnadd=findViewById(R.id.btnadd);
        txtresult=findViewById(R.id.txtresult);
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

}