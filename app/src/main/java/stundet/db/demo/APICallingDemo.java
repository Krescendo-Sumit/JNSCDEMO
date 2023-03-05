package stundet.db.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

public class APICallingDemo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apicalling_demo);
        new CallAPI().execute();
    }

    public class CallAPI extends AsyncTask {
        final HttpClient Client = new DefaultHttpClient();

        String Content;
        String Error = null;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Object doInBackground(Object[] objects) {
            try {

                HttpPost httpget = new HttpPost("https://feedbackapi.mahyco.com/api/Feedback/getAppFeedbackStatus?packageName=myactvity.mahyco");
                //     httpget.setHeader("Authorization", "Bearer " + mPref.getString(AppConstant.ACCESS_TOKEN_TAG, ""));
                ResponseHandler<String> responseHandler = new BasicResponseHandler();
                Content = Client.execute(httpget, responseHandler);
                return Content;
            } catch (Exception e) {

            }
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            Log.i("Response",o.toString());
        }
    }
}