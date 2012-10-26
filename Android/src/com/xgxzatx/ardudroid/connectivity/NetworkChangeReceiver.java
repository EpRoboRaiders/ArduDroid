package com.xgxzatx.ardudroid.connectivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class NetworkChangeReceiver extends BroadcastReceiver {

	  @Override
	  public void onReceive(final Context context, final Intent intent) {
		  //intent.getStringExtra();
		  Log.w(ConnectivityReceiver.class.getSimpleName(), "action: "
	                + intent.getAction());
	}
}