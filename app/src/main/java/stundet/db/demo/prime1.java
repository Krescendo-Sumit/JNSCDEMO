package stundet.db.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class prime1 extends AppCompatActivity {

    EditText e;
     TextView t;
     Button b;
     String strnum1;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prime1);
        e = findViewById(R.id.edit1);
        b = findViewById(R.id.btn2);
        t = findViewById(R.id.txt1);
    }

    public void primenum(View v)
    {
        strnum1=e.getText().toString().trim();
        t.setText(strnum1);

    }
}