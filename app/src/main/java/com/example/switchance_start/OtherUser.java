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

import com.example.switchance_start.Adapter.OtherUserInterestedExperienceAdapter;
import com.example.switchance_start.Adapter.OtherUserInterestedOwnedAdapter;
import com.example.switchance_start.Adapter.PersonalInterestedExperienceAdapter;
import com.example.switchance_start.Adapter.PersonalInterestedOwnedAdapter;
import com.example.switchance_start.model.UserInfo;
import com.example.switchance_start.register.InterestedExperience;
import com.example.switchance_start.register.OwnedExperience;
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
    TextView txt_place;
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
        setToolbar();
        setFollow();
        setAdapter();
        setFirebase();


    }

    public void getUserData() {
        userInfo = (UserInfo) getIntent().getExtras().getSerializable("userInfo");
        Log.v("testId", userInfo.getId());

    }

    public void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        follow = (Button) findViewById(R.id.follow);
        txt_ansTime = (TextView) findViewById(R.id.txt_ansTime);
        txt_place = (TextView) findViewById(R.id.txt_place);
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

    public void setFollow() {
        follow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                follow.setText("已要求");

            }
        });
    }

    public void setAdapter() {
        interestRecyclerView.setLayoutManager(new GridLayoutManager(this,3));
        interestRecyclerView.setAdapter(otherUserInterestedExperienceAdapter);

        ownedRecyclerView.setLayoutManager(new GridLayoutManager(this,3));
        ownedRecyclerView.setAdapter(otherUserInterestedOwnedAdapter);

        otherUserInterestedExperienceAdapter.addItem(new InterestedExperience("測試1","ownedSkill"));
        otherUserInterestedExperienceAdapter.addItem(new InterestedExperience("測試1","colorSkillMiddle"));
        otherUserInterestedExperienceAdapter.addItem(new InterestedExperience("測試1","ownedSkill"));
        otherUserInterestedExperienceAdapter.addItem(new InterestedExperience("測試1","ownedSkill"));
        otherUserInterestedExperienceAdapter.addItem(new InterestedExperience("測試1","ownedSkill"));
        otherUserInterestedExperienceAdapter.addItem(new InterestedExperience("測試1","ownedSkill"));
        otherUserInterestedExperienceAdapter.addItem(new InterestedExperience("測試1","ownedSkill"));
        otherUserInterestedExperienceAdapter.addItem(new InterestedExperience("測試1","colorSkillDark"));


        otherUserInterestedOwnedAdapter.addItem(new OwnedExperience("測試1","colorSkillDark"));
        otherUserInterestedOwnedAdapter.addItem(new OwnedExperience("測試1","colorSkillDark"));
    }

    public void setFirebase() {
        myRef.child("user_info").child(userInfo.getAccount()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                txt_name.setText(String.valueOf(dataSnapshot.child("account").getValue()));
                String icon_num = String.valueOf(dataSnapshot.child("icon").getValue());
                switch (icon_num){
                    case "2131165282":  //雞
                        img_icon.setImageResource(R.drawable.chicken);
                        break;
                    case "2131165280":  //變色龍
                        img_icon.setImageResource(R.drawable.chameleon);
                        break;
                    case "2131165324":  //獅子
                        img_icon.setImageResource(R.drawable.lion);
                        break;
                    case "2131165315":  //刺蝟
                        img_icon.setImageResource(R.drawable.hedgehog);
                        break;
                    case "2131165314":  //鵝
                        img_icon.setImageResource(R.drawable.goose);
                        break;
                    case "0":  //狐狸
                        img_icon.setImageResource(R.drawable.fox);
                        break;

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }




}

