package com.xgxzatx.ardudroid;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo.State;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends Activity implements Runnable {

	public Intent intentSystemConfigScreen = new Intent(android.provider.Settings.ACTION_SETTINGS);
	
	private AlertDialog alertDialog;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash_screen);
		new Handler().post(this);
	}

	public final boolean isInternetOn() {
		ConnectivityManager connec =  (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
		if ( connec.getNetworkInfo(0).getState() == State.CONNECTED ||
				connec.getNetworkInfo(1).getState() == State.CONNECTED ) {
			return true;
		} else if ( connec.getNetworkInfo(0).getState() == State.DISCONNECTED ||  connec.getNetworkInfo(1).getState() == State.DISCONNECTED  ) {
			return false;
		}
		return false;
	}
	
	public void verify() {
		if(isInternetOn()) {
			callMainScreen();
		} else {
			showDialogNoConnection();
		}
	}
	
	@Override
	public void run() {
		verify();
	}
	
	public void callMainScreen() {
		Intent intentMainScreen = new Intent(this, ArduDroid.class);
		startActivity(intentMainScreen);
		finish();
	}
	
	public void callSystemConfig() {
		Intent intentSystemConfigScreen = new Intent(android.provider.Settings.ACTION_SETTINGS);
		startActivityForResult(intentSystemConfigScreen, 0);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(requestCode == 0 && resultCode == RESULT_CANCELED) {
			verify();
		}
	}

	public void showDialogNoConnection() {
		alertDialog = new AlertDialog.Builder(this).create();
		alertDialog.setTitle(getString(R.string.no_connection));
		alertDialog.setMessage(getString(R.string.what_to_do));
		alertDialog.setCanceledOnTouchOutside(false);
		alertDialog.setButton(DialogInterface.BUTTON_POSITIVE,getString(R.string.configure), btnPositive);
		alertDialog.setButton(DialogInterface.BUTTON_NEUTRAL,getString(R.string.retry), btnNeutral);
		alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE,getString(R.string.quit), btnNegative); 
		alertDialog.show();
	}
	
	private DialogInterface.OnClickListener btnPositive = new DialogInterface.OnClickListener() {
		public void onClick(DialogInterface dialog, int which) {
			dialog.dismiss();
			callSystemConfig();
		}
	};
	
	private DialogInterface.OnClickListener btnNegative = new DialogInterface.OnClickListener() {
		public void onClick(DialogInterface dialog, int which) {
			//dialog.dismiss();
			finish();
		}
	};
	
	private DialogInterface.OnClickListener btnNeutral = new DialogInterface.OnClickListener() {
		public void onClick(DialogInterface dialog, int which) {
			dialog.dismiss();
			verify();
		}
	};
	
}
