package stundet.db.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DialogDemo extends AppCompatActivity {
    Button button3;
    Dialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_demo);
        button3=findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DialogDemo.this, "Button Clicked", Toast.LENGTH_SHORT).show();
                dialog=new Dialog(DialogDemo.this);
                dialog.setCanceledOnTouchOutside(false);
                dialog.setContentView(R.layout.popup_login);
                EditText et_username,et_password;
                Button btn_login;

                et_username=dialog.findViewById(R.id.et_username);
                et_password=dialog.findViewById(R.id.et_password);
                btn_login=dialog.findViewById(R.id.btn_login);

                btn_login.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String uname=et_username.getText().toString().trim();
                        String pass=et_password.getText().toString().trim();

                        Toast.makeText(DialogDemo.this, "Username: "+uname+"\nPassword:"+pass, Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });
    }
}