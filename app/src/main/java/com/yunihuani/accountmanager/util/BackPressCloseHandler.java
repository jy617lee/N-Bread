package com.yunihuani.accountmanager.util;

import android.app.Activity;
import android.widget.Toast;

import com.yunihuani.accountmanager.R;

/**
 * Created by Seunghwan on 2015-12-26.
 */
public class BackPressCloseHandler {
    private long backKeyPressedTime = 0;
    private Toast toast;

    private Activity activity;

    public BackPressCloseHandler(Activity context) {
        this.activity = context;
    }

    public void onBackPressed() {
        if (System.currentTimeMillis() > backKeyPressedTime + 2000) {
            backKeyPressedTime = System.currentTimeMillis();
            showGuide();
            return;
        }
        if (System.currentTimeMillis() <= backKeyPressedTime + 2000) {
            activity.finish();
            toast.cancel();
        }
    }

    public void showGuide() {
        toast = Toast.makeText(activity, activity.getString(R.string.main_backkey_msg), Toast.LENGTH_SHORT);
        toast.show();
    }
}
