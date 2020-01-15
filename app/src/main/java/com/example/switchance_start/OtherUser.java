package com.example.switchance_start;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.switchance_start.model.UserInfo;

public class OtherUser extends AppCompatActivity {

    Toolbar toolbar;
    UserInfo userInfo;
    Button follow;
    boolean followFlag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_user);

        getUserData();
        initView();
        setToolbar();
        setFollow();

//        ImageView img_sendtxt = (ImageView) findViewById(R.id.img_sendtxt); //開啟私訊
//        img_sendtxt.setOnClickListener(new Button.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Intent intent = new Intent();
//                intent.setClass(OtherUser.this, ChatRoom.class);
//                startActivity(intent);
//
//            }
//        });
    }

    public void getUserData() {
        userInfo = (UserInfo) getIntent().getExtras().getSerializable("userInfo");
        Log.v("testId", userInfo.getId());
    }

    public void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        follow = (Button) findViewById(R.id.follow);
    }

    public void setToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //        Toolbar返回鍵功能
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OtherUser.this.finish();
            }
        });
        toolbar.setTitle(userInfo.getAccount());
    }

    public void setFollow() {
        follow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                follow.setText("已要求");

            }
        });
    }
}
