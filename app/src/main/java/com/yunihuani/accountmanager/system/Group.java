package com.yunihuani.accountmanager.system;

import android.database.Cursor;
import com.yunihuani.accountmanager.model.DbGroupTable;
import java.util.GregorianCalendar;

/**
 * Created by Seunghwan on 2015-12-26.
 */
public class Group implements Cloneable{
    private int id;
    private String name;
    private Account account;
	private String dateStr;
	private GregorianCalendar date = new GregorianCalendar();

    public Group() {
        this(-1, "", new Account());
    }
	public Group(Cursor cursor) {
		this(cursor.getInt(DbGroupTable.KEY.KEY_IDX),
				cursor.getString(DbGroupTable.KEY.KEY_NAME),
				cursor.getString(DbGroupTable.KEY.KEY_DATE),
				new Account());
//				new Account(cursor.getInt(DbGroupTable.KEY.KEY_ACCOUNTID),
//						cursor.getInt(DbGroupTable.KEY.KEY_ACCOUNT_BANKKEY),
//						cursor.getString(DbGroupTable.KEY.KEY_ACCOUNT_ACCOUNT),
//						cursor.getString(DbGroupTable.KEY.KEY_ACCOUNT_NAME)));
	}
	public Group(int _id, String _name, Account _account) {
		this(_id, _name, "", _account);
	}
	public Group(int _id, String _name, String _date, Account _account) {
        this.id = _id;
        this.name = _name;
		setDate(_date);
        this.account = _account;
    }
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) { this.name = name; }
	public GregorianCalendar getDate() { return this.date; }
	public void setDate(GregorianCalendar date) {this.date = date; }
	public void setDate(String dateStr) {
		String[] dateArr = dateStr.split("-");
		if(dateArr.length == 3) {
			this.date = new GregorianCalendar(Integer.parseInt(dateArr[0]),
					Integer.parseInt(dateArr[1]), Integer.parseInt(dateArr[2]));
		}
		else {
			this.date = new GregorianCalendar();
		}
	}
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	public void setAccount(int accountId) {
//		this.account = DbManager.getAccount(accountId);
	}
	@Override
	public boolean equals(Object o) {
		return this.getId() == ((Group)o).getId();
	}
	@Override
	public Group clone() {
		Group group = new Group();
		group.setId(this.getId());
		group.setName(this.getName());
		group.setDate(this.getDate());
		group.setAccount(this.getAccount());

		return group;
	}
}
