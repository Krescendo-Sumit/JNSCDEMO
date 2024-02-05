package stundet.db.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.Toast;

import org.apache.http.message.BasicNameValuePair;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import stundet.db.demo.apicalling.RetrofitClient;
import stundet.db.demo.model.GroupModel;
import stundet.db.demo.model.MessageModel;

public class RetrofitActivity extends AppCompatActivity {
Button btn2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);
        btn2=findViewById(R.id.button2);

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getStudent();
            }
        });
    }

    void getStudent() {

        try{



            Call<List<MessageModel>> call = RetrofitClient.getInstance().getMyApi().insertEmpType("Title","detal","le","sts","1");
            call.enqueue(new Callback<List<MessageModel>>() {
                @Override
                public void onResponse(Call<List<MessageModel>> call, Response<List<MessageModel>> response) {
                    List<MessageModel> myheroList = response.body();
                    Toast.makeText(RetrofitActivity.this, "Size "+myheroList.size(), Toast.LENGTH_SHORT).show();
                    Log.i("Response From ",""+myheroList.size());

                    for (MessageModel messageModel:myheroList)
                    {
                        Log.i("Data",messageModel.getComment());
                    }

              }

                @Override
                public void onFailure(Call<List<MessageModel>> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "An error has occured" +t.getMessage(), Toast.LENGTH_LONG).show();
                }

            });




        }catch (Exception e)
        {

        }

    }
}