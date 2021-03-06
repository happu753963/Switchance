package com.example.switchance_start.register;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.switchance_start.HomePage;
import com.example.switchance_start.MainActivity;
import com.example.switchance_start.R;
import com.example.switchance_start.model.Constant;
import com.example.switchance_start.model.UserInfo;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class InterestedSkillActivity extends AppCompatActivity {

    ImageButton btn_back;
    Button btn_next;
    InterestedSkillAdapter interestedSkillAdapter;
    RecyclerView recyclerViewInterestedSkill;
    EditText edtxtSkill;
    ImageButton addInterestedSkill;

    InterestedExperienceAdapter interestedExperienceAdapter;
    RecyclerView recyclerViewInterestedExperience;
    EditText edtxtExperience;
    ImageButton addInterestedExperience;

    InterestedItemAdapter interestedItemAdapter;
    RecyclerView recyclerViewInterestedItem;
    EditText edtxtItem;
    ImageButton addInterestedItem;
    DatabaseReference databaseUserinfos;
    //String id;
    UserInfo userInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insterested_skill);

        databaseUserinfos = FirebaseDatabase.getInstance().getReferenceFromUrl(Constant.DB_URL).child(Constant.CHILD_REF_USERINFO);

        //id = getIntent().getStringExtra("id");
        userInfo = (UserInfo) getIntent().getSerializableExtra("userInfo");

        setBtn_back();
        setBtn_next();
        initInterestedSkillView();
        initInterestedExperienceView();
        initInterestedItemView();
    }

    private void setBtn_next() {
        btn_next = (Button)findViewById(R.id.btn_next); //跳到主頁

        btn_next.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {

//                interestedSkillAdapter.addItem(new InterestedSkill("攝影"));
//                interestedSkillAdapter.addItem(new InterestedSkill("寫程式"));
//
//                interestedExperienceAdapter.addItem(new InterestedExperience("打工"));
//                interestedExperienceAdapter.addItem(new InterestedExperience("換宿"));
//
//                interestedItemAdapter.addItem(new InterestedItem("鈴鼓"));
//                interestedItemAdapter.addItem(new InterestedItem("唱歌神器"));
                //myRef.child(Constant.CHILD_REF_USERINFO).child(id).child("interestedSkill").setValue(interestedSkillAdapter.getArrayList());
                //myRef.child(Constant.CHILD_REF_USERINFO).child(id).child("interestedExperience").setValue(interestedExperienceAdapter.getArrayList());
                //myRef.child(Constant.CHILD_REF_USERINFO).child(id).child("interestedItem").setValue(interestedItemAdapter.getArrayList());
				if(interestedSkillAdapter.getItemCount() > 0) {
					userInfo.setInterestedSkills(interestedSkillAdapter.getArrayList());
				}
				if(interestedExperienceAdapter.getItemCount() > 0) {
					userInfo.setInterestedExperiences(interestedExperienceAdapter.getArrayList());
				}
				if(interestedItemAdapter.getItemCount() > 0) {
					userInfo.setInterestedItems(interestedItemAdapter.getArrayList());
				}

                databaseUserinfos.child(userInfo.getId()).setValue(userInfo);
//                Intent intent = new Intent();
//                intent.setClass(InterestedSkillActivity.this, HomePage.class);
//                startActivity(intent);
//                InterestedSkillActivity.this.finish();

                Intent intent = new Intent();
                intent.setClass(InterestedSkillActivity.this, HomePage.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
    }

    private void setBtn_back() { //返回鍵
        btn_back = (ImageButton) findViewById(R.id.btn_back);

        btn_back.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.setClass(InterestedSkillActivity.this, OwnedSkillActivity.class);
                startActivity(intent);
//                InterestedSkillActivity.this.finish();
            }
        });
    }

    public void initInterestedSkillView() {
        interestedSkillAdapter = new InterestedSkillAdapter(this);
        edtxtSkill = (EditText) findViewById(R.id.edtxt_skill);
        addInterestedSkill = (ImageButton) findViewById(R.id.add_skill);
        recyclerViewInterestedSkill = (RecyclerView) findViewById(R.id.recyclerView_interestedSkill);

//        GridLayoutManager manager = new GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false);
        GridLayoutManager manager = new GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        recyclerViewInterestedSkill.setLayoutManager(manager);
        recyclerViewInterestedSkill.setAdapter(interestedSkillAdapter);

        //動態新增
        addInterestedSkill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!edtxtSkill.getText().toString().trim().equals("")) {
                    interestedSkillAdapter.addItem(new InterestedSkill(edtxtSkill.getText().toString()));
                    edtxtSkill.setText("");
                }
            }
        });
    }

    public void initInterestedExperienceView() {
        interestedExperienceAdapter = new InterestedExperienceAdapter(this);
        edtxtExperience = (EditText) findViewById(R.id.edtxt_experience);
        addInterestedExperience = (ImageButton) findViewById(R.id.add_experience);
        recyclerViewInterestedExperience = (RecyclerView) findViewById(R.id.recyclerView_interestedExperience);

//        GridLayoutManager manager = new GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false);
        GridLayoutManager manager = new GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        recyclerViewInterestedExperience.setLayoutManager(manager);
        recyclerViewInterestedExperience.setAdapter(interestedExperienceAdapter);

        //動態新增
        addInterestedExperience.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!edtxtExperience.getText().toString().trim().equals("")) {
                    interestedExperienceAdapter.addItem(new InterestedExperience(edtxtExperience.getText().toString()));
                    edtxtExperience.setText("");
                }
            }
        });
    }

    public void initInterestedItemView() {
        interestedItemAdapter = new InterestedItemAdapter(this);
        edtxtItem = (EditText) findViewById(R.id.edtxt_item);
        addInterestedItem = (ImageButton) findViewById(R.id.add_item);
        recyclerViewInterestedItem = (RecyclerView) findViewById(R.id.recyclerView_interestedItem);

//        GridLayoutManager manager = new GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false);
        GridLayoutManager manager = new GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        recyclerViewInterestedItem.setLayoutManager(manager);
        recyclerViewInterestedItem.setAdapter(interestedItemAdapter);

        //動態新增
        addInterestedItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!edtxtItem.getText().toString().trim().equals("")) {
                    interestedItemAdapter.addItem(new InterestedItem(edtxtItem.getText().toString()));
                    edtxtItem.setText("");
                }
            }
        });
    }

}
