package com.insta.taskmanager9000.connection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.xml.sax.InputSource;

import com.insta.taskmanager9000.business.Contributor;
import com.insta.taskmanager9000.business.Task;
import com.insta.taskmanager9000.tools.Pair;
import com.insta.taskmanager9000.tools.Parser;

import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.util.Log;


public class Connector extends AsyncTask< String, Void, Pair<ArrayList<Contributor>, ArrayList<Task>> >{

	@Override
	protected Pair<ArrayList<Contributor>, ArrayList<Task>> doInBackground(String... params) {
		HttpGet get = new HttpGet(params[0]);
		HttpClient client = AndroidHttpClient.newInstance("Android");
		
		try {
			// Connexion
			HttpResponse result = client.execute(get);
			if (result != null){ // Réponse du serveur
				String content = "";
				HttpEntity entity = result.getEntity();
				
				try {
					// Récupération et parsing du contenu
					content = inputStreamToString(entity.getContent());
					Log.d("Connector", "content : " + content);
					Parser myParser = new Parser();
					return myParser.parseTasks(new InputSource(
												new StringReader(content)));
				} 
				catch (IllegalStateException e) { e.printStackTrace(); }
				catch (IOException e) {	e.printStackTrace(); }
			}
			else{
				Log.d("Connector", "Post result = null");
			}
			return null;
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
