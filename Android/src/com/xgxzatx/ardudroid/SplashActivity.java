package com.xgxzatx.ardudroid;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import com.xgxzatx.ardudroid.connectivity.ManageConnectivity;

public class SplashActivity extends Activity implements Runnable {

	ManageConnectivity connectivityManager = new ManageConnectivity(this);
	
	private AlertDialog noConnectionDialog;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		new Handler().post(this);
	}
	
	@Override
	public void run() {
		if (connectivityManager.isInternetOn()){
			callMainScreen();
		} else {
			showDialogNoConnection();
		}
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
			run();
		}
	}

	public void showDialogNoConnection() {
		noConnectionDialog = new AlertDialog.Builder(this).create();
		noConnectionDialog.setTitle(getString(R.string.no_connection));
		noConnectionDialog.setMessage(getString(R.string.what_to_do));
		noConnectionDialog.setCanceledOnTouchOutside(false);
		noConnectionDialog.setCancelable(false);
		noConnectionDialog.setButton(DialogInterface.BUTTON_POSITIVE,getString(R.string.configure), btnPositive);
		noConnectionDialog.setButton(DialogInterface.BUTTON_NEUTRAL,getString(R.string.retry), btnNeutral);
		noConnectionDialog.setButton(DialogInterface.BUTTON_NEGATIVE,getString(R.string.quit), btnNegative); 
		noConnectionDialog.show();
	}
	
	private DialogInterface.OnClickListener btnPositive = new DialogInterface.OnClickListener() {
		public void onClick(DialogInterface dialog, int which) {
			dialog.dismiss();
			callSystemConfig();
		}
	};
	
	private DialogInterface.OnClickListener btnNegative = new DialogInterface.OnClickListener() {
		public void onClick(DialogInterface dialog, int which) {
			finish();
		}
	};
	
	private DialogInterface.OnClickListener btnNeutral = new DialogInterface.OnClickListener() {
		public void onClick(DialogInterface dialog, int which) {
			dialog.dismiss();
			run();
		}
	};
	
}
