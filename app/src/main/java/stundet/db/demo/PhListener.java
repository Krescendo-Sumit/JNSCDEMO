package stundet.db.demo;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.CallLog;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.widget.Toast;

@SuppressLint("SimpleDateFormat") public class PhListener extends BroadcastReceiver {
 Context c;
    @Override
    public void onReceive(Context c, Intent i) {
        // TODO Auto-generated method stub
        Bundle bundle=i.getExtras();
        String number="";
        this.c=c;
        if(bundle==null)
            return;

        SharedPreferences sp=c.getSharedPreferences("ZnSoftech", Activity.MODE_PRIVATE);

        String s=bundle.getString(TelephonyManager.EXTRA_STATE);

        if(i.getAction().equals(Intent.ACTION_NEW_OUTGOING_CALL))
        {
            Toast.makeText(c, "Outgoing call ", Toast.LENGTH_SHORT).show();
             number=i.getStringExtra(Intent.EXTRA_PHONE_NUMBER);
            sp.edit().putString("number", number).commit();
            sp.edit().putString("state", s).commit();
            whatsApp(number);
        }

        else if(s.equals(TelephonyManager.EXTRA_STATE_RINGING))
        {

            Toast.makeText(c, "Incomming call ", Toast.LENGTH_SHORT).show();
             number=bundle.getString("incoming_number");
            sp.edit().putString("number", number).commit();
            sp.edit().putString("state", s).commit();
            sendSMS(number,"Message From Sumit");
            whatsApp(number);
        }

        else if(s.equals(TelephonyManager.EXTRA_STATE_OFFHOOK))
        {
            Toast.makeText(c, "OFF Hook ", Toast.LENGTH_SHORT).show();
            sp.edit().putString("state", s).commit();
        }

        else if(s.equals(TelephonyManager.EXTRA_STATE_IDLE))
        {
            Toast.makeText(c, "IDle ", Toast.LENGTH_SHORT).show();
            String state=sp.getString("state", null);
            if(!state.equals(TelephonyManager.EXTRA_STATE_IDLE))
            {
                sp.edit().putString("state", null).commit();


                History h=new History(new Handler(),c);
                c.getContentResolver().registerContentObserver(CallLog.Calls.CONTENT_URI, true, h);
            }
            sp.edit().putString("state", s).commit();

        }

    }
    public void sendSMS(String phoneNo, String msg) {
        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNo, null, msg, null, null);
            Toast.makeText(c, "Message Sent",
                    Toast.LENGTH_LONG).show();
        } catch (Exception ex) {
            Toast.makeText(c,ex.getMessage().toString(),
                    Toast.LENGTH_LONG).show();
            ex.printStackTrace();
        }
    }
    public void whatsApp(String mobile) {
        try {
            try {
                Toast.makeText(c, "Mobile"+mobile, Toast.LENGTH_SHORT).show();
                MySMSService.startActionWHATSAPP(c,"Hello","3",mobile);



             /*   Toast.makeText(c, "Mobile From " + mobile, Toast.LENGTH_SHORT).show();
                String text = "Hello All :";// Replace with your message.
                text += "\n Message : Thank you for connecting me.";
                String toNumber = ""+mobile; // Replace with mobile phone number without +Sign or leading zeros, but with country code
                //Suppose your country is India and your phone number is “xxxxxxxxxx”, then you need to send “91xxxxxxxxxx”.


                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://api.whatsapp.com/send?phone=" + toNumber + "&text=" + text));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                c.startActivity(intent);*/
            } catch (Exception e) {
                Toast.makeText(c, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                e.printStackTrace();

            }
        } catch (Exception e) {
            //e.toString();
        }
    }

}