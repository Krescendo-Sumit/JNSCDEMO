package stundet.db.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.json.JSONArray;

import java.util.ArrayList;

public class LoginScreen extends AppCompatActivity {
Context context;
EditText et_mobile,et_password;
Button btn_login;
TextView txt_createuser;

String str_mobile,str_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        context=LoginScreen.this;
        init();

    }
    public void init()
    {
        et_mobile=findViewById(R.id.et_mobile);
        et_password=findViewById(R.id.et_password);
        btn_login=findViewById(R.id.btn_login);
        txt_createuser=findViewById(R.id.txt_createuser);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str_mobile=et_mobile.getText().toString().trim();
                str_password=et_password.getText().toString().trim();
                if(validation()) {
                    validateLogin(str_mobile, str_password);
                }
            }
        });

    }

     void validateLogin(String str_mobile, String str_password) {
         Toast.makeText(context, "Validation Done.", Toast.LENGTH_SHORT).show();
       new CheckLogin().execute();
    }

    boolean validation() {
        if(str_mobile.equals(""))
        {
            et_mobile.setError("Enter mobile");
            return false;
        }else if(str_mobile.length()<10 || str_mobile.length()>10)
        {
            et_mobile.setError("Invalid Number");
            return false;
        }else if(str_password.equals(""))
        {
            et_password.setError("Enter Passoword");
            return false;
        }
        else
        {
            return true;
        }
    }

    class CheckLogin extends AsyncTask
    {
        HttpClient httpClient = new DefaultHttpClient();
        String Content = "";
        ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            super.onPreExecute();
            dialog = new ProgressDialog(context);
            dialog.setMessage("Please wait...");
            dialog.show();
        }

        @Override
        protected Object doInBackground(Object[] objects) {
            try {
                HttpPost ht = new HttpPost("http://192.168.43.125/demo/getGroupsLogin.php");
                BasicHttpParams basicHttpParams = new BasicHttpParams();
                ArrayList<NameValuePair> postParameters;

                postParameters = new ArrayList<NameValuePair>();
                postParameters.add(new BasicNameValuePair("password", str_password));
                postParameters.add(new BasicNameValuePair("mobile", str_mobile));

                ht.setEntity(new UrlEncodedFormEntity(postParameters, "UTF-8"));
//                    basicHttpParams.setParameter("name", str_nm);
//                    basicHttpParams.setParameter("mobile", str_mob);
//                    basicHttpParams.setParameter("div", str_div);
//                    ht.setParams(basicHttpParams);
                ResponseHandler<String> responseHandler = new BasicResponseHandler();
                Content = httpClient.execute(ht, responseHandler);
                return Content;
            } catch (Exception e) {
                Log.i("Error is ", " " + e.getMessage());
            }

            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            super.onPostExecute(o);
            dialog.dismiss();
            Log.i("Response From API :", o.toString());
            
            try{

                JSONArray jsonArray=new JSONArray(o.toString());
                if(jsonArray.length()>0)
                {
                    Intent intent=new Intent(context,Emp_type.class);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(context, "Invalid Username and Password.", Toast.LENGTH_SHORT).show();
                }
                
            }catch (Exception e)
            {
                Toast.makeText(context, "Something went wrong.", Toast.LENGTH_SHORT).show();
            }
            
          //  Toast.makeText(Apidemo2.this, o.toString(), Toast.LENGTH_SHORT).show();
        }
    }



}