package com.insta.taskmanager9000;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

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
		setContentView(R.layout.activity_splash);
		
		this.handler.sendEmptyMessageDelayed(STOP, WAITTIME);
	}

}
