package com.example.switchance_start;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.viewpager.widget.ViewPager;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.widget.Toolbar;

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
    final FirebaseDatabase database=FirebaseDatabase.getInstance();
    DatabaseReference myRef=database.getReference();

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
    }

    @Override
    protected void onStart() {
        super.onStart();

        myRef.addValueEventListener(new ValueEventListener(){


            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

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
