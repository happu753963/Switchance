package com.example.switchance_start.register;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.switchance_start.MainActivity;
import com.example.switchance_start.R;
import com.example.switchance_start.model.Constant;
import com.example.switchance_start.model.UserInfo;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

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

    DatabaseReference databaseUserinfos;

    private static final String TAG = RegisterActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        databaseUserinfos = FirebaseDatabase.getInstance().getReferenceFromUrl(Constant.DB_URL).child(Constant.CHILD_REF_USERINFO);


        edtxt_schoolMail = (EditText) findViewById(R.id.edtxt_schoolMail);

//        edtxt_birthday = (EditText) findViewById(R.id.edtxt_birthday);

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
                    //icon有default

                if(edtxt_account.getText().toString().matches(" ") ) {
                    Toast toast = Toast.makeText(RegisterActivity.this, "帳號不可為空白!", Toast.LENGTH_LONG);
                    toast.show();}
                else if(spin_gender.getSelectedItem().toString().matches("") ) {
                    Toast toast = Toast.makeText(RegisterActivity.this, "性別不可為空白!", Toast.LENGTH_LONG);
                    toast.show();}
                else if(birthday.equals("") ) {
                    Toast toast = Toast.makeText(RegisterActivity.this, "生日不可為空白!", Toast.LENGTH_LONG);
                    toast.show();}
                else if(spin_school.getSelectedItem().toString().matches("") ) {
                    Toast toast = Toast.makeText(RegisterActivity.this, "學校不可為空白!", Toast.LENGTH_LONG);
                    toast.show();}
                else if(spin_department.getSelectedItem().toString().matches("") ) {
                    Toast toast = Toast.makeText(RegisterActivity.this, "系所不可為空白!", Toast.LENGTH_LONG);
                    toast.show();}
                else if(edtxt_schoolMail.getText().toString().matches("") ) {
                    Toast toast = Toast.makeText(RegisterActivity.this, "信箱不可為空白!", Toast.LENGTH_LONG);
                    toast.show();}
                else if(edtxt_password.getText().toString().matches("") ) {
                    Toast toast = Toast.makeText(RegisterActivity.this, "密碼不可為空白!", Toast.LENGTH_LONG);
                    toast.show();}
                else if(edtxt_doubleCheck.getText().toString().matches("") ) {
                    Toast toast = Toast.makeText(RegisterActivity.this, "確認密碼不可為空白!", Toast.LENGTH_LONG);
                    toast.show();}
                else if(edtxt_name.getText().toString().matches("") ) {
                    Toast toast = Toast.makeText(RegisterActivity.this, "姓名不可為空白!", Toast.LENGTH_LONG);
                    toast.show();}
                else if(checkMail == false){
                    Toast toast = Toast.makeText(RegisterActivity.this, "信箱格式不符", Toast.LENGTH_LONG);
                    toast.show();
                }
                else if(!edtxt_password.equals(edtxt_doubleCheck)){
                    Toast toast = Toast.makeText(RegisterActivity.this, "確認密碼錯誤", Toast.LENGTH_LONG);
                    toast.show();
                }

                    else {
                        String id = databaseUserinfos.push().getKey();
                        UserInfo userInfo = new UserInfo(id, icon, spin_gender.getSelectedItem().toString(), edtxt_birthday.getText().toString(),
                                spin_school.getSelectedItem().toString(), spin_department.getSelectedItem().toString(), edtxt_schoolMail.getText().toString(),
                                edtxt_password.getText().toString(), edtxt_name.getText().toString(), edtxt_account.getText().toString());
                        databaseUserinfos.child(id).setValue(userInfo);

//                myRef.child("user_info").child("xuansun").child("icon").setValue(R.drawable.lion);
//                myRef.child("user_info").child("xuansun").child("gender").setValue("女");
//                myRef.child("user_info").child("xuansun").child("birthday").setValue("20190724");
//                myRef.child("user_info").child("xuansun").child("school").setValue("國立成功大學");
//                myRef.child("user_info").child("xuansun").child("department").setValue("管理學院");
//                myRef.child("user_info").child("xuansun").child("mail").setValue("ncku123@ncku.edu.tw");
//                myRef.child("user_info").child("xuansun").child("password").setValue("878787");
//                myRef.child("user_info").child("xuansun").child("name").setValue("sunxuanxuan");

                        //內存個人註冊設定
                        SharedPreferences preferences_register = getSharedPreferences("Register", MODE_PRIVATE);     //呼叫getSharedPreferences()方法，產生一個檔名為temp_storge.xml的設定儲存檔，並只供本專案(app)可讀取，物件名稱為pref。
                        preferences_register.edit()
                                            .putString("ID", id)  //呼叫edit()方法取得編輯器物件，此時使用匿名方式呼叫Editor的putString()方法將字串的內容寫入設定檔，資料標籤為”ACCOUNT”。
                                            .putString("ACCOUNT", edtxt_account.getText().toString())  //呼叫edit()方法取得編輯器物件，此時使用匿名方式呼叫Editor的putString()方法將字串的內容寫入設定檔，資料標籤為”ACCOUNT”。
                                            .putInt("ICON", icon)
                                            .putString("GENDER", spin_gender.getSelectedItem().toString())
                                            .putString("BIRTHDAY", edtxt_birthday.getText().toString())
                                            .putString("SCHOOL", spin_school.getSelectedItem().toString())
                                            .putString("DEPARTMENT", spin_department.getSelectedItem().toString())
                                            .putString("EMAIL", edtxt_schoolMail.getText().toString())
                                            .putString("PASSWORD", edtxt_password.getText().toString())
                                            .putString("NAME", edtxt_name.getText().toString())
                                            .putString("CHECK", "0") //檢查碼(確認是從註冊進入APP)
                                            .commit();      //最後必須呼叫commit()方法，此時資料才真正寫入到設定檔中。

                        Intent intent = new Intent();
                        //把資料丟給下一頁
                        //intent.putExtra("id", id);
                        intent.putExtra("userInfo", userInfo);
//                intent.putExtra("account","xuansun");
                        intent.setClass(RegisterActivity.this, OwnedSkillActivity.class);
                        startActivity(intent);
//                RegisterActivity.this.finish();
                    }
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
