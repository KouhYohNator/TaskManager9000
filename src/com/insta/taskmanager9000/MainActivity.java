package com.insta.taskmanager9000;

import java.util.concurrent.ExecutionException;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.insta.taskmanager9000.connection.Connector;

public class MainActivity extends Activity {
	
	private static final String URL = "http://192.168.16.2/android/tasks.xml";
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
				
				/*String text = "Connexion à " +
							MainActivity.this.login.getText().toString() +
							":" +
							MainActivity.this.password.getText().toString() +
							"@" +
							MainActivity.this.servAddr.getText().toString() +
							":" +
							MainActivity.this.servPort.getText().toString();
				Log.i("MainActivity", text);*/
				Log.i("MainActivity", URL);
				
				MainActivity.this.connection();
				
			}
		});
	}

	public void connection(){
		Log.i("MainActivity", "Connexion - début");
		Connector connector = (Connector) new Connector();
		try {
			String mess = connector.execute(URL).get();
			Toast.makeText(this, mess, Toast.LENGTH_LONG).show();
		} catch (InterruptedException e) { e.printStackTrace();
		} catch (ExecutionException e) { e.printStackTrace(); }
		Log.i("MainActivity", "Connexion - fin");
		connectProgress.setVisibility(View.INVISIBLE);
		connectButton.setEnabled(true);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
