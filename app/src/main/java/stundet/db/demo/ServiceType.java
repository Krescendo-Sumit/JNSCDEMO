package stundet.db.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;

import java.io.IOException;
import java.util.ArrayList;

public class ServiceType extends AppCompatActivity {
EditText et_title,et_detail,et_type;
Spinner sp;
Button btn_save,btn_show;
Context context;
String strtitle,strdetail,strtype,strstatus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_type);
        context=ServiceType.this;
        init();
    }
    public void init()
    {
        et_title=findViewById(R.id.et_sertitle);
        et_detail=findViewById(R.id.et_details);
        et_type=findViewById(R.id.et_sertype);
        sp=findViewById(R.id.spinner);

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savedata();
            }
        });
    }
    public void savedata()
    {
        strtitle = et_title.getText().toString().trim();
        strdetail = et_detail.getText().toString().trim();
        strtype = et_type.getText().toString().trim();
        strstatus = sp.getSelectedItem().toString().trim();

        if (strtitle.equals("")) {
            et_title.setError("REQUIRED");
        } else if (strdetail.equals("")) {
            et_detail.setError("REQUIRED");
        } else if (strtype.equals("")) {
            et_type.setError("REQUIRED");
        } else if (strstatus.equals("") || strstatus.equals("Select")) {
            Toast.makeText(context, "Please select the status.", Toast.LENGTH_SHORT).show();
        } else {
            new CreateService().execute();
        }
    }
    class CreateService extends AsyncTask
    {
        HttpClient httpClient = new DefaultHttpClient();
        String Content = "";
        ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog.setMessage("Please Wait......");
            dialog.show();
        }

        @Override
        protected Object doInBackground(Object[] objects) {
            try {


                    HttpPost httpPost = new HttpPost("http://192.168.43.125/demo/createServiceType.php");
                    BasicHttpParams httpParams = new BasicHttpParams();
                    ArrayList<NameValuePair> parameter=new ArrayList<>();

                    parameter.add(new BasicNameValuePair("title", strtitle));
                    parameter.add(new BasicNameValuePair("detail", strdetail));
                    parameter.add(new BasicNameValuePair("stype", strtype));
                    parameter.add(new BasicNameValuePair("status", strstatus));
                    httpPost.setEntity(new UrlEncodedFormEntity(parameter,"UTF-8"));
                    ResponseHandler<String> responseHandler = new BasicResponseHandler();
                    Content = httpClient.execute(httpPost,responseHandler);
                    return Content;
                } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);

        }
    }

}

