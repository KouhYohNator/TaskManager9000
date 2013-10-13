package com.insta.taskmanager9000.storage;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class ConnectDB {

	private static ConnectDB instance = null;
	private SQLiteDatabase database;
	private DatabaseHelper helper;
	
	private ConnectDB(Context cont){
		this.helper = new DatabaseHelper(cont);
		this.database = helper.getWritableDatabase();
	}
	
	public static ConnectDB getInstance(Context cont){
		if(ConnectDB.instance == null){
			ConnectDB.instance = new ConnectDB(cont);
		}
		return ConnectDB.instance;
	}

	public SQLiteDatabase getDatabase() {
		return database;
	}

	public DatabaseHelper getHelper() {
		return helper;
	}
	
}
