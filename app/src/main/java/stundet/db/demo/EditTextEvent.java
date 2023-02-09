package stundet.db.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EditTextEvent extends AppCompatActivity implements TextWatcher {

    EditText et_data;
    TextView txt_show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_text_event);
        et_data = findViewById(R.id.et_text);
        txt_show = findViewById(R.id.txt);

        et_data.addTextChangedListener(this);

        et_data.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    et_data.setBackgroundColor(Color.GREEN);
                    Toast.makeText(EditTextEvent.this, "In", Toast.LENGTH_SHORT).show();
                } else {
                    et_data.setBackgroundColor(Color.RED);
                    Toast.makeText(EditTextEvent.this, "Out", Toast.LENGTH_SHORT).show();
                }
            }
        });

        txt_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(EditTextEvent.this, ""+txt_show.getText().toString().trim(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        txt_show.setText("" + editable.toString().trim());
    }
}
