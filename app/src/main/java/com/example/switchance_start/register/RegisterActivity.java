package com.example.switchance_start.register;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.switchance_start.MainActivity;
import com.example.switchance_start.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {
    ImageButton btn_back;
    ImageButton btn_icon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btn_back= (ImageButton)findViewById(R.id.btn_back);
        btn_icon= (ImageButton)findViewById(R.id.btn_icon);
        Button btn_next= (Button)findViewById(R.id.btn_next);

        btn_back.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.setClass(RegisterActivity.this, MainActivity.class);
                startActivity(intent);
//                RegisterActivity.this.finish();
            }
        });

        btn_icon.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.setClass(RegisterActivity.this, IconActivity.class);
                startActivityForResult(intent,1);

            }
        });

        btn_next.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.setClass(RegisterActivity.this, OwnedSkillActivity.class);
                startActivity(intent);
//                RegisterActivity.this.finish();
            }
        });

        Spinner spin_gender = (Spinner)findViewById(R.id.spin_gender);
        final String[] gender = {"男", "女", "其他"};
        ArrayAdapter<String> genderList = new ArrayAdapter<>(RegisterActivity.this,
                android.R.layout.simple_spinner_dropdown_item,
                gender);
        spin_gender.setAdapter(genderList);

        Spinner spin_school = (Spinner)findViewById(R.id.spin_school);
        final String[] school = {"國立成功大學", "國立臺南藝術大學", "南臺科技大學","台南應用科技大學",
                "台南首府大學","嘉南藥理大學","國立臺南大學","國立臺南護理專科學校","崑山科技大學","遠東科技大學","長榮大學"};
        ArrayAdapter<String> schoolList = new ArrayAdapter<>(RegisterActivity.this,
                android.R.layout.simple_spinner_dropdown_item,
                school);
        spin_school.setAdapter(schoolList);

        Spinner spin_department = (Spinner)findViewById(R.id.spin_department);
        final String[] department = {"文學院", "理學院", "工學院","管理學院","醫學院",
                "社會科學院","電機資訊學院","規劃與設計學院","生物科學與科技學院","視覺藝術學院",
                "文博學院","音像藝術學院","音樂學院","商管學院","人文學院","設計學院"};
        ArrayAdapter<String> departmentList = new ArrayAdapter<>(RegisterActivity.this,
                android.R.layout.simple_spinner_dropdown_item,
                department);
        spin_department.setAdapter(departmentList);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data==null){
            return;
        }
//        Toast.makeText(this,"2",Toast.LENGTH_LONG).show();
        if(requestCode==1) {

            switch (data.getIntExtra("image",0)) {
                case R.drawable.chameleon:
                    btn_icon.setImageResource(R.drawable.chameleon);
                    break;
                case R.drawable.chicken:
                    btn_icon.setImageResource(R.drawable.chicken);
                    break;
                case R.drawable.fox:
                    btn_icon.setImageResource(R.drawable.fox);
                    break;
                case R.drawable.goose:
                    btn_icon.setImageResource(R.drawable.goose);
                    break;
                case R.drawable.lion:
                    btn_icon.setImageResource(R.drawable.lion);
                    break;
                case R.drawable.hedgehog:
                    btn_icon.setImageResource(R.drawable.hedgehog);
                    break;
            }
        }
    }
}
