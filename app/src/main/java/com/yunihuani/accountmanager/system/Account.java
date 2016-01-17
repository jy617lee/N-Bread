package com.yunihuani.accountmanager.system;

import android.database.Cursor;

import com.yunihuani.accountmanager.model.DbAccountTable;

/**
 * Created by Seunghwan on 2015-12-26.
 */
public class Account {
	private int id;
	private String bank;
	private String account;
	private String name;
	public Account() {
		this(-1, "", "", "");
	}
	public Account(Cursor cursor) {
		this(cursor.getInt(DbAccountTable.KEY.KEY_IDX),
				cursor.getString(DbAccountTable.KEY.KEY_BANK),
				cursor.getString(DbAccountTable.KEY.KEY_ACCOUNT),
				cursor.getString(DbAccountTable.KEY.KEY_NAME));
	}
	public Account(int id, String bank, String account, String name) {
		this.id = id;
		this.bank = bank;
		this.account = account;
		this.name = name;
	}




	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBank() {
		return bank;
	}

	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
