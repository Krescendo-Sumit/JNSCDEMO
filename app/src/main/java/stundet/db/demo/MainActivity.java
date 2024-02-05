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
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
Context context;
    DBHelper db;
    TextView tv;
    Button  button;
    Button  sms;
    Button  whatsapp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context=MainActivity.this;
        tv=(TextView)findViewById(R.id.text);
        button=(Button)findViewById(R.id.button);
        sms=(Button)findViewById(R.id.button4);
        whatsapp=(Button)findViewById(R.id.button5);
        tv.setText("");

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
                    //   Toast.makeText(context, "" + mobile, Toast.LENGTH_SHORT).show();
                 String text = "Hello All :";// Replace with your message.
                    text += "\n Message : Thank you for connecting me.";
                    String toNumber = "919420329047"; // Replace with mobile phone number without +Sign or leading zeros, but with country code
                    //Suppose your country is India and your phone number is “xxxxxxxxxx”, then you need to send “91xxxxxxxxxx”.
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setPackage("com.whatsapp");
                    intent.setData(Uri.parse("http://api.whatsapp.com/send?phone=" + toNumber + "&text=" + text));
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                   //sendMessage();
                    //sendMessage();
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
}