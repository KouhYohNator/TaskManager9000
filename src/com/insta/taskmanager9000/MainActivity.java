package com.insta.taskmanager9000;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.insta.taskmanager9000.business.Contributor;
import com.insta.taskmanager9000.business.Task;
import com.insta.taskmanager9000.connection.Connector;
import com.insta.taskmanager9000.tools.Pair;

public class MainActivity extends Activity {
	
	private static final String DEFAULT_URL = "http://192.168.16.2/android/tasks.xml";
	private static final int LIST_ID = 8001;
	@SuppressWarnings("unused")
	private EditText login, password, servAddr, servPort;
	private Button connectButton;
	private ProgressBar connectProgress;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		this.login    = (EditText) findViewById(R.id.edit_login);
		this.password = (EditText) findViewById(R.id.edit_pass);
		this.servAddr = (EditText) findViewById(R.id.edit_addr);
		this.servPort = (EditText) findViewById(R.id.edit_port);
		
		this.connectButton = (Button) findViewById(R.id.btn_connect);
		this.connectProgress = (ProgressBar) findViewById(R.id.prgrs_connect);
		this.connectButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				MainActivity.this.connectButton.setEnabled(false);
				MainActivity.this.connectProgress.setVisibility(View.VISIBLE);
				
				Log.i("MainActivity", DEFAULT_URL);
				
				MainActivity.this.connection();
				
			}
		});
	}

	public void connection(){
		Log.i("MainActivity", "Connexion - début");
		Connector connector = (Connector) new Connector();
		Pair<ArrayList<Contributor>, ArrayList<Task>> parsedLists = null;
		
		// Connexion et récupération des données
		try {
			parsedLists = connector.execute(DEFAULT_URL).get();
		} catch (InterruptedException e) { e.printStackTrace();
		} catch (ExecutionException e) { e.printStackTrace(); }
		
		Log.i("MainActivity", "Connexion - fin");
		connectProgress.setVisibility(View.INVISIBLE);
		connectButton.setEnabled(true);
		
		if(parsedLists != null){
			
			Intent intent = new Intent(this, ListActivity.class);
			Bundle humble = new Bundle();
			humble.putSerializable("datas", parsedLists);
			intent.putExtras(humble);
			
			startActivityForResult(intent, LIST_ID);
		}
		else {
			Toast grilled = Toast.makeText(this, "Echec de connexion", Toast.LENGTH_SHORT);
			grilled.show();
		}
	}
	
	//TODO
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
