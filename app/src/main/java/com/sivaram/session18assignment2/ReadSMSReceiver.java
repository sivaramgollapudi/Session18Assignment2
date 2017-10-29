package com.sivaram.session18assignment2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Telephony;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.widget.Toast;

/**
 * Created by User on 29/10/2017.
 */

public class ReadSMSReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // Create Bundle Object to retrieve data from intent
        final Bundle bundle = intent.getExtras();

        // Try n Catch to handle exceptions
        try{
            // check whether bundle is empty
            if (bundle != null){
                // Create collection of Object of puds type and retrieve from bundle.
                final Object[] pdusObj = (Object[]) bundle.get("pdus");

                // Loop through the collection of retrieved objects from bundle
                for (int i = 0; i < pdusObj.length; i++) {
                    // Create Object of SmsMessage class and create object from the puds object, convert to byte array.
                    SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) pdusObj[i]);

                    // Retrieve Phone Number from the Message Object
                    String phoneNumber = currentMessage.getDisplayOriginatingAddress();

                    // Read message from message object.
                    String message = currentMessage.getDisplayMessageBody();

                    // Toast Retrieved message from SmsMessage object.
                    Toast.makeText(context, "Received From : " + phoneNumber +  " SMS Message  :" + message, Toast.LENGTH_SHORT).show();
                }
            }
        }
        catch (Exception ex){
            // Toast on exception
            Toast.makeText(context, "An Exception Occurred While Receiving SMS", Toast.LENGTH_SHORT).show();
            ex.printStackTrace();
        }
    }
}
