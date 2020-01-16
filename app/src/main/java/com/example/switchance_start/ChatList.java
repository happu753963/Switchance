package com.example.switchance_start;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.switchance_start.Adapter.EnvelopeAdapter;
import com.example.switchance_start.Datas.ChatData;
import com.example.switchance_start.model.Constant;
import com.example.switchance_start.model.UserInfo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class ChatList extends Fragment {
    ImageView imageView5;
    Button button2;
    EnvelopeAdapter envelopeAdapter;
    RecyclerView recyclerViewInterestedSkill;
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();
    ArrayList<String> messageIdArrayList = new ArrayList<String>();
    ArrayList<String> friendIdArrayList = new ArrayList<String>();


    public ChatList() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_envelope2, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        setAdapter();
        getChatList();
    }

    public void initView(View view) {
        recyclerViewInterestedSkill = (RecyclerView) view.findViewById(R.id.recyclerView_interestedSkill);
    }

    public void setAdapter() {
        recyclerViewInterestedSkill.setLayoutManager(new LinearLayoutManager(getActivity()));
        envelopeAdapter = new EnvelopeAdapter(getActivity());
        recyclerViewInterestedSkill.setAdapter(envelopeAdapter);

        envelopeAdapter.addItem(new ChatData("123", "123", R.drawable.goose));
        envelopeAdapter.addItem(new ChatData("123", "123", R.drawable.goose));
        envelopeAdapter.addItem(new ChatData("123", "123", R.drawable.goose));
    }

    public void getChatList() {
        Log.v("messageId", Singleton.getInstance().getAccount());
        myRef.child("user_info").child(Singleton.getInstance().getAccount()).child("friends").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                envelopeAdapter.clearItem();
                friendIdArrayList.clear();
                Log.v("messageId", dataSnapshot.toString());
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Log.v("messageId", snapshot.toString());
                    friendIdArrayList.add(snapshot.getKey());
                }

                for (int i = 0; i < friendIdArrayList.size(); i++) {
                    myRef.child("user_info").child(friendIdArrayList.get(i)).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            envelopeAdapter.clearItem();
                            envelopeAdapter.addItem(new ChatData(dataSnapshot.getKey(), Integer.parseInt(dataSnapshot.child("icon").getValue().toString())));
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
