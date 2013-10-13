package com.insta.taskmanager9000;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	private EditText login, password, servAddr, servPort;
	private Button connectButton;
	private ProgressBar connectProgress;
	
	private final int STOP = 8042;
	private final long WAITTIME = 5000;
	
	@SuppressLint("HandlerLeak")
	private Handler handler = new Handler(){
		@Override
		public void handleMessage(Message msg){
			if(msg.what == STOP){
				Toast grilled = Toast.makeText(MainActivity.this, 
											   "Connecté", Toast.LENGTH_SHORT);
				grilled.show();
				MainActivity.this.connectProgress.setVisibility(View.INVISIBLE);
				MainActivity.this.connectButton.setEnabled(true);
			}
			super.handleMessage(msg);
		}
	};;
	
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
				
				String text = "Connexion à " +
							MainActivity.this.login.getText().toString() +
							":" +
							MainActivity.this.password.getText().toString() +
							"@" +
							MainActivity.this.servAddr.getText().toString() +
							":" +
							MainActivity.this.servPort.getText().toString();
				Log.i("MainActivity", text);
				MainActivity.this.handler.sendEmptyMessageDelayed(STOP, 
																  WAITTIME);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
