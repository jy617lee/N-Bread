package com.yunihuani.accountmanager.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.yunihuani.accountmanager.system.Account;

/**
 * Created by Seunghwan on 2015-12-26.
 */
public class DbAccountTable<E> extends DbTable {

    public DbAccountTable(SQLiteDatabase db) {
        super(db);

        TABLE_NAME = "'account'";
        CREATE_TABLE = "create table " + TABLE_NAME +
                "(" + KEY.IDX + " integer primary key default 0, " +
//                KEY.BANK + " text not null, " +
                KEY.BANK + " text not null, " +
                KEY.ACCOUNT + " text not null, " +
                KEY.NAME + " text not null)";
    }

    @Override
    protected Object constructor(Cursor cursor) {
        return new Account(cursor);
    }

    public static interface KEY {
        public final String IDX = "'idx'";
        public final String BANK = "'bank'";
        public final String ACCOUNT = "'account'";
        public final String NAME = "'name'";

        public final int KEY_IDX = 0;
        public final int KEY_BANK = 1;
        public final int KEY_ACCOUNT = 2;
        public final int KEY_NAME = 3;

        public final String[] list = {IDX, BANK, ACCOUNT, NAME};
        public final String[] joinList = {IDX, BANK, ACCOUNT, NAME};
    }

    public String[] getList() { return parseColumns(KEY.list); }
    public String[] getJoinList() { return parseColumns(KEY.joinList); }
    @Override
    public long create(Object item) {
        Account account = (Account)item;
        return create(account.getBank(), account.getAccount(), account.getName());
    }
    public long create(String bank, String account, String name) {
        ContentValues v = new ContentValues();
        v.put(KEY.BANK, bank);
        v.put(KEY.ACCOUNT, account);
        v.put(KEY.NAME, name);
        return mDb.insert(TABLE_NAME, null, v);
    }
    public boolean update(long idx, Account item) {
        ContentValues v = new ContentValues();
        v.put(KEY.BANK, item.getBank());
        v.put(KEY.ACCOUNT, item.getAccount());
        v.put(KEY.NAME, item.getName());
        v.put("'idx'", idx);
        return mDb.update(TABLE_NAME, v, "'idx'" +"="+ idx, null) > 0;
    }
}
