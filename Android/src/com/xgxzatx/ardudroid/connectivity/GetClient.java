package com.xgxzatx.ardudroid.connectivity;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import android.util.Log;

public class GetClient {

	private DefaultHttpClient client;
	private HttpGet get;
	private HttpResponse response;

	public GetClient(String url) {
		client = new DefaultHttpClient();
		get = new HttpGet(url);
	}

	public String get() {

		String result = null;

		try {
			get.setHeader("Accept", "application/json");
			get.setHeader("Content-type", "application/json");

			response = client.execute(get);
			if(response.getStatusLine().getStatusCode() != 200) {
				throw new RuntimeException("StatusCode not OK");
			}
			result = EntityUtils.toString(response.getEntity());

		} catch (Exception e) {
			String message = "Não foi possível conectar com o servidor";
			Log.e("GetClient", message, e);
			throw new RuntimeException(message, e);
		}

		return result;
	}

}
