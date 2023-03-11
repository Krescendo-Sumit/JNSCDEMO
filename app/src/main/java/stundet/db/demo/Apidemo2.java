package stundet.db.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import java.util.ArrayList;

public class Apidemo2 extends AppCompatActivity {
    EditText ed1, ed2, ed3;
    Button btn;
    String str_nm, str_mob, str_div;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apidemo2);

        ed1 = findViewById(R.id.editText1);
        ed2 = findViewById(R.id.editText2);
        ed3 = findViewById(R.id.editText3);
        btn = findViewById(R.id.button9);
context=Apidemo2.this;

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str_nm = ed1.getText().toString().trim();
                str_mob = ed2.getText().toString().trim();
                str_div = ed3.getText().toString().trim();
                new Insertdemo().execute();
            }
        });
    }

      /*  class Stud_insert extends AsyncTask {
            HttpClient httpClient = new DefaultHttpClient();
            String Content = "";
            ProgressDialog dialog;

            protected void onPreExecute() {
                super.onPreExecute();
                dialog = new ProgressDialog(context);
                dialog.setMessage("Please wait...");
                dialog.show();
            }
            @Override
            protected Object doInBackground(Object[] objects) {
                try {
                    HttpPost ht = new HttpPost("http://192.168.43.125/student123/stud.php");
                    BasicHttpParams basicHttpParams = new BasicHttpParams();
                    basicHttpParams.setParameter("name", str_nm);
                    basicHttpParams.setParameter("mobile", str_mob);
                    basicHttpParams.setParameter("division", str_div);
                    ht.setParams(basicHttpParams);
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
                dialog.dismiss();
                Log.i("Response From API :", o.toString());
                Toast.makeText(Apidemo2.this, o.toString(), Toast.LENGTH_SHORT).show();
            }

        }*/
        class Insertdemo extends AsyncTask
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
                    HttpPost ht = new HttpPost("http://192.168.43.125/student123/stud.php");
                    BasicHttpParams basicHttpParams = new BasicHttpParams();
                    ArrayList<NameValuePair> postParameters;

                    postParameters = new ArrayList<NameValuePair>();
                    postParameters.add(new BasicNameValuePair("name", str_nm));
                    postParameters.add(new BasicNameValuePair("mobile", str_mob));
                    postParameters.add(new BasicNameValuePair("division", str_div));

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
                Toast.makeText(Apidemo2.this, o.toString(), Toast.LENGTH_SHORT).show();
            }
            }
        }


