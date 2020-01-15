package com.example.switchance_start;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.example.switchance_start.model.Constant;
import com.example.switchance_start.model.UserInfo;
import com.example.switchance_start.register.InterestedExperience;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HomePage extends AppCompatActivity {

    Toolbar toolbar;
    TabLayout tabLayout;
    TabItem tabLogo;
    TabItem tabEnvelope;
    TabItem tabPerson;
    ViewPager viewPager;
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();

    PageAdapter pageAdapter;
    private int[] icons = {R.drawable.home, R.drawable.chat, R.drawable.user};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);

        pageAdapter = new PageAdapter(getSupportFragmentManager(), icons.length);

        viewPager.setAdapter(pageAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setupWithViewPager(viewPager);

        for (int i = 0; i < icons.length; i++) {
            tabLayout.getTabAt(i).setCustomView(getTabView(i, icons[i]));
        }

        getFibaseData();
        Log.v("testting", Singleton.getInstance().getAccount());
    }



    public void getFibaseData() {
        Log.v("testting", Singleton.getInstance().getAccount());
        myRef.child(Constant.CHILD_REF_USERINFO).child(Singleton.getInstance().getAccount()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.v("testting", dataSnapshot.toString());
                Singleton.getInstance().setAccount(dataSnapshot.child("account").getValue().toString());
                Singleton.getInstance().setBirthday(dataSnapshot.child("birthday").getValue().toString());
                Singleton.getInstance().setDepartment(dataSnapshot.child("department").getValue().toString());
                Singleton.getInstance().setGender(dataSnapshot.child("gender").getValue().toString());
                Singleton.getInstance().setIcon(Integer.parseInt(dataSnapshot.child("icon").getValue().toString()));
                Singleton.getInstance().setId(dataSnapshot.child("id").getValue().toString());
//                Singleton.getInstance().setInterestedExperience(dataSnapshot.getValue(InterestedExperience.class));
//                Singleton.getInstance().setInterestedExperience((InterestedExperience)dataSnapshot.child("interestedExperiences").getValue());

                Singleton.getInstance().setIntroduction(dataSnapshot.child("introduction").getValue().toString());
                Singleton.getInstance().setMail(dataSnapshot.child("mail").getValue().toString());
                Singleton.getInstance().setName(dataSnapshot.child("name").getValue().toString());


                Singleton.getInstance().setPassword(dataSnapshot.child("password").getValue().toString());
                Singleton.getInstance().setPlace(dataSnapshot.child("place").getValue().toString());
                Singleton.getInstance().setSchool(dataSnapshot.child("school").getValue().toString());
                Singleton.getInstance().setTime(dataSnapshot.child("time").getValue().toString());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public View getTabView(int position, int icon) {
        View view = LayoutInflater.from(this).inflate(R.layout.layout_icon, null);
        ImageView imgIcon = (ImageView) view.findViewById(R.id.imageView);
        imgIcon.setImageResource(icon);
        return view;
    }

    public void onFragmentInteraction(Uri uri) {
    }
}
