package stundet.db.demo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {
Context context;
    DBHelper db;
    TextView tv;
    Button  button;
    Button  sms;
    Button  whatsapp;
    Button  download;
    File file;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context=MainActivity.this;
        tv=(TextView)findViewById(R.id.text);
        button=(Button)findViewById(R.id.button);
        sms=(Button)findViewById(R.id.button4);
        whatsapp=(Button)findViewById(R.id.button5);
        download=(Button)findViewById(R.id.button6);
        tv.setText("");

        file = Environment.getExternalStorageDirectory();
        File ff=new File(file.getAbsolutePath()+"/download/test3.png");
        if(ff.exists())
        {
            Log.i("File Status","Exist "+ff.getTotalSpace());
        }else
        {
            Log.i("File Status","Not Exist");
        }
        Log.i("File Path",file.getAbsolutePath());

   if(!isAccessibilitySettingsOn(getApplicationContext()))
   {
       Intent intent=new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
       intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
       startActivity(intent);
   }
        sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             MySMSService.startActionSMS(context,"Hello","3","9420329047");
            }
        });

        whatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             MySMSService.startActionWHATSAPP(context,"Hello","3","919420329047");
            }
        });

        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try{
               /*     String Url="http://krescendo.co.in/prachar/image/11.jpg";
                    File file = Environment.getExternalStorageDirectory();
                    File ff=new File(file.getAbsolutePath()+"/download/test3.png");
                    downloadFile(Url,ff);*/

                    Intent intent=new Intent(context,Download.class);
                    startActivity(intent);

                }catch (Exception e)
                {
                    Toast.makeText(context, "Error in File Downlaod\n"+e.getMessage(), Toast.LENGTH_SHORT).show();
                }


           }
        });

        db=new DBHelper(this, "ZnSoftech.db", null, 2);

        Cursor c=db.getData();

        if(c.getCount()>0)
        {
            c.moveToFirst();
            do
            {
                String number=c.getString(0);
                String date=c.getString(1);
                String time=c.getString(2);
                String duration=c.getString(3);
                String type=c.getString(4);

                tv.append("Number:"+number+"\nDate:"+date+"\nTime:"+time+"\nDuration:"+duration+"\nCall Type:"+type+"\n\n");
            }while(c.moveToNext());
        }
        else
        {
            tv.setText("No Incoming and Outgoing call history exists!!!");
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    file = Environment.getExternalStorageDirectory();
                    File ff=new File(file.getAbsolutePath()+"/download/test3.png");
                    if(ff.exists())
                    {
                        Log.i("File Status","Exist "+ff.getTotalSpace());
                    }else
                    {
                        Log.i("File Status","Not Exist");
                    }
                    Uri imageUri = Uri.parse(ff.getAbsolutePath());
                    Uri uri = Uri.parse("smsto:" + "919420329047");
                  //  Intent shareIntent = new Intent(Intent.ACTION_SENDTO, uri);
                    Intent shareIntent = new Intent();
                    shareIntent.setAction(Intent.ACTION_SEND);
                    shareIntent.putExtra("jid", "919420329047" + "@s.whatsapp.net"); //phone number without "+" prefix

                    //Target whatsapp:
                    shareIntent.setPackage("com.whatsapp");
                    //Add text and then Image URI
                    shareIntent.putExtra(Intent.EXTRA_TEXT, "Demo asdaa asdsdasd asdsd asd asdsd add asdd");
                    shareIntent.putExtra(Intent.EXTRA_STREAM, imageUri);

                    shareIntent.setType("image/jpeg");
                    shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

                    try {
                        startActivity(shareIntent);
                    } catch (android.content.ActivityNotFoundException ex) {
                        Toast.makeText(MainActivity.this, "Error ", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        if(!checkAccessibilityPermission()){
            Toast.makeText(MainActivity.this, "Permission denied", Toast.LENGTH_SHORT).show();
        }else {
            startService(new Intent(context,WhatsAppAccebilityService.class));
            Toast.makeText(context, "Service Start", Toast.LENGTH_SHORT).show();
        }

        IntentFilter intent=new IntentFilter("my.own.broadcast");
        LocalBroadcastManager.getInstance(this).registerReceiver(myLocalBroadcastReceiver,intent);

    }
    public boolean checkAccessibilityPermission () {
        int accessEnabled = 0;
        try {
            accessEnabled = Settings.Secure.getInt(this.getContentResolver(), Settings.Secure.ACCESSIBILITY_ENABLED);
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
        }
        if (accessEnabled == 0) {
            // if not construct intent to request permission
            Intent intent = new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            // request permission via start activity for result
            startActivity(intent);
            return false;
        } else {
            return true;
        }
    }
    private boolean isAccessibilitySettingsOn(Context mContext) {
        int accessibilityEnabled = 0;
        final String service = getPackageName() + "/" + WhatsAppAccebilityService.class.getCanonicalName();
        try {
            accessibilityEnabled = Settings.Secure.getInt(
                    mContext.getApplicationContext().getContentResolver(),
                    android.provider.Settings.Secure.ACCESSIBILITY_ENABLED);
            Log.v("hemu_check", "accessibilityEnabled = " + accessibilityEnabled);
        } catch (Settings.SettingNotFoundException e) {
            Log.e("hemu_check", "Error finding setting, default accessibility to not found: "
                    + e.getMessage());
        }
        TextUtils.SimpleStringSplitter mStringColonSplitter = new TextUtils.SimpleStringSplitter(':');

        if (accessibilityEnabled == 1) {
            Log.v("hemu_check", "**ACCESSIBILITY IS ENABLED** -----------------");
            String settingValue = Settings.Secure.getString(
                    mContext.getApplicationContext().getContentResolver(),
                    Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES);
            if (settingValue != null) {
                mStringColonSplitter.setString(settingValue);
                while (mStringColonSplitter.hasNext()) {
                    String accessibilityService = mStringColonSplitter.next();

                    Log.v("hemu_check", "-------------- > accessibilityService :: " + accessibilityService + " " + service);
                    if (accessibilityService.equalsIgnoreCase(service)) {
                        Log.v("hemu_check", "We've found the correct setting - accessibility is switched on!");
                        return true;
                    }
                }
            }
        } else {
            Log.v("hemu_check", "***ACCESSIBILITY IS DISABLED***");
        }

        return false;
    }

    public void sendMessage()
    {
        try{
            toString();
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, "Hi Sumit");
            sendIntent.setType("text/plain");
            // Do not forget to add this to open whatsApp App specifically
            sendIntent.setPackage("com.whatsapp");
            startActivity(sendIntent);
        }catch (Exception e)
        {

        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
       getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            db.deleteTable();
            tv.setText("No Incoming and Outgoing call history exists!!!");
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private BroadcastReceiver myLocalBroadcastReceiver=new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
             String result=intent.getStringExtra("result");
            Toast.makeText(context, ""+result, Toast.LENGTH_SHORT).show();
        }
    };

    private void downloadFile(String url1, File outputFile) {
        try {
            URL url = new URL(url1);
            InputStream inputStream = url.openStream();
            FileOutputStream fileOutputStream = new FileOutputStream(Environment.getExternalStorageDirectory() + "/Java_Programming.jpg");
            int length;
            byte[] buffer = new byte[1024];
            while ((length = inputStream.read(buffer)) > -1) {
                fileOutputStream.write(buffer, 0, length);
            }
            fileOutputStream.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}