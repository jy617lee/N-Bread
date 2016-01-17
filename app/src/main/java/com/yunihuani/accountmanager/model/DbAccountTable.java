package com.yunihuani.accountmanager.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.yunihuani.accountmanager.system.Account;
import com.yunihuani.accountmanager.system.Event;

import java.util.HashMap;

/**
 * Created by Seunghwan on 2015-12-26.
 */
public class DbAccountTable<E> extends DbTable {

    public DbAccountTable(SQLiteDatabase db) {
        super(db);

        TABLE_NAME = "account";
        CREATE_TABLE = "create table " + TABLE_NAME +
                "(" + KEY.IDX + " integer primary key autoincrement, " +
//                KEY.BANK + " text not null, " +
                KEY.BANKKEY + " integer not null, " +
                KEY.ACCOUNT + " text not null, " +
                KEY.NAME + " text not null);";
    }

    @Override
    protected Object constructor(Cursor cursor) {
        return new Account(cursor);
    }

    public static interface KEY {
        public final String IDX = "'idx'";
        public final String BANKKEY = "'bankkey'";
        public final String ACCOUNT = "'account'";
        public final String NAME = "'name'";

        public final int KEY_IDX = 0;
        public final int KEY_BANKKEY = 1;
        public final int KEY_ACCOUNT = 2;
        public final int KEY_NAME = 3;

        public final String[] list = {IDX, BANKKEY, ACCOUNT, NAME};
        public final String[] joinList = {IDX, BANKKEY, ACCOUNT, NAME};
    }

    public String[] getList() { return parseColumns(KEY.list); }
    public String[] getJoinList() { return parseColumns(KEY.joinList); }
    @Override
    public long create(Object item) {
        Account account = (Account)item;
        return create(account.getBankKey(), account.getAccount(), account.getName());
    }
    public long create(int bankkey, String account, String name) {
        ContentValues v = new ContentValues();
        v.put(KEY.BANKKEY, bankkey);
        v.put(KEY.ACCOUNT, account);
        v.put(KEY.NAME, name);
        return mDb.insert(TABLE_NAME, null, v);
    }
}
