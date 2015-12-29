package com.yunihuani.accountmanager.system;

import android.database.Cursor;

import com.yunihuani.accountmanager.model.DbEventTable;

/**
 * Created by Seunghwan on 2015-12-26.
 */
public class Event {
	private int id;
	private Group group;
	private String name;
	private float amount;
	
	public Event() {
		this(-1, new Group(), "", 0);
	}
	public Event(Cursor cursor) {
		this(cursor.getInt(DbEventTable.KEY.KEY_IDX),
				new Group(cursor.getInt(DbEventTable.KEY.KEY_GROUP_IDX),
						cursor.getString(DbEventTable.KEY.KEY_GROUP_NAME),
						new Account(cursor.getInt(DbEventTable.KEY.KEY_GROUP_ACCOUNT_IDX),
								cursor.getInt(DbEventTable.KEY.KEY_GROUP_ACCOUNT_BANKKEY),
								cursor.getString(DbEventTable.KEY.KEY_GROUP_ACCOUNT_ACCOUNT),
								cursor.getString(DbEventTable.KEY.KEY_GROUP_ACCOUNT_NAME)
						)
				),
				cursor.getString(DbEventTable.KEY.KEY_NAME),
				cursor.getFloat(DbEventTable.KEY.KEY_AMOUNT));
	}
	public Event(int id, Group group, String name, float amount) {
		this.id = id;
		this.group = group;
		this.name = name;
		this.amount = amount;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Group getGroup() {
		return group;
	}
	public void setGroup(Group group) {
		this.group = group;
	}
	public void setGroup(int groupId) {
//		this.group = DbManager.getGroup(groupId);
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	@Override
	public boolean equals(Object o) {
		return this.getId() == ((Event)o).getId();
	}
	
	
}
