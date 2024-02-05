package stundet.db.demo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
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
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Locale;

import stundet.db.demo.model.UserTypeModel;

public class Emp_type extends AppCompatActivity {

    EditText et_title, et_level, et_detail;
    Spinner sp_status;
    Button btn_save;
    Button btn_view;
    Context context;

    String str_title, str_level, str_detail, str_status;
    ListView lst;
    ArrayAdapter arrayAdapter;
    UserTypeModel userTypeModel[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emp_type);
        context=Emp_type.this;
        init();
    }

    public void init() {
        et_title = findViewById(R.id.et_title);
        et_level = findViewById(R.id.et_level);
        et_detail = findViewById(R.id.et_details);
        sp_status = findViewById(R.id.sp_status);
        btn_save = findViewById(R.id.btn_save);
        btn_view = findViewById(R.id.btn_view);
        lst = findViewById(R.id.lst);
        btn_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showData();
            }
        });

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitData();
            }
        });
    }

    public void showData() {
    try {
        new GetEmpType().execute();
    }catch (Exception e)
    {

    }
    }

    void submitData() {
        str_title = et_title.getText().toString().trim();
        str_level = et_level.getText().toString().trim();
        str_detail = et_detail.getText().toString().trim();
        str_status = sp_status.getSelectedItem().toString().trim();

        if (str_title.equals("")) {
            et_title.setError("REQUIRED");
        } else if (str_detail.equals("")) {
            et_detail.setError("REQUIRED");
        } else if (str_level.equals("")) {
            et_level.setError("REQUIRED");
        } else if (str_status.equals("") || str_status.equals("Select")) {
            Toast.makeText(context, "Please select the status.", Toast.LENGTH_SHORT).show();
        } else {
new CreateEmpType().execute();
        }

    }

    class CreateEmpType extends AsyncTask {
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
                HttpPost ht = new HttpPost("http://192.168.43.125/demo/createEmpType.php");
                BasicHttpParams basicHttpParams = new BasicHttpParams();
                ArrayList<NameValuePair> postParameters;
                postParameters = new ArrayList<NameValuePair>();
                postParameters.add(new BasicNameValuePair("title", str_title));
                postParameters.add(new BasicNameValuePair("level", str_level));
                postParameters.add(new BasicNameValuePair("detail", str_detail));
                postParameters.add(new BasicNameValuePair("status", str_status));
                postParameters.add(new BasicNameValuePair("admin_id", "1"));
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

            try {

                if(o.toString().contains("Success"))
                {
                    showData();
                    new AlertDialog.Builder(context)
                            .setMessage("Record Saved Successfully.")
                            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })
                    .show();
                }else
                {
                    new AlertDialog.Builder(context)
                            .setMessage("Please retry."+o.toString())
                            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            }).show();
                }

            } catch (Exception e) {
                Toast.makeText(context, "Something went wrong.", Toast.LENGTH_SHORT).show();
            }

            //  Toast.makeText(Apidemo2.this, o.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    class GetEmpType extends AsyncTask {
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
                HttpPost ht = new HttpPost("http://192.168.43.125/demo/viewEmpType.php");
                BasicHttpParams basicHttpParams = new BasicHttpParams();
                ArrayList<NameValuePair> postParameters;

                postParameters = new ArrayList<NameValuePair>();

                postParameters.add(new BasicNameValuePair("admin_id", "1"));
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

            try {
                setListData(o.toString());
                 
            } catch (Exception e) {
                Toast.makeText(context, "Something went wrong.", Toast.LENGTH_SHORT).show();
            }

            //  Toast.makeText(Apidemo2.this, o.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    public void setListData(String toString) {
        try{
            JSONArray jsonArray=new JSONArray(toString);
            if(jsonArray.length()>0)
            {
                userTypeModel=new UserTypeModel[jsonArray.length()];
                for(int i=0;i<jsonArray.length();i++)
                {
                    userTypeModel[i]=new UserTypeModel();
                    JSONObject jsonObject=jsonArray.getJSONObject(i);
                    userTypeModel[i].setId(jsonObject.getString("id"));
                    userTypeModel[i].setTitle(jsonObject.getString("title"));
                    userTypeModel[i].setDetail(jsonObject.getString("detail"));
                    userTypeModel[i].setStatus(jsonObject.getString("status"));
                    userTypeModel[i].setAdmin_id(jsonObject.getString("admin_id"));

                }
                 arrayAdapter =new ArrayAdapter(context, android.R.layout.simple_list_item_1,userTypeModel);
                lst.setAdapter(arrayAdapter);
                lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        UserTypeModel userTypeModel=(UserTypeModel) parent.getItemAtPosition(position);
                        Toast.makeText(context, ""+userTypeModel.getId()+" NAME :"+userTypeModel.getTitle(), Toast.LENGTH_SHORT).show();

                        et_title.setText(userTypeModel.getTitle());
                        et_detail.setText(userTypeModel.getDetail());
                        et_level.setText(userTypeModel.getLevel());

                    }
                });

            }else
            {
                Toast.makeText(context, "No Data found", Toast.LENGTH_SHORT).show();
            }
            
        }catch (Exception e)
        {
            Log.i("Error ",e.getMessage());
            Toast.makeText(context, "Something went wrong."+e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

}