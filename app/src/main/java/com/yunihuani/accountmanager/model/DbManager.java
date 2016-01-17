package com.yunihuani.accountmanager.model;


import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.yunihuani.accountmanager.system.Account;
import com.yunihuani.accountmanager.system.Group;

import java.util.ArrayList;

/**
 * Created by Seunghwan on 2015-12-26.
 */
public class DbManager {
	private final static String TAG = "DbManager";

	public DbGroupTable GroupTable;
	public DbAccountTable AccountTable;
	public ArrayList<DbTable> DbTables = new ArrayList<DbTable>();

	private Context mContext;
	private DatabaseHelper mDbHelper;
	private SQLiteDatabase mDb;

	private static DbManager mInstance = null;
	public static DbManager getInstance(Context context) {
		if(mInstance == null) {
			mInstance = new DbManager(context);
		}
		else {
			mInstance.setContext(context);
		}
		return mInstance;
	}
	public DbManager(Context context) {
		mContext = context;
	}
	public void setContext(Context context) {
		mContext = context;
	}
	public DbManager open() throws SQLException {
		mDbHelper = new DatabaseHelper(mContext);//DatabaseHelper.getInstance(mContext);
		mDb = mDbHelper.getWritableDatabase();

		return this;
	}
	public void close() {
		mDbHelper.close();
	}

	private class DatabaseHelper extends SQLiteOpenHelper {
		private static final String DATABASE_NAME = "db_account";
		private static final int DATABASE_VERSION = 1;

		public DatabaseHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onConfigure(SQLiteDatabase db) {
			GroupTable = new DbGroupTable(db);
			AccountTable = new DbAccountTable(db);
			DbTables.add(GroupTable);
			DbTables.add(AccountTable);
		}
		@Override
		public void onCreate(SQLiteDatabase db) {
			for(int i = 0; i < DbTables.size(); i++) {
				DbTables.get(i).createTable();
			}
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			Log.d(TAG, "[Database Upgrade]" + oldVersion + " -> " + newVersion);
			for(int i = 0; i < DbTables.size(); i++) {
				DbTables.get(i).dropTable();
			}
			onCreate(db);
		}
	}

}
