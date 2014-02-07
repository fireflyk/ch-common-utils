package com.codinghero.util.http;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

public class HttpGet {
	public String execute(String url) {
		HttpClient httpclient = new DefaultHttpClient();
		try {
			org.apache.http.client.methods.HttpGet httpget = new org.apache.http.client.methods.HttpGet(url);
			ResponseHandler<String> responseHandler = new BasicResponseHandler();
			return httpclient.execute(httpget, responseHandler);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			return "";
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		} finally {
			httpclient.getConnectionManager().shutdown();
		}
	}
}
