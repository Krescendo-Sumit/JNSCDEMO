package stundet.db.demo;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.CallLog;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

import java.util.Date;

@SuppressLint("SimpleDateFormat") public class PhListener extends BroadcastReceiver {
 Context c;
    private static int lastState = TelephonyManager.CALL_STATE_IDLE;
    private static Date callStartTime;
    private static boolean isIncoming;
    private static String savedNumber;


    @Override
    public void onReceive(Context context, Intent intent) {
        c=context;
        if (intent.getAction().equals("android.intent.action.NEW_OUTGOING_CALL")) {
            savedNumber = intent.getExtras().getString("android.intent.extra.PHONE_NUMBER");
            Toast.makeText(context, ""+savedNumber, Toast.LENGTH_SHORT).show();
        } else {
            String stateStr = intent.getExtras().getString(TelephonyManager.EXTRA_STATE);
            String number = intent.getExtras().getString(TelephonyManager.EXTRA_INCOMING_NUMBER);

            int state = 0;
            if (stateStr.equals(TelephonyManager.EXTRA_STATE_IDLE)) {
                state = TelephonyManager.CALL_STATE_IDLE;
            } else if (stateStr.equals(TelephonyManager.EXTRA_STATE_OFFHOOK)) {
                state = TelephonyManager.CALL_STATE_OFFHOOK;
            } else if (stateStr.equals(TelephonyManager.EXTRA_STATE_RINGING)) {
                state = TelephonyManager.CALL_STATE_RINGING;
            }
            onCallStateChanged(context, state, savedNumber, intent);

        }
    }

    public void onCallStateChanged(Context context, int state, String number, Intent intent) {
        if (lastState == state) {
            //No change, debounce extras
            return;
        }
        switch (state) {
            case TelephonyManager.CALL_STATE_RINGING:
                isIncoming = true;
                callStartTime = new Date();
                onIncomingCallStarted(context, number, callStartTime, intent);
                break;
            case TelephonyManager.CALL_STATE_OFFHOOK:
                //Transition of ringing->offhook are pickups of incoming calls.  Nothing done on them
                if (lastState != TelephonyManager.CALL_STATE_RINGING) {
                    isIncoming = false;
                    callStartTime = new Date();
                    onOutgoingCallStarted(context, number, callStartTime, intent);
                }
                break;
            case TelephonyManager.CALL_STATE_IDLE:
                //Went to idle-  this is the end of a call.  What type depends on previous state(s)
                if (lastState == TelephonyManager.CALL_STATE_RINGING) {
                    //Ring but no pickup-  a miss
                    onMissedCall(context, number, callStartTime, intent);
                } else if (isIncoming) {
                    onIncomingCallEnded(context, number, callStartTime, new Date(), intent);
                } else {
                    onOutgoingCallEnded(context, number, callStartTime, new Date(), intent);
                }
                break;
        }
        lastState = state;
        Intent intent1 = new Intent("CallApp");
        context.sendBroadcast(intent1);
    }

    protected void onIncomingCallStarted(Context ctx, String number, Date start, Intent intent) {
        Toast.makeText(ctx, "calling from " + number, Toast.LENGTH_SHORT).show();
    }

    protected void onOutgoingCallStarted(Context ctx, String number, Date start, Intent intent) {
        Toast.makeText(ctx, "calling to " + number, Toast.LENGTH_SHORT).show();
    }

    protected void onIncomingCallEnded(Context ctx, String number, Date start, Date end, Intent intent) {
        Toast.makeText(ctx, "calling from " + number + " ended ", Toast.LENGTH_SHORT).show();
//        saveData(ctx, number, intent, "Incoming Call");
    }

    protected void onOutgoingCallEnded(Context ctx, String number, Date start, Date end, Intent intent) {
        Toast.makeText(ctx, "calling to " + number + " ended ", Toast.LENGTH_SHORT).show();
//        saveData(ctx, number, intent, "Outgoing Call");
         MySMSService.startActionWHATSAPP(c,"Hello "+number+", Thank you for connecting to me.","2",number);
    }

    protected void onMissedCall(Context ctx, String number, Date start, Intent intent) {
        Toast.makeText(ctx, "missed call from " + number + " sim ", Toast.LENGTH_SHORT).show();
      ///  saveData(ctx, number, intent, "Missed Call");
    }

}