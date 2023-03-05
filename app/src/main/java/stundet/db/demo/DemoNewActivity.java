package stundet.db.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class DemoNewActivity extends AppCompatActivity {
EditText et_data;
Button btn;
    TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_new);
        txt=findViewById(R.id.textView11);
        et_data=findViewById(R.id.et_data);
        txt.setText("Hello World");

    }
    public void go(View v)
    {
        String str=et_data.getText().toString().trim();
        int a=Integer.parseInt(str);
        txt.setText(""+(a*a));
    }
}