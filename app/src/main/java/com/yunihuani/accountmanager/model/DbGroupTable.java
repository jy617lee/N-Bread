package com.yunihuani.accountmanager.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.yunihuani.accountmanager.system.Group;

/**
 * Created by Seunghwan on 2015-12-26.
 */
public class DbGroupTable<E> extends DbTable {

    public DbGroupTable(SQLiteDatabase db) {
        super(db);

        TABLE_NAME = "'group'";
        CREATE_TABLE = "create table " + TABLE_NAME +
                " ( " + KEY.IDX + " integer primary key autoincrement, " +
                KEY.NAME + " text not null, " +
                KEY.DATE + " date not null, " +
                KEY.ACCOUNTID + " text not null )";
    }

    @Override
    protected Object constructor(Cursor cursor) {
        return new Group(cursor);
    }

    public static interface KEY {
        public final String IDX = "'idx'";
        public final String NAME = "'name'";
        public final String DATE = "'date'";
        public final String ACCOUNTID = "'account_id'";

        public final String ACCOUNT_BANK = "'account_bank'";
        public final String ACCOUNT_BANKKEY = "'account_bankkey'";
        public final String ACCOUNT_ACCOUNT = "'account_account'";
        public final String ACCOUNT_NAME = "'account_name'";


        public final int KEY_IDX = 0;
        public final int KEY_NAME = 1;
        public final int KEY_DATE = 2;
        public final int KEY_ACCOUNTID = 3;

        public final int KEY_ACCOUNT_BANKKEY = 4;
        public final int KEY_ACCOUNT_ACCOUNT = 5;
        public final int KEY_ACCOUNT_NAME = 6;

        public final String[] list = {IDX, NAME, DATE, ACCOUNTID};
        public final String[] joinList = {IDX, NAME, DATE, ACCOUNTID, ACCOUNT_BANK, ACCOUNT_BANKKEY, ACCOUNT_ACCOUNT, ACCOUNT_NAME};
    }

    public String[] getList() { return parseColumns(KEY.list); }
    public String[] getJoinList() { return parseColumns(KEY.joinList); }
    @Override
    public long create(Object item) {
        Group group = (Group)item;
        return create(group.getName(), group.getDate().toString(), group.getAccount().getId());
    }
    public long create(String name, String date, int account_id) {
        ContentValues v = new ContentValues();
        v.put(KEY.NAME, name);
        v.put(KEY.DATE, date);
        v.put(KEY.ACCOUNTID, account_id);
        return mDb.insert(TABLE_NAME, null, v);
    }
}
