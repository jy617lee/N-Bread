package com.yunihuani.accountmanager.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.yunihuani.accountmanager.util.LOG;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Seunghwan on 2015-12-26.
 */
public abstract class DbTable<E> {
    protected String TABLE_NAME;
    protected String CREATE_TABLE;

    protected SQLiteDatabase mDb;
    public DbTable(SQLiteDatabase db) {
        mDb = db;
    }

    public static interface KEY {
        public final String IDX = "'idx'";
        public final int KEY_IDX = 0;
        public String[] list = null;
        public String[] joinList = null;
    };

    protected abstract E constructor(Cursor cursor);

    public final void createTable() {
        if(TABLE_NAME == null) {
            LOG.d("DbManager", "TABLE_NAME is undefined");
            return;
        }
        if(CREATE_TABLE != null) {
            mDb.execSQL(CREATE_TABLE);
        }
        else {
            LOG.d("DbManager", "Query CREATE_TABLE is undefined");
        }
    }
    public final void dropTable() {
        if(TABLE_NAME != null) {
            mDb.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        }
        else {
            LOG.d("DbManager", "TABLE_NAME is undefined");
        }
    }

    public String[] parseColumns(String[] _list) {
        String[] list = _list.clone();
        for(int i = 0; i < list.length; i++) {
            list[i] = list[i].substring(1, list[i].length()-1);
        }
        return list;
    }
    public abstract String[] getList();
    public abstract String[] getJoinList();

    // Create Item
    public abstract long create(E item);

    // Read Item
    public final Cursor fetch() {
        return fetch(false);
    }
    public final Cursor fetch(boolean joined) {
        String cols[] = (joined) ? getJoinList() : getList();
        Cursor mCursor = mDb.query(TABLE_NAME, cols, null, null, null, null, "idx");
        if(mCursor != null)
            mCursor.moveToFirst();
        return mCursor;
    }
    // Read Item list
    public final List<E> fetchList() {
        return fetchList(false);
    }
    public final List<E> fetchList(boolean joined) {
        ArrayList<E> list = new ArrayList<E>();
        Cursor cursor = fetch(joined);
            if(cursor.isAfterLast())
            return list;
        do {
            list.add(constructor(cursor));
        } while(cursor.moveToNext());

        return list;
    }
    public E fetchByIndex(int idx) {
        return fetchByIndex(idx, false);
    }
    public E fetchByIndex(int idx, boolean joined) {
        E item = null;
        String cols[] = (joined) ? getJoinList() : getList();
        Cursor mCursor = mDb.query(TABLE_NAME, cols, "idx" + "=" + idx, null, null, null, "idx");
        if (mCursor != null) {
            if (mCursor.getCount() > 0) {
                mCursor.moveToFirst();
                item = constructor(mCursor);
            }
        }
        return item;
    }

    // Update Item
    public final boolean update(long idx, String key, String value) {
        ContentValues v = new ContentValues();
        v.put(key, value);
        return mDb.update(TABLE_NAME, v, "idx" +"="+ idx, null) > 0;
    }

    public boolean update(long idx, E item) {
        ContentValues v = new ContentValues();
        v.put("'idx'", idx);
        return mDb.update(TABLE_NAME, v, "idx" +"="+ idx, null) > 0;
    }

    // Delete Item
    public final boolean delete(long idx) {
        return mDb.delete(TABLE_NAME, "idx" +"="+ idx, null) > 0;
    }
}
