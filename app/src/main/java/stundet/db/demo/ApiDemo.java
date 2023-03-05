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
import android.widget.ListView;
import android.widget.Toast;

import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.json.JSONArray;
import org.json.JSONObject;

public class ApiDemo extends AppCompatActivity {
    Context context;
    ListView l;
    String name[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_api_demo);
        context = ApiDemo.this;
        l = findViewById(R.id.list123);
    }

    public void call(View view) {
        try {
            Toast.makeText(this, "Calling Api", Toast.LENGTH_SHORT).show();
            new Call1().execute();

        } catch (Exception e) {
            Log.i("error", e.getMessage());
        }
    }

    class Call1 extends AsyncTask {
        HttpClient httpClient = new DefaultHttpClient();
        String content = "";
        ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(context);
            dialog.setMessage("Please wait...");
            dialog.show();
        }

        @Override
        protected Object doInBackground(Object[] objects) {
            try {
                HttpPost http = new HttpPost("http://pbk.krescendo.co.in/getVideoBatchMaster.php");
                ResponseHandler<String> responseHandler = new BasicResponseHandler();
                content = httpClient.execute(http, responseHandler);
                return content;

            } catch (Exception e) {
                Log.i("Error is ", " " + e.getMessage());

            }
            return null;
        }

        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            Log.i("Response From API :", o.toString());
            dialog.dismiss();
            Log.i("Response From API :", o.toString());
            showlist(o.toString());
        }
    }

    public void showlist(String str) {
        try {

            JSONArray jsonArray = new JSONArray(str);
            name = new String[jsonArray.length()];
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);
                Log.i("ProductName", object.getString("title"));
                name[i] = object.getString("title");
            }
            ArrayAdapter arrayAdapter = new ArrayAdapter(context, android.R.layout.simple_list_item_1, name);
            l.setAdapter(arrayAdapter);
            l.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(context, "" + name[position], Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception e) {
            Log.i("Error is ", e.getMessage());
            Toast.makeText(this, "Error is " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

}

