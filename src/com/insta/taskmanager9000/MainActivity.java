package com.insta.taskmanager9000;

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
	
	private static final String URL = "http://82.66.42.109/android/tasks.xml";
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
		Toast grilled = null;
		Log.i("MainActivity", "Connexion - début");
		Connector connector = (Connector) new Connector();
		Log.i("MainActivity", "Connexion - fin");
		if(connector.execute(URL) != null){
			Log.i("MainActivity", "Connexion - résultat : " + connector.getContent());
			grilled = Toast.makeText(this,
								     connector.getContent(),
								     Toast.LENGTH_LONG);
		}
		else{
			grilled = Toast.makeText(this,
					  "Echec de la connexion",
				      Toast.LENGTH_LONG);
		}
		this.connectProgress.setVisibility(View.INVISIBLE);
		this.connectButton.setEnabled(true);
		grilled.show();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
