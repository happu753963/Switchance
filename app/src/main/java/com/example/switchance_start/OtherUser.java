package com.example.switchance_start;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.switchance_start.Adapter.OtherUserInterestedExperienceAdapter;
import com.example.switchance_start.Adapter.OtherUserInterestedOwnedAdapter;
import com.example.switchance_start.Adapter.PersonalInterestedExperienceAdapter;
import com.example.switchance_start.Adapter.PersonalInterestedOwnedAdapter;
import com.example.switchance_start.model.UserInfo;
import com.example.switchance_start.register.InterestedExperience;
import com.example.switchance_start.register.InterestedItem;
import com.example.switchance_start.register.InterestedSkill;
import com.example.switchance_start.register.OwnedExperience;
import com.example.switchance_start.register.OwnedItem;
import com.example.switchance_start.register.OwnedSkill;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class OtherUser extends AppCompatActivity {

    Toolbar toolbar;
    UserInfo userInfo;
    Button follow;
    boolean followFlag = false;

    TextView txt_ansTime;
    TextView txt_ansplace;
    TextView txt_information;

    TextView txt_name;
    ImageView img_icon;
    RecyclerView interestRecyclerView;
    RecyclerView ownedRecyclerView;
    OtherUserInterestedExperienceAdapter otherUserInterestedExperienceAdapter;
    OtherUserInterestedOwnedAdapter otherUserInterestedOwnedAdapter;
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_user);

        getUserData();
        initView();
        getFollow();
        setToolbar();
        setAdapter();
        setFirebase();
    }

    public void getUserData() {
        userInfo = (UserInfo) getIntent().getExtras().getSerializable("userInfo");
    }

    public void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        follow = (Button) findViewById(R.id.follow);
        txt_ansTime = (TextView) findViewById(R.id.txt_ansTime);
        txt_ansplace = (TextView) findViewById(R.id.txt_ansPlace);
        txt_information = (TextView) findViewById(R.id.txt_information);
        img_icon = (ImageView) findViewById(R.id.img_icon);
        txt_name = (TextView) findViewById(R.id.txt_name);


        interestRecyclerView = (RecyclerView) findViewById(R.id.interestRecyclerView);
        ownedRecyclerView = (RecyclerView) findViewById(R.id.ownedRecyclerView);

        otherUserInterestedExperienceAdapter = new OtherUserInterestedExperienceAdapter(this);
        otherUserInterestedOwnedAdapter = new OtherUserInterestedOwnedAdapter(this);
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

    public void setAdapter() {
        interestRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        interestRecyclerView.setAdapter(otherUserInterestedExperienceAdapter);

        ownedRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        ownedRecyclerView.setAdapter(otherUserInterestedOwnedAdapter);

        //interested&owned資料加進recyclerView
        for (int i = 0; i < userInfo.getInterestedSkill().size(); i++) {
            InterestedSkill interestedSkill = userInfo.getInterestedSkill().get(i);
            otherUserInterestedExperienceAdapter.addItem(new InterestedExperience(interestedSkill.getInterestedSkill(), "interestedSkill"));
        }

        for (int i = 0; i < userInfo.getInterestedExperience().size(); i++) {
            InterestedExperience interestedExperience = userInfo.getInterestedExperience().get(i);
            otherUserInterestedExperienceAdapter.addItem(new InterestedExperience(interestedExperience.getInterestedExperience(), "interestedExperience"));
        }

        for (int i = 0; i < userInfo.getInterestedItem().size(); i++) {
            InterestedItem interestedItem = userInfo.getInterestedItem().get(i);
            otherUserInterestedExperienceAdapter.addItem(new InterestedExperience(interestedItem.getInterestedItem(), "interestedItem"));
        }

        for (int i = 0; i < userInfo.getOwnedSkill().size(); i++) {
            OwnedSkill ownedSkill = userInfo.getOwnedSkill().get(i);
            otherUserInterestedOwnedAdapter.addItem(new OwnedExperience(ownedSkill.getOwnedSkill(), "ownedSkill"));
        }

        for (int i = 0; i < userInfo.getOwnedExperience().size(); i++) {
            OwnedExperience ownedExperience = userInfo.getOwnedExperience().get(i);
            otherUserInterestedOwnedAdapter.addItem(new OwnedExperience(ownedExperience.getOwnedExperience(), "ownedExperience"));
        }

        for (int i = 0; i < userInfo.getOwnedItem().size(); i++) {
            OwnedItem ownedItem = userInfo.getOwnedItem().get(i);
            otherUserInterestedOwnedAdapter.addItem(new OwnedExperience(ownedItem.getOwnedItem(), "ownedItem"));
        }


//        otherUserInterestedExperienceAdapter.addItem(new InterestedExperience("測試1","ownedSkill"));
//        otherUserInterestedExperienceAdapter.addItem(new InterestedExperience("測試1","colorSkillMiddle"));
//        otherUserInterestedExperienceAdapter.addItem(new InterestedExperience("測試1","ownedSkill"));
//        otherUserInterestedExperienceAdapter.addItem(new InterestedExperience("測試1","ownedSkill"));
//        otherUserInterestedExperienceAdapter.addItem(new InterestedExperience("測試1","ownedSkill"));
//        otherUserInterestedExperienceAdapter.addItem(new InterestedExperience("測試1","ownedSkill"));
//        otherUserInterestedExperienceAdapter.addItem(new InterestedExperience("測試1","ownedSkill"));
//        otherUserInterestedExperienceAdapter.addItem(new InterestedExperience("測試1","colorSkillDark"));
//
//        otherUserInterestedOwnedAdapter.addItem(new OwnedExperience("測試1","colorSkillDark"));
//        otherUserInterestedOwnedAdapter.addItem(new OwnedExperience("測試1","colorSkillDark"));
    }

    public void setFirebase() {
        myRef.child("user_info").child(userInfo.getAccount()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                txt_name.setText(String.valueOf(dataSnapshot.child("account").getValue()));
                txt_ansplace.setText(String.valueOf(dataSnapshot.child("place").getValue()));
//                Log.v("place",String.valueOf(dataSnapshot.child("place").getValue()));
//                Log.v("introduction",String.valueOf(dataSnapshot.child("introduction").getValue()));
                txt_ansTime.setText(String.valueOf(dataSnapshot.child("time").getValue()));

                txt_information.setText(String.valueOf(dataSnapshot.child("introduction").getValue()));

                String icon_num = String.valueOf(dataSnapshot.child("icon").getValue());
                Log.v("iconicon", icon_num + "");
                switch (icon_num) {
                    case "2131165282":  //雞
                        img_icon.setImageResource(R.drawable.chicken);
                        break;
                    case "2131165280":  //變色龍
                        img_icon.setImageResource(R.drawable.chameleon);
                        break;
                    case "2131165325":  //獅子
                        img_icon.setImageResource(R.drawable.lion);
                        break;
                    case "2131165315":  //刺蝟
                        img_icon.setImageResource(R.drawable.hedgehog);
                        break;
                    case "2131165314":  //鵝
                        img_icon.setImageResource(R.drawable.goose);
                        break;
                    case "2131165311":  //狐狸
                        img_icon.setImageResource(R.drawable.fox);
                        break;

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void onClickFollow(View view) {
//        Toast.makeText(this,follow.getText().toString(),Toast.LENGTH_LONG).show();
        String followText = follow.getText().toString();
        if (followText.equals("追蹤")) {
            myRef.child("user_info").child(Singleton.getInstance().getAccount()).child("friends").child(userInfo.getAccount()).child("account").setValue(userInfo.getAccount());
            myRef.child("user_info").child(userInfo.getAccount()).child("friends").child(Singleton.getInstance().getAccount()).child("account").setValue(Singleton.getInstance().getAccount());
//            follow.setText("追蹤中");
        } /*else if (followText.equals("已要求")) {
//            myRef.child(userInfo.getAccount()).child("inviteList").child(Singleton.getInstance().getAccount()).removeValue();
        }*/
    }

    public void getFollow() {
        Log.v("tesssssss", "wttttttt");
        myRef.child("user_info").child(Singleton.getInstance().getAccount()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild("friends") && dataSnapshot.child("friends").hasChild(userInfo.getAccount())) {
                    for (DataSnapshot snapshot : dataSnapshot.child("friends").getChildren()) {
                        if (snapshot.child("account").getValue().toString().equals(userInfo.getAccount())) {
                            follow.setText("追蹤中");
                        }
                    }
                } /*else if (dataSnapshot.hasChild("inviteList") && dataSnapshot.child("inviteList").hasChild(userInfo.getAccount())) {
                    boolean flag = Boolean.parseBoolean(dataSnapshot.child("inviteList").child(userInfo.getAccount()).getValue().toString());
                    if (flag) {
                        follow.setText("已要求");
                    }
                }*/
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}

