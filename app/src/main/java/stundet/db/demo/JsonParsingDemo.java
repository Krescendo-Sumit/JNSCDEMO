package stundet.db.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

public class JsonParsingDemo extends AppCompatActivity {
ListView lst;
ArrayAdapter arrayAdapter;
String name[];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json_parsing_demo);

        lst=findViewById(R.id.lst);
        String str="[\n" +
                "  {\n" +
                "    \"ProductId\": 2,\n" +
                "    \"CropId\": 2,\n" +
                "    \"ProductType\": \"\",\n" +
                "    \"ProductCode\": \"1130053A\",\n" +
                "    \"ProductName\": \"COTTON MECH-118 HY\",\n" +
                "    \"IsDelete\": true,\n" +
                "    \"CreatedBy\": \"Junaid Siddiqui\",\n" +
                "    \"CreatedDt\": \"2022-10-07T16:30:28.91\",\n" +
                "    \"ModifiedBy\": \"55000066\",\n" +
                "    \"ModifiedDt\": \"2022-11-26T10:31:48.52\",\n" +
                "    \"CropCode\": \"113\",\n" +
                "    \"CropName\": \"COTTON\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"ProductId\": 3,\n" +
                "    \"CropId\": 3,\n" +
                "    \"ProductType\": \"\",\n" +
                "    \"ProductCode\": \"113000502A\",\n" +
                "    \"ProductName\": \"C 571 HY-UNP\",\n" +
                "    \"IsDelete\": true,\n" +
                "    \"CreatedBy\": \"Junaid Siddiqui\",\n" +
                "    \"CreatedDt\": \"2022-10-07T16:30:28.91\",\n" +
                "    \"ModifiedBy\": \"55000066\",\n" +
                "    \"ModifiedDt\": \"2022-11-26T10:31:49.35\",\n" +
                "    \"CropCode\": \"113\",\n" +
                "    \"CropName\": \"American/Indian Cotton\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"ProductId\": 4,\n" +
                "    \"CropId\": 4,\n" +
                "    \"ProductType\": \"\",\n" +
                "    \"ProductCode\": \"115000069\",\n" +
                "    \"ProductName\": \"COTTON MRC-7365 (BALRAJ PLUS)\",\n" +
                "    \"IsDelete\": true,\n" +
                "    \"CreatedBy\": \"55000066\",\n" +
                "    \"CreatedDt\": \"2022-11-07T10:17:23.78\",\n" +
                "    \"ModifiedBy\": \"55000066\",\n" +
                "    \"ModifiedDt\": \"2022-11-26T10:31:47.56\",\n" +
                "    \"CropCode\": \"115\",\n" +
                "    \"CropName\": \"Transgenic Cotton\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"ProductId\": 5,\n" +
                "    \"CropId\": 6,\n" +
                "    \"ProductType\": \"\",\n" +
                "    \"ProductCode\": \"102000001\",\n" +
                "    \"ProductName\": \"PADDY MRP-5629\",\n" +
                "    \"IsDelete\": true,\n" +
                "    \"CreatedBy\": \"55000066\",\n" +
                "    \"CreatedDt\": \"2022-11-08T14:56:41.55\",\n" +
                "    \"ModifiedBy\": \"55000066\",\n" +
                "    \"ModifiedDt\": \"2022-11-26T10:31:22.053\",\n" +
                "    \"CropCode\": \"102\",\n" +
                "    \"CropName\": \"Paddy\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"ProductId\": 6,\n" +
                "    \"CropId\": 5,\n" +
                "    \"ProductType\": \"\",\n" +
                "    \"ProductCode\": \"105000095\",\n" +
                "    \"ProductName\": \"MAIZE MRM-4010\",\n" +
                "    \"IsDelete\": true,\n" +
                "    \"CreatedBy\": \"55000066\",\n" +
                "    \"CreatedDt\": \"2022-11-08T14:57:46.957\",\n" +
                "    \"ModifiedBy\": \"55000066\",\n" +
                "    \"ModifiedDt\": \"2022-11-26T10:31:27.42\",\n" +
                "    \"CropCode\": \"105\",\n" +
                "    \"CropName\": \"Maize\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"ProductId\": 8,\n" +
                "    \"CropId\": 3,\n" +
                "    \"ProductType\": \"\",\n" +
                "    \"ProductCode\": \"113000502A\",\n" +
                "    \"ProductName\": \"Cotton 571\",\n" +
                "    \"IsDelete\": true,\n" +
                "    \"CreatedBy\": \"55000066\",\n" +
                "    \"CreatedDt\": \"2022-11-08T15:08:05.687\",\n" +
                "    \"ModifiedBy\": \"55000066\",\n" +
                "    \"ModifiedDt\": \"2022-11-26T10:31:50.427\",\n" +
                "    \"CropCode\": \"113\",\n" +
                "    \"CropName\": \"American/Indian Cotton\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"ProductId\": 9,\n" +
                "    \"CropId\": 3,\n" +
                "    \"ProductType\": \"\",\n" +
                "    \"ProductCode\": \"113000501A\",\n" +
                "    \"ProductName\": \"COTTON 567\",\n" +
                "    \"IsDelete\": true,\n" +
                "    \"CreatedBy\": \"55000066\",\n" +
                "    \"CreatedDt\": \"2022-11-14T15:32:46.84\",\n" +
                "    \"ModifiedBy\": \"55000066\",\n" +
                "    \"ModifiedDt\": \"2022-11-26T10:31:44.83\",\n" +
                "    \"CropCode\": \"113\",\n" +
                "    \"CropName\": \"American/Indian Cotton\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"ProductId\": 10,\n" +
                "    \"CropId\": 3,\n" +
                "    \"ProductType\": \"\",\n" +
                "    \"ProductCode\": \"113000502A\",\n" +
                "    \"ProductName\": \"MAHYCO C 571\",\n" +
                "    \"IsDelete\": false,\n" +
                "    \"CreatedBy\": \"55000066\",\n" +
                "    \"CreatedDt\": \"2022-11-26T10:33:21.09\",\n" +
                "    \"ModifiedBy\": \"97190987\",\n" +
                "    \"ModifiedDt\": \"2022-11-30T23:58:51.6\",\n" +
                "    \"CropCode\": \"113\",\n" +
                "    \"CropName\": \"American/Indian Cotton\"\n" +
                "  }\n" +
                "]";
        try{
         /*   JSONObject jsonObject=new JSONObject(str);
            Toast.makeText(this, ""+jsonObject.getString("KeyValue"), Toast.LENGTH_SHORT).show();
*/
            JSONArray jsonArray=new JSONArray(str);
            name=new String[jsonArray.length()];
            for(int i=0;i<jsonArray.length();i++)
            {
                JSONObject object=jsonArray.getJSONObject(i);
                Log.i("ProductName",object.getString("ProductName"));
                name[i]=object.getString("ProductName");
            }

            arrayAdapter=new ArrayAdapter(JsonParsingDemo.this, android.R.layout.simple_list_item_1,name);
            lst.setAdapter(arrayAdapter);
            lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(JsonParsingDemo.this, ""+name[position], Toast.LENGTH_SHORT).show();
                }
            });


        }catch (Exception e)
        {
            Log.i("Error is ",e.getMessage());
            Toast.makeText(this, "Error is "+e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }
}