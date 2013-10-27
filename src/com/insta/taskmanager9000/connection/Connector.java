package com.insta.taskmanager9000.connection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;

import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.util.Log;


public class Connector extends AsyncTask<String, Void, String>{

	@Override
	protected String doInBackground(String... params) {
		HttpGet get = new HttpGet(params[0]);
		HttpClient client = AndroidHttpClient.newInstance("Android");
		try {
			HttpResponse result = client.execute(get);
			String content = "";
			if (result != null){
				Log.d("Connector", "Post result = " + result.toString());
				HttpEntity entity = result.getEntity();
				try {
					Log.d("Connector", "Entity = " + result.getEntity().toString());
					content = inputStreamToString(entity.getContent());
					Log.d("Connector", "Content = " + content);
				} 
				catch (IllegalStateException e) { e.printStackTrace(); }
				catch (IOException e) {	e.printStackTrace(); }
			}
			else{
				Log.d("Connector", "Post result = null");
				content = "Echec de la connexion";
			}
			return content;
		} catch (IOException e1) {
			e1.printStackTrace();
			return null;
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

}
