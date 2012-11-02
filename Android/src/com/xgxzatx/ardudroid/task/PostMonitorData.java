package com.xgxzatx.ardudroid.task;

import com.xgxzatx.ardudroid.connectivity.PostClient;
import android.os.AsyncTask;

public class PostMonitorData extends AsyncTask<String, Object, String>{

	private final static String ENDPOINT = "";
	
	@Override
	protected String doInBackground(String... params) {
		try {
			postDataToMonitor();
		} catch(Exception e) {
			return e.getMessage();
		}			
		return null;
	}
	
	protected String postDataToMonitor() {
		return new PostClient(ENDPOINT).post();		
	}
	
}
