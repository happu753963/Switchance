package com.example.switchance_start;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import androidx.viewpager.widget.ViewPager;
import android.net.Uri;
import androidx.appcompat.widget.Toolbar;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class HomePage extends AppCompatActivity
        implements logo.OnFragmentInteractionListener, ChatList.OnFragmentInteractionListener, Personal.OnFragmentInteractionListener {

    Toolbar toolbar;
    TabLayout tabLayout;
    TabItem tabLogo;
    TabItem tabEnvelope;
    TabItem tabPerson;
    ViewPager viewPager;

    PageAdapter pageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

//        toolbar = findViewById(R.id.toolbar);
//        toolbar.setTitle(getResources().getString(R.string.app_name));
        tabLayout = findViewById(R.id.tabLayout);
        tabLogo = findViewById(R.id.tabLogo);
        tabEnvelope = findViewById(R.id.tabEnvelope);
        tabPerson = findViewById(R.id.tabPerson);
        viewPager = findViewById(R.id.viewPager);

        pageAdapter = new PageAdapter(getSupportFragmentManager(), tabLayout.getTabCount());

        viewPager.setAdapter(pageAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    }

    public void onFragmentInteraction(Uri uri) {
    }
}
