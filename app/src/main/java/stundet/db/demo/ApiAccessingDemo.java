package stundet.db.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.json.JSONArray;
import org.json.JSONObject;

public class ApiAccessingDemo extends AppCompatActivity {
Context context;
    ListView lst;
    ArrayAdapter arrayAdapter;
    String name[];

    EditText et_name,et_address,et_mobile;
    Button btnsave;

    String str_name,str_address,str_mobile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_api_accessing_demo);
        context=ApiAccessingDemo.this;
        lst=findViewById(R.id.lst);

        et_name=findViewById(R.id.et_name);
        et_address=findViewById(R.id.et_address);
        et_mobile=findViewById(R.id.et_mobile);
        btnsave=findViewById(R.id.btnsave);
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str_name=et_name.getText().toString().trim().replace(" ","%20");
                        str_address=et_address.getText().toString().trim().replace(" ","%20");
                str_mobile=et_mobile.getText().toString().trim().replace(" ","%20");
                new CallAPI_Insert().execute();
            }
        });
    }
    public void call(View v)
    {
        try{
             Toast.makeText(context, "API Calling", Toast.LENGTH_SHORT).show();
             new CallAPI().execute();
        }catch (Exception e)
        {

        }
    }
    class CallAPI extends AsyncTask{
        HttpClient httpClient=new DefaultHttpClient();
        String Content="";
        ProgressDialog dialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog=new ProgressDialog(context);
            dialog.setMessage("Please wait...");
            dialog.show();
        }

        @Override
        protected Object doInBackground(Object[] objects) {
            try{

                HttpPost httpget = new HttpPost("http://pbk.krescendo.co.in/getVideoBatchMaster.php");
                //     httpget.setHeader("Authorization", "Bearer " + mPref.getString(AppConstant.ACCESS_TOKEN_TAG, ""));
                ResponseHandler<String> responseHandler = new BasicResponseHandler();
                Content = httpClient.execute(httpget, responseHandler);
                return Content;
            }catch(Exception e)
            {
                Log.i("Error is "," "+e.getMessage());
            }


            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            dialog.dismiss();
            Log.i("Response From API :",o.toString());
            showdata(o.toString());
        }
    }



    class CallAPI_Insert extends AsyncTask{
        HttpClient httpClient=new DefaultHttpClient();
        String Content="";
        ProgressDialog dialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog=new ProgressDialog(context);
            dialog.setMessage("Please wait...");
            dialog.show();
        }

        @Override
        protected Object doInBackground(Object[] objects) {
            try{

                //HttpPost httpget = new HttpPost("http://192.168.43.125/demoapi/demo.php?name="+str_name+"&address="+str_address+"&mobile="+str_mobile);
                HttpPost httpget = new HttpPost("http://192.168.43.125/demoapi/demo.php");
                //     httpget.setHeader("Authorization", "Bearer " + mPref.getString(AppConstant.ACCESS_TOKEN_TAG, ""));
                BasicHttpParams basicHttpParams=new BasicHttpParams();
                basicHttpParams.setParameter("name",str_name);
                basicHttpParams.setParameter("address",str_address);
                basicHttpParams.setParameter("mobile",str_mobile);
                 httpget.setParams(basicHttpParams);
                ResponseHandler<String> responseHandler = new BasicResponseHandler();
                Content = httpClient.execute(httpget, responseHandler);
                return Content;
            }catch(Exception e)
            {
                Log.i("Error is "," "+e.getMessage());
            }


            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            dialog.dismiss();
            Log.i("Response From API :",o.toString());
            Toast.makeText(ApiAccessingDemo.this,o.toString(),Toast.LENGTH_SHORT).show();

//            showdata(o.toString());
        }
    }



    private void showdata(String str) {

            try{
         /*   JSONObject jsonObject=new JSONObject(str);
            Toast.makeText(this, ""+jsonObject.getString("KeyValue"), Toast.LENGTH_SHORT).show();
*/
                JSONArray jsonArray=new JSONArray(str);
                name=new String[jsonArray.length()];
                for(int i=0;i<jsonArray.length();i++)
                {
                    JSONObject object=jsonArray.getJSONObject(i);
                    Log.i("ProductName",object.getString("title"));
                    name[i]=object.getString("title");
                }

                arrayAdapter=new ArrayAdapter(context, android.R.layout.simple_list_item_1,name);
                lst.setAdapter(arrayAdapter);
                lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Toast.makeText(context, ""+name[position], Toast.LENGTH_SHORT).show();
                    }
                });


            }catch (Exception e)
            {
                Log.i("Error is ",e.getMessage());
                Toast.makeText(this, "Error is "+e.getMessage(), Toast.LENGTH_SHORT).show();
            }


    }



}