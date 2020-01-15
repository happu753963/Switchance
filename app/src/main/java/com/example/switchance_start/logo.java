package com.example.switchance_start;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

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

    public logo() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static logo newInstance(String param1, String param2) {
        logo fragment = new logo();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_logo2, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        gridViewUserinfos = (GridView) view.findViewById(R.id.gridViewUserinfos);

        databaseUserinfos = FirebaseDatabase.getInstance().getReferenceFromUrl(Constant.DB_URL).child(Constant.CHILD_REF_USERINFO);
        userinfos = new ArrayList<>();
//        p2_logo = (ImageView) view.findViewById(R.id.p2_logo);
//        p2_logo.setOnClickListener(new ImageView.OnClickListener() {
//            @Override
//            public void onClick(View v) {

//                Intent intent = new Intent();
//                intent.setClass(ChatList.this, ChatRoom.class);

//                Intent intent = new Intent(getActivity(), OtherUser.class);
//                startActivity(intent);
//            }
//        });
    }

    @Override
    public void onStart() {
        super.onStart();

        //attaching value event listener
        databaseUserinfos.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //clearing the previous artist list
                userinfos.clear();

                //iterating through all the nodes
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    //getting userinfo

                    UserInfo userinfo = postSnapshot.getValue(UserInfo.class);
                    Log.v("id", userinfo.getId());

                    //adding artist to the list
                    userinfos.add(userinfo);
                }

                //creating adapter
                UserInfoList userinfotAdapter = new UserInfoList((HomePage) getActivity(), userinfos);
                //attaching adapter to the listview
                // gridViewUserinfos.setNumColumns(2);
                gridViewUserinfos.setAdapter(userinfotAdapter);
                gridViewUserinfos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                        Intent intent = new Intent(getActivity(), OtherUser.class);
                        intent.putExtra("userInfo", userinfos.get(position));
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}