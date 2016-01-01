package com.yunihuani.accountmanager.view;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.yunihuani.accountmanager.R;
import com.yunihuani.accountmanager.model.DbManager;
import com.yunihuani.accountmanager.util.BackPressCloseHandler;

public class MainActivity extends AppCompatActivity {
    private BackPressCloseHandler backPressCloseHandler;

    public static DbManager mDbManager;

    public static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDbManager = DbManager.getInstance(this);
        mDbManager.open();

        backPressCloseHandler = new BackPressCloseHandler(this);

        if (savedInstanceState == null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            SlidingTabsBasicFragment fragment = new SlidingTabsBasicFragment();
            transaction.replace(R.id.sample_content_fragment, fragment);
            transaction.commit();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mDbManager != null) {
            mDbManager.close();
        }
    }
    @Override
    public void onBackPressed() {
        backPressCloseHandler.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.menu_add:
                startActivity(new Intent(MainActivity.this, GroupDetailActivity.class));
                return true;
            case R.id.menu_setting:
                startActivity(new Intent(MainActivity.this, SettingActivity.class));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
