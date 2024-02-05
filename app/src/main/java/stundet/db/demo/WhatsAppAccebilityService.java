package stundet.db.demo;

import android.accessibilityservice.AccessibilityService;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;

import java.util.List;

public class WhatsAppAccebilityService extends AccessibilityService {
    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
            Log.i("Pass","1");
        if(event.getEventType() == AccessibilityEvent.TYPE_NOTIFICATION_STATE_CHANGED)
        {
            if(event.getPackageName().equals("com.whatsapp"))
            {
                StringBuilder sb = new StringBuilder();
                List<CharSequence> texts = event.getText();
                if (!texts.isEmpty())
                {
                    for (CharSequence s : event.getText())
                    {
                        sb.append(s);
                    }
                    if(sb.toString().equals("Incoming video call"))
                    {
                        Log.d( "onAccessibilityEvent", "whatsapp video call" );
                    }
                }
            }
        }

        Log.i("Pass","2");

        if(getRootInActiveWindow()==null)
        {
            return;
        }
        Log.i("Pass","3");
        AccessibilityNodeInfoCompat rootNodeInfo=AccessibilityNodeInfoCompat.wrap(getRootInActiveWindow());
        List<AccessibilityNodeInfoCompat> messaNodeList=rootNodeInfo.findAccessibilityNodeInfosByViewId("com.whatsapp:id/entry");
        if(messaNodeList==null || messaNodeList.isEmpty())
            return;
        Log.i("Pass","4");
        AccessibilityNodeInfoCompat messageField=messaNodeList.get(0);
        if(messageField==null || messageField.getText().length()==0)
            return;

        Log.i("Pass","5");
        List<AccessibilityNodeInfoCompat> sendMessageNodeList=rootNodeInfo.findAccessibilityNodeInfosByViewId("com.whatsapp:id/send");
        if(sendMessageNodeList==null || sendMessageNodeList.isEmpty())
            return;
        Log.i("Pass","6");
        AccessibilityNodeInfoCompat sendMessage=sendMessageNodeList.get(0);
        if(!sendMessage.isVisibleToUser())
            return;
        sendMessage.performAction(AccessibilityNodeInfoCompat.ACTION_CLICK);
        Log.i("Pass","7");
        try{
              Thread.sleep(2000);
              performGlobalAction(GLOBAL_ACTION_BACK);
              Thread.sleep(2000);
        }catch (Exception e)
        {

        }

        performGlobalAction(GLOBAL_ACTION_BACK);

    }

    @Override
    public void onInterrupt() {
        Log.i("Pass","Intterupt");
    }
}
