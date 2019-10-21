package com.example.switchance_start.register;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.switchance_start.MainActivity;
import com.example.switchance_start.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {
    ImageButton btn_back;
    ImageButton btn_icon;
    EditText edtxt_schoolMail;
    EditText edtxt_birthday;
    EditText edtxt_password;
    EditText edtxt_doubleCheck;
    EditText edtxt_name;
    EditText edtxt_account;

    Spinner spin_gender;
    Spinner spin_school;
    Spinner spin_department;
    int icon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getInstance().getReferenceFromUrl("https://switchance-e8900.firebaseio.com/");


        edtxt_schoolMail = (EditText) findViewById(R.id.edtxt_schoolMail);

        edtxt_birthday = (EditText) findViewById(R.id.edtxt_birthday);

        edtxt_password = (EditText) findViewById(R.id.edtxt_password);

        edtxt_doubleCheck = (EditText) findViewById(R.id.edtxt_doubleCheck);

        edtxt_name = (EditText) findViewById(R.id.edtxt_name);

        edtxt_account = (EditText) findViewById(R.id.edtxt_account);


        btn_back = (ImageButton) findViewById(R.id.btn_back);
        btn_icon = (ImageButton) findViewById(R.id.btn_icon);
        Button btn_next = (Button) findViewById(R.id.btn_next);

        btn_back.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.setClass(RegisterActivity.this, MainActivity.class);
                startActivity(intent);
//                RegisterActivity.this.finish();
            }
        });

        btn_icon.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.setClass(RegisterActivity.this, IconActivity.class);
                startActivityForResult(intent, 1);

            }
        });

        btn_next.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {


                myRef.child("user_info").child(edtxt_account.getText().toString()).child("icon").setValue(icon);
                myRef.child("user_info").child(edtxt_account.getText().toString()).child("gender").setValue(spin_gender.getSelectedItem().toString());
                myRef.child("user_info").child(edtxt_account.getText().toString()).child("birthday").setValue(edtxt_birthday.getText().toString());
                myRef.child("user_info").child(edtxt_account.getText().toString()).child("school").setValue(spin_school.getSelectedItem().toString());
                myRef.child("user_info").child(edtxt_account.getText().toString()).child("department").setValue(spin_department.getSelectedItem().toString());
                myRef.child("user_info").child(edtxt_account.getText().toString()).child("mail").setValue(edtxt_schoolMail.getText().toString());
                myRef.child("user_info").child(edtxt_account.getText().toString()).child("password").setValue(edtxt_password.getText().toString());
                myRef.child("user_info").child(edtxt_account.getText().toString()).child("name").setValue(edtxt_name.getText().toString());

//                myRef.child("user_info").child("xuansun").child("icon").setValue(R.drawable.lion);
//                myRef.child("user_info").child("xuansun").child("gender").setValue("女");
//                myRef.child("user_info").child("xuansun").child("birthday").setValue("20190724");
//                myRef.child("user_info").child("xuansun").child("school").setValue("國立成功大學");
//                myRef.child("user_info").child("xuansun").child("department").setValue("管理學院");
//                myRef.child("user_info").child("xuansun").child("mail").setValue("ncku123@ncku.edu.tw");
//                myRef.child("user_info").child("xuansun").child("password").setValue("878787");
//                myRef.child("user_info").child("xuansun").child("name").setValue("sunxuanxuan");

                Intent intent = new Intent();
                //把資料丟給下一頁
                intent.putExtra("account",edtxt_account.getText().toString());
//                intent.putExtra("account","xuansun");
                intent.setClass(RegisterActivity.this, OwnedSkillActivity.class);
                startActivity(intent);
//                RegisterActivity.this.finish();
            }
        });

        spin_gender = (Spinner) findViewById(R.id.spin_gender);
        final String[] gender = {"男", "女", "其他"};
        ArrayAdapter<String> genderList = new ArrayAdapter<>(RegisterActivity.this,
                android.R.layout.simple_spinner_dropdown_item,
                gender);
        spin_gender.setAdapter(genderList);

        spin_school = (Spinner) findViewById(R.id.spin_school);
        final String[] school = {"國立成功大學", "國立臺南藝術大學", "南臺科技大學", "台南應用科技大學",
                "台南首府大學", "嘉南藥理大學", "國立臺南大學", "國立臺南護理專科學校", "崑山科技大學", "遠東科技大學", "長榮大學"};
        ArrayAdapter<String> schoolList = new ArrayAdapter<>(RegisterActivity.this,
                android.R.layout.simple_spinner_dropdown_item,
                school);
        spin_school.setAdapter(schoolList);

        spin_department = (Spinner) findViewById(R.id.spin_department);
        final String[] department = {"文學院", "理學院", "工學院", "管理學院", "醫學院",
                "社會科學院", "電機資訊學院", "規劃與設計學院", "生物科學與科技學院", "視覺藝術學院",
                "文博學院", "音像藝術學院", "音樂學院", "商管學院", "人文學院", "設計學院"};
        ArrayAdapter<String> departmentList = new ArrayAdapter<>(RegisterActivity.this,
                android.R.layout.simple_spinner_dropdown_item,
                department);
        spin_department.setAdapter(departmentList);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {
            return;
        }
//        Toast.makeText(this,"2",Toast.LENGTH_LONG).show();
        if (requestCode == 1) {

            switch (data.getIntExtra("image", 0)) {
                case R.drawable.chameleon:
                    btn_icon.setImageResource(R.drawable.chameleon);
                    icon = R.drawable.chameleon;
                    break;
                case R.drawable.chicken:
                    btn_icon.setImageResource(R.drawable.chicken);
                    icon = R.drawable.chicken;
                    break;
                case R.drawable.fox:
                    btn_icon.setImageResource(R.drawable.fox);
                    icon = R.drawable.fox;
                    break;
                case R.drawable.goose:
                    btn_icon.setImageResource(R.drawable.goose);
                    icon = R.drawable.goose;
                    break;
                case R.drawable.lion:
                    btn_icon.setImageResource(R.drawable.lion);
                    icon = R.drawable.lion;
                    break;
                case R.drawable.hedgehog:
                    btn_icon.setImageResource(R.drawable.hedgehog);
                    icon = R.drawable.hedgehog;
                    break;
            }
        }
    }
}
