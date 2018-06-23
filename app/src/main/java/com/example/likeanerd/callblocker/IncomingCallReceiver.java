package com.example.likeanerd.callblocker;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.widget.Toast;
import java.lang.reflect.Method;
import com.android.internal.telephony.ITelephony;

public class IncomingCallReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {


        DatabaseHelper tdatabasehelper;

        ITelephony telephonyService;
        try {
            String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
            String number = intent.getExtras().getString(TelephonyManager.EXTRA_INCOMING_NUMBER);

            tdatabasehelper = new DatabaseHelper(context);
                String flag =  tdatabasehelper.checkNumber(number);



            if(state.equalsIgnoreCase(TelephonyManager.EXTRA_STATE_RINGING)){

                TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
                try {
                    Method m = tm.getClass().getDeclaredMethod("getITelephony");

                    m.setAccessible(true);
                    telephonyService = (ITelephony) m.invoke(tm);
                    Toast.makeText(context,number ,Toast.LENGTH_LONG ).show();

                    if (((number != null)&&(flag=="True"))||(number.substring(0,3).equals("140")||number.substring(0,6).equals("+91140"))) {
                        telephonyService.endCall();
                        Toast.makeText(context, "Ending the call from: " + number, Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }


            }



        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
