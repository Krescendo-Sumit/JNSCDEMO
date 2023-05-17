package stundet.db.demo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import stundet.db.demo.model.UserTypeModel;

public class HomeScreen extends AppCompatActivity implements View.OnClickListener {
CardView card_usertype;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        card_usertype=findViewById(R.id.card_usertype);
        card_usertype.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
            case R.id.card_usertype:
                Intent intent=new Intent(HomeScreen.this, Emp_type.class);
                startActivity(intent);
                break;
        }
    }
}