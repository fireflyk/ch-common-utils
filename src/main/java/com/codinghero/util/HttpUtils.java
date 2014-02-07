package com.codinghero.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.AuthCache;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.util.EntityUtils;

@Deprecated
public final class HttpUtils {
	
	public static String get(String url) {
		HttpClient httpclient = new DefaultHttpClient();
		try {
			HttpGet httpget = new HttpGet(url);
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
	
	public static boolean post(String url, HttpEntity httpEntity) {
		HttpClient httpclient = new DefaultHttpClient();
        try {
            HttpPost httppost = new HttpPost(url);
            httppost.setEntity(httpEntity);
            httpclient.execute(httppost);
            // HttpResponse response = httpclient.execute(httppost);
            // HttpEntity resEntity = response.getEntity();
            // EntityUtils.consume(resEntity);
            return true;
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} finally {
			httpclient.getConnectionManager().shutdown();
		}
	}
	
	public static void getBasicAuth() {
		
	}
	
	public static Map<String, Object> postBasicAuth(String protocol, String hostName,
			int port, String uri, String userName, String password, Map<String, String> parameters) {
		HttpHost targetHost = new HttpHost(hostName, port, protocol);
		DefaultHttpClient httpclient = new DefaultHttpClient();
		
		// Create AuthCache instance
        AuthCache authCache = new BasicAuthCache();
        // Generate BASIC scheme object and add it to the local
        // auth cache
        BasicScheme basicAuth = new BasicScheme();
        authCache.put(targetHost, basicAuth);
        
        // Add AuthCache to the execution context
        BasicHttpContext localcontext = new BasicHttpContext();
        localcontext.setAttribute(ClientContext.AUTH_CACHE, authCache);

        HttpPost httppost = new HttpPost(uri);
        httpclient.getCredentialsProvider().setCredentials(
				new AuthScope(targetHost.getHostName(), targetHost.getPort()),
				new UsernamePasswordCredentials(userName, password));
        
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		Iterator<Entry<String, String>> iter = parameters.entrySet().iterator();
		while (iter.hasNext()) {
			Entry<String, String> e = iter.next();
			nvps.add(new BasicNameValuePair(e.getKey(), e.getValue()));
		}

		Map<String, Object> result = new HashMap<String, Object>();
		try {
	        httppost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
			HttpResponse response = httpclient.execute(targetHost, httppost, localcontext);
			HttpEntity entity = response.getEntity();
			
			result.put("statusCode", response.getStatusLine().getStatusCode());
			result.put("responseContent", IOUtils.toString(entity.getContent()));
			
			EntityUtils.consume(entity);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			httpclient.getConnectionManager().shutdown();
		}
		return result;
	}
	
	public static boolean isRelativeUrl(String url) {
		if (url == null)
			throw new IllegalArgumentException();
		if (url.charAt(0) == '/' || url.charAt(0) == '.')
			return true;
		else
			return false;
	}
}
