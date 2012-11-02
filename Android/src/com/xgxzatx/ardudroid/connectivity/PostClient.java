package com.xgxzatx.ardudroid.connectivity;

import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import android.util.Log;

public class PostClient {
	
	private DefaultHttpClient client;
	private HttpPost post;
	private HttpResponse response;
	
	public PostClient(String url) {
		client = new DefaultHttpClient();
		post = new HttpPost(url);		
	}

	public String post() {
		
		String result = null;
		
	    try {
	        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
	        nameValuePairs.add(new BasicNameValuePair("l1", "1"));
	        nameValuePairs.add(new BasicNameValuePair("l2", "0"));
	        post.setEntity(new UrlEncodedFormEntity(nameValuePairs));
	        
	        response = client.execute(post);
	        result = EntityUtils.toString(response.getEntity());

	    } catch (Exception e) {
	    	String message = "Não foi possível conectar com o servidor";
			Log.e("PostClient", message, e);
			throw new RuntimeException(message, e);
	    }
	    return result;
	} 
	
}
