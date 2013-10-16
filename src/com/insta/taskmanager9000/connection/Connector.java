package com.insta.taskmanager9000.connection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;


public class Connector {

	private static final String URL = "http://82.66.42.109:1337/android/tasks.xml";
	private static Connector instance = null;
	
	private HttpClient client;
	private HttpGet get;
	private String content;
	
	private Connector(){
		this.client = new DefaultHttpClient();
		this.get = new HttpGet(URL);
		this.content = "";
	}
	
	public boolean connect(){
		HttpResponse response = null;
		try {
			response = client.execute(this.get);
			HttpEntity entity = response.getEntity();
			this.content = inputStreamToString(entity.getContent());
		} catch (ClientProtocolException e1) {
			e1.printStackTrace();
			return false;
		} catch (IOException e1) {
			e1.printStackTrace();
			return false;
		}
		return true;
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

	public static Connector getInstance(){
		if(Connector.instance == null){
			Connector.instance = new Connector();
			return Connector.instance;
		}
		else{
			return Connector.instance;
		}
	}
	
	public String getContent(){
		return this.content;
	}

}
