package stundet.db.demo;

import android.accessibilityservice.AccessibilityService;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Toast;

import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;

import java.util.List;

public class WhatsAppAccebilityService extends AccessibilityService {

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {

        if(AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED == event.getEventType()) {
        Log.e("this", "ACC::onAccessibilityEvent : event=" + event);

        AccessibilityNodeInfo nodeInfo = event.getSource();
        Log.e("this", "ACC::onAccessibilityEvent : nodeInfo=" + nodeInfo);
        if (nodeInfo == null) {
            return;
        }


        //get whatsapp send message button node list
        List<AccessibilityNodeInfo> sendMessageNodeList = nodeInfo.findAccessibilityNodeInfosByViewId("com.whatsapp:id/send");
        if (sendMessageNodeList == null) {
            Log.v("hritik", "sendMessageNodeList is null");
        }
        for (AccessibilityNodeInfo node : sendMessageNodeList) {
            Log.e("this", "ACC::onAccessibilityEvent : send_button=" + node);
            node.performAction(AccessibilityNodeInfo.ACTION_CLICK);
            try {
                Thread.sleep(5000); // hack for certain devices in which the immediate back click is too fast to handle
                performGlobalAction(GLOBAL_ACTION_BACK);
                Thread.sleep(2000);  // same hack as above
               // performGlobalAction(GLOBAL_ACTION_BACK);
            } catch (InterruptedException ignored) {
            }
            // performGlobalAction (GLOBAL_ACTION_BACK);
        }
    }

}



    @Override
    public void onInterrupt() {
        Toast.makeText(getApplicationContext(), "not able to send", Toast.LENGTH_SHORT).show();

    }
}
