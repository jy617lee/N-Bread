package com.yunihuani.accountmanager.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.yunihuani.accountmanager.R;
import com.yunihuani.accountmanager.model.DbManager;
import com.yunihuani.accountmanager.system.Account;

import java.util.ArrayList;

public class SettingActivity extends AppCompatActivity {

    private DbManager mDbManager;
    private EditText bankName, bankAccount;
    private ArrayList<Account> accountList;
    private Account accountObject;

    public SettingActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        mDbManager = DbManager.getInstance(SettingActivity.this);
        accountList = (ArrayList<Account>)mDbManager.AccountTable.fetchList();
        initUI();
    }

    private void initUI(){

        //등록된 계좌가 있다
        if(accountList.size() >0) {
                drawAccount();
         }
        //등록된 계좌가 없다
        else {
            TextView accountInfo = (TextView)findViewById(R.id.settings_layout_account);
            accountInfo.setText("등록된 계좌가 없습니다");
        }
        //등록된 절삭값이 있다(등록값 선택)

        //등록된 절삭값이 없다(디폴트값 선택)

        //등록 버튼 누르면 저장하기
        Button btnRegister = (Button) findViewById(R.id.settings_btn_add_account);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnRegisterClick();
            }
        });
    }

    private void drawAccount(){
        String bank, account, name;
        accountList = (ArrayList<Account>)mDbManager.AccountTable.fetchList();
        accountObject = accountList.get(0);
        bank = accountObject.getBank();
        account = accountObject.getAccount();
        name = accountObject.getName();

        //등록된 계좌정보 그려주기
        TextView accountInfo = (TextView)findViewById(R.id.settings_layout_account);
        accountInfo.setText("은행명 : " + bank + "\n 계좌번호 : " + account + "\n 예금주 : " + name);

    }


    private void btnRegisterClick(){
        String bank, account, name;
        Account accountObject;
        bank = ((EditText)findViewById(R.id.settings_text_bank)).getText().toString();
        account = ((EditText)findViewById(R.id.settings_text_account)).getText().toString();
        name = ((EditText)findViewById(R.id.settings_text_name)).getText().toString();
        accountObject = new Account(0, bank, account, name);
        if(accountList.size() >0) {
            mDbManager.AccountTable.update(0, accountObject);
        }else{
            mDbManager.AccountTable.create(accountObject);
        }
        drawAccount();
    }





}
