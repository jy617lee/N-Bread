package com.yunihuani.accountmanager.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.yunihuani.accountmanager.system.Event;
import com.yunihuani.accountmanager.system.Group;

/**
 * Created by Seunghwan on 2015-12-26.
 */
public class DbEventTable<E> extends DbTable {

    public DbEventTable(SQLiteDatabase db) {
        super(db);

        TABLE_NAME = "event";
        CREATE_TABLE = "create table " + TABLE_NAME +
                "(" + KEY.IDX + " integer primary key autoincrement, " +
                KEY.GROUP_IDX + " integer not null, " +
                KEY.NAME + " text not null, " +
                KEY.AMOUNT + " integer default 0);";
    }

    @Override
    protected Object constructor(Cursor cursor) {
        return new Event(cursor);
    }

    public static interface KEY {
        public final String IDX = "idx";
        public final String GROUP_IDX = "group_id";
        public final String NAME= "name";
        public final String AMOUNT = "amount";

        public final String GROUP_NAME = "gruop_name";
        public final String GROUP_ACCOUNT_IDX = "group_account_id";
        public final String GROUP_ACCOUNT_BANK = "group_account_bank";
        public final String GROUP_ACCOUNT_ACCOUNT = "group_account_account";
        public final String GROUP_ACCOUNT_NAME = "group_account_name";
        public final String GROUP_ACCOUNT_BANKKEY = "group_account_bankkey";

        public final int KEY_IDX = 0;
        public final int KEY_GROUP_IDX = 1;
        public final int KEY_NAME = 2;
        public final int KEY_AMOUNT = 3;

        public final int KEY_GROUP_NAME = 4;
        public final int KEY_GROUP_ACCOUNT_IDX = 5;
        public final int KEY_GROUP_ACCOUNT_BANK = 6;
        public final int KEY_GROUP_ACCOUNT_ACCOUNT = 7;
        public final int KEY_GROUP_ACCOUNT_NAME = 8;
        public final int KEY_GROUP_ACCOUNT_BANKKEY = 9;

        public final String[] list = {IDX, GROUP_IDX, NAME, AMOUNT};
        public final String[] joinList = {IDX, GROUP_IDX, NAME, AMOUNT, GROUP_NAME,
                                        GROUP_ACCOUNT_IDX, GROUP_ACCOUNT_BANK, GROUP_ACCOUNT_BANK, GROUP_ACCOUNT_ACCOUNT,
                                        GROUP_ACCOUNT_NAME, GROUP_ACCOUNT_BANKKEY};
    }

    public String[] getList() { return KEY.list; }
    public String[] getJoinList() { return KEY.joinList; }
    @Override
    public long create(Object item) {
        Event event = (Event)item;
        return create(event.getId(), event.getGroup().getId(), event.getName(), event.getAmount());
    }
    public long create(int id, int group_id, String name, float amount) {
        ContentValues v = new ContentValues();
        v.put(KEY.IDX, id);
        v.put(KEY.GROUP_IDX, group_id);
        v.put(KEY.NAME, name);
        v.put(KEY.AMOUNT, amount);
        return mDb.insert(TABLE_NAME, null, v);
    }
}
