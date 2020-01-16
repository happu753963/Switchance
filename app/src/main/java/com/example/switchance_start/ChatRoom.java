package com.example.switchance_start;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.switchance_start.Adapter.ChatRoomAdapter;
import com.example.switchance_start.Datas.ChatData;
import com.example.switchance_start.model.Constant;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;

public class ChatRoom extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView recyclerViewInterestedSkill;
    EditText editText;
    ChatRoomAdapter chatRoomAdapter;
    ChatData chatData;
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room);

        Log.v("aaaaatag", "yes1");
        chatData = (ChatData) getIntent().getSerializableExtra("userInfo");
        Log.v("aaaaatag", chatData.getIcon()+"");
        initView();
        setToolbar();
        setAdapter();
        getChatMessenge();
    }
    public void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        recyclerViewInterestedSkill = (RecyclerView) findViewById(R.id.recyclerView_interestedSkill);
        editText = (EditText) findViewById(R.id.editText);
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
        chatRoomAdapter = new ChatRoomAdapter(this,recyclerViewInterestedSkill);
        recyclerViewInterestedSkill.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewInterestedSkill.setAdapter(chatRoomAdapter);

//        chatRoomAdapter.addItem(new ChatData("123", "Willy1", "Willy"));
//        chatRoomAdapter.addItem(new ChatData("123", "Willy1", "Willy"));
//        chatRoomAdapter.addItem(new ChatData("123", "Willy", "Willy1"));
//        chatRoomAdapter.addItem(new ChatData("123", "Willy", "Willy1"));
    }

    public void onMessageSend(View view) {
        String timesamp = Calendar.getInstance().getTimeInMillis()+"";
        String message = editText.getText().toString();

        ChatData tempChatData = new ChatData(message, chatData.getId(), Singleton.getInstance().account);


        myRef.child("chatroom").child(timesamp).setValue(tempChatData);
        myRef.child("user_info").child(Singleton.getInstance().getAccount()).child("friends").child(chatData.getId()).child("messageId").child(chatRoomAdapter.getItemCount()+"").setValue(timesamp);
        myRef.child("user_info").child(chatData.getId()).child("friends").child(Singleton.getInstance().getAccount()).child("messageId").child(chatRoomAdapter.getItemCount()+"").setValue(timesamp);
        editText.setText("");
    }

    public void getChatMessenge() {
        Log.v("aaaaatag", "yes2");
        final ArrayList<String> chatId = new ArrayList<String>();
        myRef.child("user_info").child(Singleton.getInstance().getAccount()).child("friends").child(chatData.getId()).child("messageId").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                chatRoomAdapter.clearItem();
                chatId.clear();
                Log.v("aaaaatag", dataSnapshot.toString());
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    chatId.add(snapshot.getValue().toString());
                    Log.v("aaaaatag", snapshot.getValue().toString()+"yesh");
                }
                Log.v("aaaaatag", chatId.size()+"size");
                for (int i = 0; i < chatId.size(); i++) {
                    final String id = chatId.get(i);
                    myRef.child("chatroom").child(chatId.get(i)).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            Log.v("aaaaatag", id);
                            ChatData tmpChatData = dataSnapshot.getValue(ChatData.class);
                            if (!id.equals(Singleton.getInstance().getAccount())) {
                                tmpChatData.setIcon(chatData.getIcon());
                            }
                            chatRoomAdapter.addItem(tmpChatData);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                        }
                    });
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });
    }
}
