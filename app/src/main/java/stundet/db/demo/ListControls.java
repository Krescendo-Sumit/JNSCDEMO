package stundet.db.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Spinner;
import android.widget.Toast;

public class ListControls extends AppCompatActivity {

    Spinner spinner;
    AutoCompleteTextView autoCompleteTextView;
    String items[]={"Sachin Tendulkar","Kapil Dev","Virat Kohli","Rohit Sharma"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_controls);
        spinner=findViewById(R.id.spinner);
        autoCompleteTextView=findViewById(R.id.autoCompleteTextView);
        autoCompleteTextView.setThreshold(3);
        ArrayAdapter arrayAdapter=new ArrayAdapter(ListControls.this, android.R.layout.simple_list_item_1,items);
        ArrayAdapter arrayAdapter1=new ArrayAdapter(ListControls.this, android.R.layout.simple_list_item_1,items);
        spinner.setAdapter(arrayAdapter);
        autoCompleteTextView.setAdapter(arrayAdapter1);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                String name=spinner.getSelectedItem().toString().trim();
                Toast.makeText(ListControls.this, i+" "+name+" "+l, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(ListControls.this, ""+autoCompleteTextView.getText(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}