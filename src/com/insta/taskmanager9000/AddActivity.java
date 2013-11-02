package com.insta.taskmanager9000;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class AddActivity extends Activity {
	
	private TextView todo;
	private RadioGroup rg_priority;
	private RadioGroup rg_state;
	private Button btn_add;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add);
		
		this.todo = (TextView) findViewById(R.id.text_todo);
		this.rg_priority = (RadioGroup) findViewById(R.id.radioGroup_priority);
		this.rg_state = (RadioGroup) findViewById(R.id.radioGroup_state);
		this.btn_add = (Button) findViewById(R.id.button_add);
		this.btn_add.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(AddActivity.this, ListActivity.class);
				Bundle humble = new Bundle();
				humble.putString("todo", todo.getText().toString());
				
				int checkedID = rg_priority.getCheckedRadioButtonId();
				Button checkedButton = (RadioButton) findViewById(checkedID);
				String priority = (String) checkedButton.getTag();
				humble.putString("priority", priority);
				
				checkedID = rg_state.getCheckedRadioButtonId();
				checkedButton = (RadioButton) findViewById(checkedID);
				String state = (String) checkedButton.getTag();
				humble.putString("state", state);
				
				intent.putExtras(humble);
				AddActivity.this.setResult(RESULT_OK, intent);
				AddActivity.this.finish();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add, menu);
		return true;
	}

}
