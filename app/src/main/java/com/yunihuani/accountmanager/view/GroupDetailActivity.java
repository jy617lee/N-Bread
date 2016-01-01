package com.yunihuani.accountmanager.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.yunihuani.accountmanager.R;
import com.yunihuani.accountmanager.model.DbManager;
import com.yunihuani.accountmanager.system.Account;
import com.yunihuani.accountmanager.system.Group;

public class GroupDetailActivity extends AppCompatActivity {

    private EditText text_date, text_name, text_amount;
    private DbManager mDbManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_groupdetail);

        mDbManager = DbManager.getInstance(this);

        initUI();
    }

    private void initUI() {
        text_date = (EditText) findViewById(R.id.group_event_date);
        text_name = (EditText) findViewById(R.id.group_event_name);
        text_amount = (EditText) findViewById(R.id.group_event_amount);

        Button btn_add = (Button) findViewById(R.id.group_btn_add);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDbManager.GroupTable.create(new Group(-1, text_name.getText().toString(), text_date.getText().toString(), new Account()));
                finish();
            }
        });
    }
}
