package com.example.switchance_start.register;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.switchance_start.HomePage;
import com.example.switchance_start.MainActivity;
import com.example.switchance_start.R;

public class LogInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        ImageButton btn_back= (ImageButton)findViewById(R.id.btn_back);

        btn_back.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.setClass(LogInActivity.this, MainActivity.class);
                startActivity(intent);
                LogInActivity.this.finish();
            }
        });

        Button btn_logIn= (Button)findViewById(R.id.btn_logIn); //跳到主頁
        btn_logIn.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.setClass(LogInActivity.this, HomePage.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);

                startActivity(intent);

                //還缺關閉之前所有的Activity
            }
        });
    }
}

