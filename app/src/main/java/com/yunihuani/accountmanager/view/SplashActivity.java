package com.yunihuani.accountmanager.view;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.yunihuani.accountmanager.R;

public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Thread logoTread = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(1000);
                } catch(InterruptedException e) {
                    // do nothing
                } finally {
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    finish();
                }
            }
        };
        logoTread.start();
    }
}
