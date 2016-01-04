package com.yunihuani.accountmanager.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Seunghwan on 2016-01-04.
 */
public class PreferenceManager {
    private Context mContext;
    public static final String PREFFILENAME = "PrefFile";

    /********************
     * Preference KEY *
     ********************/
    private static final String MONEY_UNIT = "money_unit";

    private static PreferenceManager mInstance;
    public static PreferenceManager getInstance(Context context) {
        if(mInstance == null) {
            mInstance = new PreferenceManager();
        }
        mInstance.setContext(context);
        return mInstance;
    }
    public void setContext(Context context) {
        mContext = context;
    }

    private void set(String key, String value) {
        SharedPreferences pref = mContext.getSharedPreferences(PREFFILENAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = pref.edit();
        edit.putString(key, value);
        edit.commit();
    }
    private void set(String key, boolean value) {
        SharedPreferences pref = mContext.getSharedPreferences(PREFFILENAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = pref.edit();
        edit.putBoolean(key, value);
        edit.commit();
    }

    private String get(String key) {
        SharedPreferences preferences = mContext.getSharedPreferences(PREFFILENAME, Context.MODE_PRIVATE);
        return preferences.getString(key, "");
    }
    private boolean get(String key, boolean defValue) {
        SharedPreferences preferences = mContext.getSharedPreferences(PREFFILENAME, Context.MODE_PRIVATE);
        return preferences.getBoolean(key, defValue);
    }

    public void setMoneyUnit(String value) {
        set(MONEY_UNIT, value);
    }
    public String getMoneyUnit() {
        return get(MONEY_UNIT);
    }


}
