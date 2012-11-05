package com.xgxzatx.ardudroid.task;

import com.xgxzatx.ardudroid.connectivity.GetClient;
import android.os.AsyncTask;

public class GetMonitorData extends AsyncTask<String, Object, String> {

	private final static String ENDPOINT = "http://192.168.1.252";

	@Override
	protected String doInBackground(String... params) {
		String result = null;
		try {
			result = new GetClient(ENDPOINT).get();
		} catch (Exception e) {
			return e.getMessage();
		}
		return result;
	}

}
