package com.insta.taskmanager9000;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.insta.taskmanager9000.business.Contributor;
import com.insta.taskmanager9000.business.Priority;
import com.insta.taskmanager9000.business.State;
import com.insta.taskmanager9000.business.Task;
import com.insta.taskmanager9000.tools.Pair;

public class ListActivity extends android.app.ListActivity {
	
	private ArrayList<Contributor> contributors;
	private ArrayList<Task> tasks;
	private ArrayList<String> taskList;
	private ArrayAdapter<String> adapter;
	@SuppressWarnings("unused")
	private ListView list;
	private final int ADD_ID = 8001;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list);
		
		Intent i = getIntent();
		Bundle humble = i.getExtras();
		this.contributors = (ArrayList<Contributor>)((Pair)humble.get("datas")).getFirst();
		this.tasks = (ArrayList<Task>)((Pair)humble.get("datas")).getSecond();
		
		this.list = (ListView) findViewById(android.R.id.list);
		
		taskList = new ArrayList<String>(tasks.size());
		
		for(Task t : this.tasks){
			taskList.add(t.toString());
		}
		
		this.adapter = new ArrayAdapter<String>(this, 
				android.R.layout.simple_list_item_1, taskList);
		setListAdapter(this.adapter);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		final int i = position;
		builder.setMessage(R.string.dial_suppr);
		builder.setPositiveButton(R.string.dial_btn_yes, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				taskList.remove(i);
				adapter.notifyDataSetChanged();
			}
		});
		builder.setNegativeButton(R.string.dial_btn_no, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
			}
		});
		builder.show();
	}
	
	private void addListItem() {
		Intent intent = new Intent(this, AddActivity.class);
		startActivityForResult(intent, ADD_ID);
	}
	
	@SuppressLint("SimpleDateFormat")
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data){
		if(requestCode == ADD_ID){
			if(resultCode == RESULT_OK){
				Bundle humble = data.getExtras();
				String todo = (String) humble.get("todo");
				String priority = (String) humble.get("priority");
				String state = (String) humble.get("state");
				Date today = new Date();
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				String date = dateFormat.format(today);
				
				Contributor contrib = null;
				
				for(Contributor c : this.contributors){
					if(c.getName().equals(MainActivity.DEFAULT_USER)){
						contrib = c;
						break;
					}
				}
				
				Task newTask = new Task(contrib, date, State.valueOf(state), 
						Priority.valueOf(priority), todo);
				
				this.tasks.add(newTask);
				this.taskList.add(newTask.toString());
				adapter.notifyDataSetChanged();
			}
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.list, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		switch(item.getItemId()){
		case R.id.add_item:
			addListItem();
			return true;
		}
		return false;
	}

}
