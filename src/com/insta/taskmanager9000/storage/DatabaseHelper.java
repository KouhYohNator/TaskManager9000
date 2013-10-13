package com.insta.taskmanager9000.storage;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

class DatabaseHelper extends SQLiteOpenHelper {

	private static final String DATABASE_NAME = "tasksManager";
	private static final String DATABASE_TABLE = "contributors";
	private static final String DATABASE_TABLE2 = "tasks";
	private static final int DATABASE_VERSION = 4;
	private static final String TABLE_CREATE = "create table " + DATABASE_TABLE
			+ " (_id integer primary key autoincrement, "
			+ "name varchar(50) not null, mail text not null);";
	private static final String TABLE_CREATE2 = "create table "
			+ DATABASE_TABLE2 + " (_id integer primary key autoincrement, "
			+ "author_name varchar(50) not null, tdate date not null,"
			+ "contributor_name varchar(50), state varchar(10) not null,"
			+ "priority varchar(10) not null, todo text not null);";
	
	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(TABLE_CREATE);
		db.execSQL(TABLE_CREATE2);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w("Content provider database",
				"Upgrading database from version " + oldVersion + " to "
						+ newVersion + ", which will destroy all old data");
	}
	
}
