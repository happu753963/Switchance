package com.example.switchance_start;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.switchance_start.Adapter.PersonalInterestedExperienceAdapter;
import com.example.switchance_start.Adapter.PersonalInterestedOwnedAdapter;
import com.example.switchance_start.register.InterestedExperience;
import com.example.switchance_start.register.OwnedExperience;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class Personal extends Fragment {
    TextView txt_ansTime;
    TextView txt_place;
    RecyclerView interestRecyclerView;
    RecyclerView ownedRecyclerView;
    PersonalInterestedExperienceAdapter personalInterestedExperienceAdapter;
    PersonalInterestedOwnedAdapter personalInterestedOwnedAdapter;
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();

    public Personal() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_personal2, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initView(view);
        setAdapter();
        setFirebase();
    }

    public void initView(View view) {
        txt_ansTime = (TextView) view.findViewById(R.id.txt_ansTime);
        txt_place = (TextView) view.findViewById(R.id.txt_place);
        interestRecyclerView = (RecyclerView) view.findViewById(R.id.interestRecyclerView);
        ownedRecyclerView = (RecyclerView) view.findViewById(R.id.ownedRecyclerView);

        personalInterestedExperienceAdapter = new PersonalInterestedExperienceAdapter(getActivity());
        personalInterestedOwnedAdapter = new PersonalInterestedOwnedAdapter(getActivity());
    }

    public void setAdapter() {
        interestRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(),3));
        interestRecyclerView.setAdapter(personalInterestedExperienceAdapter);

        ownedRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(),3));
        ownedRecyclerView.setAdapter(personalInterestedOwnedAdapter);

        personalInterestedExperienceAdapter.addItem(new InterestedExperience("測試1","ownedSkill"));
        personalInterestedExperienceAdapter.addItem(new InterestedExperience("測試1","colorSkillMiddle"));
        personalInterestedExperienceAdapter.addItem(new InterestedExperience("測試1","ownedSkill"));
        personalInterestedExperienceAdapter.addItem(new InterestedExperience("測試1","ownedSkill"));
        personalInterestedExperienceAdapter.addItem(new InterestedExperience("測試1","ownedSkill"));
        personalInterestedExperienceAdapter.addItem(new InterestedExperience("測試1","ownedSkill"));
        personalInterestedExperienceAdapter.addItem(new InterestedExperience("測試1","ownedSkill"));
        personalInterestedExperienceAdapter.addItem(new InterestedExperience("測試1","colorSkillDark"));


        personalInterestedOwnedAdapter.addItem(new OwnedExperience("測試1","colorSkillDark"));
        personalInterestedOwnedAdapter.addItem(new OwnedExperience("測試1","colorSkillDark"));
    }

    public void setFirebase() {
        myRef.child("user_info").child("Willy").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

//    public void setFirebase() {
//        final ImageView icon = getActivity().findViewById(R.id.img_icon);
//
//
//        //把firebase資料加進adapter，以及隨時更新資料
//        myRef.addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(@NotNull DataSnapshot dataSnapshot, @NotNull String s) {
//                Log.v("testingting", "yes2");
//                for (int num = 0 ; num < 10 ; num++) {
//                    if (String.valueOf(dataSnapshot.child(account).child("interestedExperiences").child(String.valueOf(num)).child("interestedExperience").getValue()) != "null") {
//                        adapter_interestedExperience.add(String.valueOf(dataSnapshot.child(account).child("interestedExperiences").child(String.valueOf(num)).child("interestedExperience").getValue()));
//                    }
//                    if (String.valueOf(dataSnapshot.child(account).child("interestedSkills").child(String.valueOf(num)).child("interestedSkill").getValue()) != "null") {
//                        adapter_interestedSkill.add(String.valueOf(dataSnapshot.child(account).child("interestedSkills").child(String.valueOf(num)).child("interestedSkill").getValue()));
//                    }
//                    if (String.valueOf(dataSnapshot.child(account).child("interestedItems").child(String.valueOf(num)).child("interestedItem").getValue()) != "null") {
//                        adapter_interestedItem.add(String.valueOf(dataSnapshot.child(account).child("interestedItems").child(String.valueOf(num)).child("interestedItem").getValue()));
//                    }
//                    if (String.valueOf(dataSnapshot.child(account).child("ownedExperiences").child(String.valueOf(num)).child("ownedExperience").getValue()) != "null") {
//                        adapter_ownedExperience.add(String.valueOf(dataSnapshot.child(account).child("ownedExperiences").child(String.valueOf(num)).child("ownedExperience").getValue()));
//                        Log.v("testingting", String.valueOf(dataSnapshot.child(account).child("ownedExperiences").child(String.valueOf(num)).child("ownedExperience").getValue()));
//                    }
//                    if (String.valueOf(dataSnapshot.child(account).child("ownedSkills").child(String.valueOf(num)).child("ownedSkill").getValue()) != "null") {
//                        adapter_ownedSkill.add(String.valueOf(dataSnapshot.child(account).child("ownedSkills").child(String.valueOf(num)).child("ownedSkill").getValue()));
//                    }
//                    if (String.valueOf(dataSnapshot.child(account).child("ownedItems").child(String.valueOf(num)).child("ownedItem").getValue()) != "null") {
//                        adapter_ownedItem.add(String.valueOf(dataSnapshot.child(account).child("ownedItems").child(String.valueOf(num)).child("ownedItem").getValue()));
//                    }
//
//                }
////                Log.d("count",String.valueOf(adapter.getItemViewType()));
//                img_icon[0] = String.valueOf(dataSnapshot.child(account).child("icon").getValue());      //firebase抓icon資料
//                switch (img_icon[0]){
//                    case "2131165282":  //雞
//                        icon.setImageResource(R.drawable.chicken);
//                        break;
//                    case "2131165280":  //變色龍
//                        icon.setImageResource(R.drawable.chameleon);
//                        break;
//                    case "2131165324":  //獅子
//                        icon.setImageResource(R.drawable.lion);
//                        break;
//                    case "2131165315":  //刺蝟
//                        icon.setImageResource(R.drawable.hedgehog);
//                        break;
//                    case "2131165314":  //鵝
//                        icon.setImageResource(R.drawable.goose);
//                        break;
//                    case "0":  //狐狸
//                        icon.setImageResource(R.drawable.fox);
//                        break;
//
//                }
//                Log.d("~~~~~~~~~~~~~~~~~~~~~~~~~~~~`img_icon:",String.valueOf(img_icon[0]));
//            }
//
//            @Override
//            public void onChildChanged(@NotNull DataSnapshot dataSnapshot, @NotNull String s) {
//                adapter_interestedExperience.clear();
//                adapter_interestedSkill.clear();
//                adapter_interestedItem.clear();
//                adapter_ownedSkill.clear();
//                adapter_ownedItem.clear();
//                adapter_ownedExperience.clear();
//                for (int num = 0; num < 10; num++) {
//                    if (String.valueOf(dataSnapshot.child(account).child("interestedExperiences").child(String.valueOf(num)).child("interestedExperience").getValue()) != "null") {
//                        adapter_interestedExperience.add(String.valueOf(dataSnapshot.child(account).child("interestedExperiences").child(String.valueOf(num)).child("interestedExperience").getValue()));
//                    }
//                    if (String.valueOf(dataSnapshot.child(account).child("interestedSkills").child(String.valueOf(num)).child("interestedSkill").getValue()) != "null") {
//                        adapter_interestedSkill.add(String.valueOf(dataSnapshot.child(account).child("interestedSkills").child(String.valueOf(num)).child("interestedSkill").getValue()));
//                    }
//                    if (String.valueOf(dataSnapshot.child(account).child("interestedItems").child(String.valueOf(num)).child("interestedItem").getValue()) != "null") {
//                        adapter_interestedItem.add(String.valueOf(dataSnapshot.child(account).child("interestedItems").child(String.valueOf(num)).child("interestedItem").getValue()));
//                    }
//                    if (String.valueOf(dataSnapshot.child(account).child("ownedExperiences").child(String.valueOf(num)).child("ownedExperience").getValue()) != "null") {
//                        adapter_ownedExperience.add(String.valueOf(dataSnapshot.child(account).child("ownedExperiences").child(String.valueOf(num)).child("ownedExperience").getValue()));
//                    }
//                    if (String.valueOf(dataSnapshot.child(account).child("ownedSkills").child(String.valueOf(num)).child("ownedSkill").getValue()) != "null") {
//                        adapter_ownedSkill.add(String.valueOf(dataSnapshot.child(account).child("ownedSkills").child(String.valueOf(num)).child("ownedSkill").getValue()));
//                    }
//                    if (String.valueOf(dataSnapshot.child(account).child("ownedItems").child(String.valueOf(num)).child("ownedItem").getValue()) != "null") {
//                        adapter_ownedItem.add(String.valueOf(dataSnapshot.child(account).child("ownedItems").child(String.valueOf(num)).child("ownedItem").getValue()));
//                    }
//                }
//
//            }
//            @Override
//            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
//
//            }
//
//            @Override
//            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//    }
}
