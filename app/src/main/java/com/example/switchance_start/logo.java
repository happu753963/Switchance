package com.example.switchance_start;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.switchance_start.Adapter.LogoAdapter;
import com.example.switchance_start.Adapter.LogoItemAdapter;
import com.example.switchance_start.model.Constant;
import com.example.switchance_start.model.UserInfo;
import com.example.switchance_start.register.OwnedSkill;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class logo extends Fragment {
    // TODO: Rename parameter arguments, choose names that match


    GridView gridViewUserinfos;

    List<UserInfo> userinfos;
    DatabaseReference databaseUserinfos;
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();
    RecyclerView recyclerView;
    RecyclerView logoItemView;
    LogoAdapter logoAdapter;
    LogoItemAdapter logoItemAdapter;

    public logo() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_logo2, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initView(view);
        setAdapter();
        getUserData();
    }

    public void initView(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        logoItemView=(RecyclerView)view.findViewById(R.id.logo_item_recyclerview);
    }

    public void setAdapter() {
        logoAdapter = new LogoAdapter(getActivity());
        recyclerView.setAdapter(logoAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));

        //        logoItemView.setAdapter(logoItemAdapter);

//        logoItemView.setLayoutManager(new GridLayoutManager(getActivity(),3));

        databaseUserinfos = FirebaseDatabase.getInstance().getReferenceFromUrl(Constant.DB_URL).child(Constant.CHILD_REF_USERINFO);
    }

    public void getUserData() {
        myRef.child(Constant.CHILD_REF_USERINFO).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                logoAdapter.clearItem();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    if (!postSnapshot.getKey().equals("Willy")) {
                        UserInfo userinfo = postSnapshot.getValue(UserInfo.class);
                        //adding artist to the list
                        logoAdapter.addItem(userinfo);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}