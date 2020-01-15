package com.example.switchance_start;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.switchance_start.Adapter.ChatRoomAdapter;
import com.example.switchance_start.Datas.ChatData;

public class ChatRoom extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView recyclerViewInterestedSkill;
    ChatRoomAdapter chatRoomAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room);

        initView();
        setToolbar();
        setAdapter();
        getUserData();
    }
    public void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        recyclerViewInterestedSkill = (RecyclerView) findViewById(R.id.recyclerView_interestedSkill);
    }

    public void setToolbar() {
        //      Toolbar的相關設定
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
    }

    public void setAdapter() {
        chatRoomAdapter = new ChatRoomAdapter(this);
        recyclerViewInterestedSkill.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewInterestedSkill.setAdapter(chatRoomAdapter);


    }

    public void getUserData() {
//        chatRoomAdapter.addItem(new ChatData());
    }
}
