package stundet.db.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView txt_name=findViewById(R.id.txtid1);

        txt_name.setText("My Changed text Value");
        txt_name.setTextColor(Color.RED);
        txt_name.setTextSize(30);
        txt_name.setBackgroundColor(Color.YELLOW);

    }

}