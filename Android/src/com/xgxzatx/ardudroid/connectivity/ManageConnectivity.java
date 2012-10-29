package com.xgxzatx.ardudroid.connectivity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class ManageConnectivity {
	
	Context mContext;
	
	public ManageConnectivity (Context mContext) {
		this.mContext = mContext;
	}
	
	public final boolean isInternetOn() {
		ConnectivityManager cm = (ConnectivityManager)mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
		if (activeNetwork != null){
			boolean isConnected = activeNetwork.isConnected();
			boolean isWiFi = activeNetwork.getType() == ConnectivityManager.TYPE_WIFI;
			if ( isConnected & isWiFi) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

}
