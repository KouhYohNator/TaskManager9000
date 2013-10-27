package com.insta.taskmanager9000;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.insta.taskmanager9000.business.Contributor;
import com.insta.taskmanager9000.business.Task;
import com.insta.taskmanager9000.tools.Pair;

public class ListActivity extends android.app.ListActivity {
	
	private ArrayList<Contributor> contributors;
	private ArrayList<Task> tasks;
	private ArrayAdapter<String> adapter;
	private ListView list;

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
		
		String[] taskTab = new String[tasks.size()];
		
		int j = 0;
		for(Task t : this.tasks){
			taskTab[j] = t.toString();
			j++;
		}
		
		this.adapter = new ArrayAdapter<String>(this, 
				android.R.layout.simple_list_item_1, taskTab);
		setListAdapter(this.adapter);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.list, menu);
		return true;
	}

}
