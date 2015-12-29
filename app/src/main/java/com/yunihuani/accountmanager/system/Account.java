package com.yunihuani.accountmanager.system;

import android.database.Cursor;

import com.yunihuani.accountmanager.model.DbAccountTable;

import java.util.HashMap;

/**
 * Created by Seunghwan on 2015-12-26.
 */
public class Account {
	private int id;
	private String bank;
	private int bankKey;
	private String account;
	private String name;
	public Account() {
		this(-1, -1, "", "");
	}
	public Account(Cursor cursor) {
		this(cursor.getInt(DbAccountTable.KEY.KEY_IDX),
				cursor.getInt(DbAccountTable.KEY.KEY_BANKKEY),
				cursor.getString(DbAccountTable.KEY.KEY_ACCOUNT),
				cursor.getString(DbAccountTable.KEY.KEY_NAME));
	}
	public Account(int id, int bankKey, String account, String name) {
		this.id = id;
//		this.bank = bank;
		this.bankKey = bankKey;
		setBank(this.bankKey);
		this.account = account;
		this.name = name;
	}


	private static HashMap<Integer, String> bankMap = new HashMap<>();
	private void setBankMap() {
		bankMap.put(1, "국민은행");
		bankMap.put(2, "신한은행");
        /*
        ...
        */
	}
	public static HashMap<Integer, String> getBankMap() {
		return bankMap;
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
	public void setBank(String bank) { this.bank = bank; }
	public void setBank(int _bankKey) {
		if(bankMap.size() < 1) {
			setBankMap();
		}
		this.bank = bankMap.get(_bankKey);
	}
	public int getBankKey() {
		return bankKey;
	}
	public void setBankKey(int bankKey) {
		this.bankKey = bankKey;
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
