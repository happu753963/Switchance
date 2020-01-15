package com.example.switchance_start.register;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.switchance_start.HomePage;
import com.example.switchance_start.MainActivity;
import com.example.switchance_start.R;
import com.example.switchance_start.Singleton;

public class LogInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        ImageButton btn_back = (ImageButton) findViewById(R.id.btn_back);

        btn_back.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.setClass(LogInActivity.this, MainActivity.class);
                startActivity(intent);
                LogInActivity.this.finish();
            }
        });

        Button btn_logIn = (Button) findViewById(R.id.btn_logIn); //跳到主頁
        btn_logIn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {


                //內存登入時輸入資料
                EditText mail = findViewById(R.id.edtxt_account);
                EditText password = findViewById(R.id.edtxt_password);
                SharedPreferences preferences_login = getSharedPreferences("Log_in", MODE_PRIVATE);     //呼叫getSharedPreferences()方法，產生一個檔名為temp_storge.xml的設定儲存檔，並只供本專案(app)可讀取，物件名稱為pref。
                preferences_login.edit()
                        .putString("ACCOUNT", mail.getText().toString())
                        .putString("PASSWORD", password.getText().toString())
                        .commit();

                SharedPreferences preferences_register = getSharedPreferences("Register", MODE_PRIVATE);
                preferences_register.edit()
                        .putString("CHECK", "1") //檢查碼(確認是從登入進入APP)
                        .commit();

                Singleton.getInstance().setAccount(mail.getText().toString());


                Intent intent = new Intent();
                intent.setClass(LogInActivity.this, HomePage.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);

                startActivity(intent);

                //還缺關閉之前所有的Activity
            }
        });
    }
}

