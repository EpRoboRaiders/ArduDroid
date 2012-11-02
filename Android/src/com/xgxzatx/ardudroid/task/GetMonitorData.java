package com.xgxzatx.ardudroid.task;

import com.xgxzatx.ardudroid.connectivity.GetClient;
import android.os.AsyncTask;

public class GetMonitorData extends AsyncTask<String, Object, String>{

	private final static String ENDPOINT = "";

	@Override
	protected String doInBackground(String... params) {
		try {
			getDataFromMonitor();
		} catch(Exception e) {
			return e.getMessage();
		}			
		return null;
	}

	protected String getDataFromMonitor() {
		return new GetClient(ENDPOINT).get();		
	}

}
