package stundet.db.demo;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import java.io.File;
import java.net.URLEncoder;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class MySMSService extends IntentService {

    // TODO: Rename actions, choose action names that describe tasks that this
    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    private static final String ACTION_SMS = "stundet.db.demo.action.SMS";
    private static final String ACTION_WHATSAPP = "stundet.db.demo.action.WHATSAPP";

    // TODO: Rename parameters
    private static final String MESSAGE = "stundet.db.demo.extra.PARAM1";
    private static final String COUNT = "stundet.db.demo.extra.PARAM2";
    private static final String MOBILE = "stundet.db.demo.extra.PARAM3";

    public MySMSService() {
        super("MySMSService");
    }

    /**
     * Starts this service to perform action Foo with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startActionSMS(Context context, String message, String count, String mobile_number) {
        Intent intent = new Intent(context, MySMSService.class);
        intent.setAction(ACTION_SMS);
        intent.putExtra(MESSAGE, message);
        intent.putExtra(COUNT, count);
        intent.putExtra(MOBILE, mobile_number);
        context.startService(intent);
    }

    /**
     * Starts this service to perform action Baz with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startActionWHATSAPP(Context context, String message, String count, String mobile_number) {
        Intent intent = new Intent(context, MySMSService.class);
        intent.setAction(ACTION_WHATSAPP);
        intent.putExtra(MESSAGE, message);
        intent.putExtra(COUNT, count);
        intent.putExtra(MOBILE, mobile_number);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_SMS.equals(action)) {
                final String message = intent.getStringExtra(MESSAGE);
                final String count = intent.getStringExtra(COUNT);
                final String mobile = intent.getStringExtra(MOBILE);
                handleActionSMS(message, count,mobile);
            } else if (ACTION_WHATSAPP.equals(action)) {
                final String message = intent.getStringExtra(MESSAGE);
                final String count = intent.getStringExtra(COUNT);
                final String mobile = intent.getStringExtra(MOBILE);
                handleActionWHATSAPP(message, count,mobile);
            }
        }
    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private void handleActionSMS(String message, String count, String mobile) {
        // TODO: Handle action Foo
        sendBroadcastMessage("SMS");

    }

    /**
     * Handle action Baz in the provided background thread with the provided
     * parameters.
     */
    private void handleActionWHATSAPP(String message, String count, String mobile) {
        // TODO: Handle action Baz
        try{
            PackageManager packageManager=getApplicationContext().getPackageManager();
        String text = "Hello All :";// Replace with your message.
        text += message;
        mobile = mobile.replace(" ", "");
        mobile = mobile.replace("+", "");
        String toNumber = mobile; // Replace with mobile phone number without +Sign or leading zeros, but with country code
        //Suppose your country is India and your phone number is “xxxxxxxxxx”, then you need to send “91xxxxxxxxxx”.

        Log.i("Whatsapp number is",toNumber);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setPackage("com.whatsapp");

        intent.setData(Uri.parse("http://api.whatsapp.com/send?phone=" + toNumber + "&text=" + URLEncoder.encode(text+"    ","UTF-8")));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            File file = Environment.getExternalStorageDirectory();
            File ff=new File(file.getAbsolutePath()+"/Download/1.jpg");
            Log.i("Path11221",Utils.getRootDirPath(this)+"/Download/1.jpg");

       /*     File file =new File(Utils.getRootDirPath(this)+"/11.jpg");
            File ff=new File(file.getAbsolutePath());
            Log.i("Path11221",Utils.getRootDirPath(this)+"/Download/1.jpg");*/

            if(ff.exists())
            {
                Log.i("File Status11212","Exist "+ff.getTotalSpace());
            }else
            {
                Log.i("File Status 112","Not Exist");
            }
            Uri imageUri = Uri.parse(ff.getAbsolutePath());
            Log.i("Path11221q",ff.getAbsolutePath());
            Uri uri = Uri.parse("smsto:" + "919420329047");
            //  Intent shareIntent = new Intent(Intent.ACTION_SENDTO, uri);
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setAction(Intent.ACTION_SEND);
            shareIntent.putExtra("jid", toNumber + "@s.whatsapp.net"); //phone number without "+" prefix

            //Target whatsapp:
            shareIntent.setPackage("com.whatsapp");
            //Add text and then Image URI

            shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            shareIntent.putExtra(Intent.EXTRA_STREAM, imageUri);
            shareIntent.putExtra(Intent.EXTRA_TEXT, text);


            shareIntent.setType("image/jpeg");

            shareIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

          //  intent=shareIntent;



            if(!(shareIntent.resolveActivity(packageManager)!=null)) {

            getApplicationContext().startActivity(shareIntent);
                Thread.sleep(5000);

            sendBroadcastMessage("Whats app send");
        }else
        {
            sendBroadcastMessage("Whats app not Installed123.");
        }
        }catch (Exception e)
        {
            Log.i("Error is",e.getMessage());
            sendBroadcastMessage("Whats app not Installed."+e.getMessage());
        }



    }

    private void sendBroadcastMessage(String message)
    {
        Intent localIntent=new Intent("my.own.broadcast");
        localIntent.putExtra("result",message);
        LocalBroadcastManager.getInstance(this).sendBroadcast(localIntent);
    }
}