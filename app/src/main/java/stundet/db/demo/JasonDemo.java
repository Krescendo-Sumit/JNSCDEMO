package stundet.db.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

public class JasonDemo extends AppCompatActivity {
TextView t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jason_demo);
        t=findViewById(R.id.textView10);
        String s = " {\n" +
                "    \"CropId\": 2,\n" +
                "    \"CropCode\": \"113\",\n" +
                "    \"CropName\": \"COTTON\",\n" +
                "    \"IsDelete\": true,\n" +
                "    \"CreatedBy\": \"Junaid Siddiqui\",\n" +
                "    \"CreatedDt\": \"2022-10-06T15:55:28.627\",\n" +
                "    \"ModifiedBy\": \"Junaid Siddiqui\",\n" +
                "    \"ModifiedDt\": \"2022-10-07T16:24:26.52\"\n" +
                "  }";
        try {
            JSONObject j=new JSONObject(s);
            //Toast.makeText(this, j.getString("CreatedBy")+j.getString("CropCode"), Toast.LENGTH_SHORT).show();
            t.setText(j.getString("CreatedBy")+j.getString("CropCode"));
        }
        catch(Exception e){
            Toast.makeText(this, "Exception"+e, Toast.LENGTH_SHORT).show();
        }
    }
}