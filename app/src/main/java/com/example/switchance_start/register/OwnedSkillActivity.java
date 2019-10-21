package com.example.switchance_start.register;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.switchance_start.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class OwnedSkillActivity extends AppCompatActivity {

    OwnedSkillAdapter ownedSkillAdapter;
    RecyclerView recyclerViewOwnedSkill;
    EditText edtxtSkill;
    ImageButton addOwnedSkill;

    OwnedExperienceAdapter ownedExperienceAdapter;
    RecyclerView recyclerViewOwnedExperience;
    EditText edtxtExperience;
    ImageButton addOwnedExperience;

    OwnedItemAdapter ownedItemAdapter;
    RecyclerView recyclerViewOwnedItem;
    EditText edtxtItem;
    ImageButton addOwnedItem;
    String account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owned_skill);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getInstance().getReferenceFromUrl("https://switchance-e8900.firebaseio.com/");

        account = getIntent().getStringExtra("account");

        ImageButton btn_back = (ImageButton) findViewById(R.id.btn_back);
        Button btn_next = (Button) findViewById(R.id.btn_next);


        btn_back.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.setClass(OwnedSkillActivity.this, RegisterActivity.class);
                startActivity(intent);
//                OwnedSkillActivity.this.finish();
            }
        });

        btn_next.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ownedSkillAdapter.addItem(new OwnedSkill("吃飯"));
//                ownedSkillAdapter.addItem(new OwnedSkill("寫食記"));
//
//                ownedExperienceAdapter.addItem(new OwnedExperience("留學"));
//                ownedExperienceAdapter.addItem(new OwnedExperience("實習"));
//
//                ownedItemAdapter.addItem(new OwnedItem("碗"));
//                ownedItemAdapter.addItem(new OwnedItem("吉他"));
                myRef.child("user_info").child(account).child("ownedSkill").setValue(ownedSkillAdapter.getArrayList());
                myRef.child("user_info").child(account).child("ownedExperience").setValue(ownedExperienceAdapter.getArrayList());
                myRef.child("user_info").child(account).child("ownedItem").setValue(ownedItemAdapter.getArrayList());


                Intent intent = new Intent();
//                intent.putExtra("account","xuansun");
                intent.putExtra("account",account);

                intent.setClass(OwnedSkillActivity.this, InterestedSkillActivity.class);
                startActivity(intent);
//                OwnedSkillActivity.this.finish();
            }
        });

        initOwnedSkillView();
        initOwnedExperienceView();
        initOwnedItemView();
    }

    public void initOwnedSkillView() {
        ownedSkillAdapter = new OwnedSkillAdapter(this);
        edtxtSkill = (EditText) findViewById(R.id.edtxt_skill);
        addOwnedSkill = (ImageButton) findViewById(R.id.add_skill);
        recyclerViewOwnedSkill = (RecyclerView) findViewById(R.id.recyclerView_ownedSkill);

//        GridLayoutManager manager = new GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false);
        GridLayoutManager manager = new GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        recyclerViewOwnedSkill.setLayoutManager(manager);
        recyclerViewOwnedSkill.setAdapter(ownedSkillAdapter);

        //動態新增
        addOwnedSkill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!edtxtSkill.getText().toString().trim().equals("")) {
                    ownedSkillAdapter.addItem(new OwnedSkill(edtxtSkill.getText().toString()));
                    edtxtSkill.setText("");
                }
            }
        });
    }


    public void initOwnedExperienceView() {
        ownedExperienceAdapter = new OwnedExperienceAdapter(this);
        edtxtExperience = (EditText) findViewById(R.id.edtxt_experience);
        addOwnedExperience = (ImageButton) findViewById(R.id.add_experience);
        recyclerViewOwnedExperience = (RecyclerView) findViewById(R.id.recyclerView_ownedExperience);

//        GridLayoutManager manager = new GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false);
        GridLayoutManager manager = new GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        recyclerViewOwnedExperience.setLayoutManager(manager);
        recyclerViewOwnedExperience.setAdapter(ownedExperienceAdapter);

        //動態新增
        addOwnedExperience.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!edtxtExperience.getText().toString().trim().equals("")) {
                    ownedExperienceAdapter.addItem(new OwnedExperience(edtxtExperience.getText().toString()));
                    edtxtExperience.setText("");
                }
            }
        });
    }

    public void initOwnedItemView() {
        ownedItemAdapter = new OwnedItemAdapter(this);
        edtxtItem = (EditText) findViewById(R.id.edtxt_item);
        addOwnedItem = (ImageButton) findViewById(R.id.add_item);
        recyclerViewOwnedItem = (RecyclerView) findViewById(R.id.recyclerView_ownedItem);

//        GridLayoutManager manager = new GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false);
        GridLayoutManager manager = new GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        recyclerViewOwnedItem.setLayoutManager(manager);
        recyclerViewOwnedItem.setAdapter(ownedItemAdapter);

        //動態新增
        addOwnedItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!edtxtItem.getText().toString().trim().equals("")) {
                    ownedItemAdapter.addItem(new OwnedItem(edtxtItem.getText().toString()));
                    edtxtItem.setText("");
                }
            }
        });
    }

}
