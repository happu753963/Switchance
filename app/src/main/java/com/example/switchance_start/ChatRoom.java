package com.example.switchance_start;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.ImageView;
import android.widget.TextView;

public class ChatRoom extends AppCompatActivity {

    private EditText et_Name;
    private TextView tv_Show1;
    private TextView tv_Show2;
    private Button btn_Show;
    private TextView tv_Resonse1;
    private TextView tv_Resonse2;
    private  ImageView icon_Response1;
    private  ImageView icon_Response2;
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room);

        //      Toolbar的相關設定
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        Toolbar返回鍵功能
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChatRoom.this.finish();
            }
        });


        //        聊天輸入輸出
        et_Name = (EditText) findViewById(R.id.editText);
        tv_Show1 = (TextView) findViewById(R.id.textView2);
        tv_Show2 = (TextView) findViewById(R.id.textView6);
        btn_Show = (Button) findViewById(R.id.button);
        tv_Resonse1 = (TextView) findViewById(R.id.textView5);
        tv_Resonse2 = (TextView) findViewById(R.id.textView7);
        icon_Response1 = (ImageView)findViewById(R.id.imageView);
        icon_Response2 = (ImageView)findViewById(R.id.imageView4);

        btn_Show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count +=1;
                if(count ==1) {
//                   輸入訊息設定
                    tv_Show1.setText((et_Name.getText().toString()));
                    tv_Show1.setBackgroundResource(R.drawable.textview_round_border);
                    et_Name.setText("");

                    //      假聊天室之對方回傳訊息(sleep)

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            //延迟两秒
                            try {
                                Thread.sleep( 2000 );
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    tv_Resonse1.setText("請問你有攝影機嗎?");
                                    tv_Resonse1.setBackgroundResource(R.drawable.textview_round_border);
                                    icon_Response1.setVisibility(View.VISIBLE);
                                }
                            });

                        }
                    }).start();
                }

                if(count ==2) {

//                   輸入訊息設定
                    tv_Show2.setText((et_Name.getText().toString()));
                    tv_Show2.setBackgroundResource(R.drawable.textview_round_border);
                    et_Name.setText("");

                    //      假聊天室之對方回傳訊息(sleep)

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            //延迟两秒
                            try {
                                Thread.sleep( 2000 );
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    tv_Resonse2.setText("OK");
                                    tv_Resonse2.setBackgroundResource(R.drawable.textview_round_border);
                                    icon_Response2.setVisibility(View.VISIBLE);
                                }
                            });
                        }
                    }).start();
                }

            }
        });
















    }
}
