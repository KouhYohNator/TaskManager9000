package com.insta.taskmanager9000;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.Window;

public class SplashActivity extends Activity {
	
	private final int STOP = 8042;
	private final long WAITTIME = 3000;
	
	@SuppressLint("HandlerLeak")
	private Handler handler = new Handler(){
		@Override
		public void handleMessage(Message msg){
			if(msg.what == STOP){
				Intent i = new Intent(SplashActivity.this, MainActivity.class);
				startActivity(i);
			}
			super.handleMessage(msg);
			SplashActivity.this.finish();
		}
	};;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_splash);
		
		this.handler.sendEmptyMessageDelayed(STOP, WAITTIME);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.splash, menu);
		return true;
	}

}
