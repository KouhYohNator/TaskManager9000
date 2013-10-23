package com.insta.taskmanager9000.connection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.AsyncTask;
import android.util.Log;


public class Connector extends AsyncTask<String, Void, HttpResponse>{

	private String content;

	@Override
	protected HttpResponse doInBackground(String... params) {
		HttpGet get = new HttpGet(params[0]);
		HttpClient client = new DefaultHttpClient();
		try {
			return client.execute(get);
		} catch (IOException e1) {
			e1.printStackTrace();
			return null;
		}
	}

	@Override
	protected void onPostExecute(HttpResponse result) {
		if (result != null){
			Log.d("Connector", "Post result = " + result.toString());
			HttpEntity entity = result.getEntity();
			try {
				this.content = inputStreamToString(entity.getContent());
			} 
			catch (IllegalStateException e) { e.printStackTrace(); }
			catch (IOException e) {	e.printStackTrace(); }
		}
		else{
			Log.d("Connector", "Post result = null");
		}
	}


	// repris du TP HTTPmethodsOXinit
	public String inputStreamToString(InputStream in) throws IOException {
		InputStreamReader streamReader = new InputStreamReader(in);
		BufferedReader buffer = new BufferedReader(streamReader);
		StringBuilder sb = new StringBuilder();
		String line = "";
		while (null != (line = buffer.readLine())) {
			sb.append(line);
		}
		return sb.toString();
	}

	public String getContent(){
		return this.content;
	}

}
