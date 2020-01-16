package com.example.switchance_start.register;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.switchance_start.MainActivity;
import com.example.switchance_start.R;
import com.example.switchance_start.Singleton;
import com.example.switchance_start.model.Constant;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {
    ImageButton btn_back;
    ImageButton btn_icon;
    EditText edtxt_schoolMail;
    DatePicker datePicker;
    EditText edtxt_password;
    EditText edtxt_doubleCheck;
    EditText edtxt_name;
    EditText edtxt_account;
    Button btn_next;
    Spinner spin_gender;
    Spinner spin_school;
    Spinner spin_department;
    int icon;
    boolean checkMail;
    String strEmail;
    String id;

    private static final String TAG = RegisterActivity.class.getSimpleName();
    public static FirebaseDatabase database = FirebaseDatabase.getInstance();
    public static final DatabaseReference myRef = database.getInstance().getReferenceFromUrl("https://switchance-e8900.firebaseio.com/");

    DatabaseReference databaseUserinfos;
    String birthday;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        databaseUserinfos = FirebaseDatabase.getInstance().getReferenceFromUrl(Constant.DB_URL).child(Constant.CHILD_REF_USERINFO);

        initViews();

        final String[] gender = {"男", "女", "其他"};
        ArrayAdapter<String> genderList = new ArrayAdapter<>(RegisterActivity.this,
                android.R.layout.simple_spinner_dropdown_item,
                gender);
        spin_gender.setAdapter(genderList);

        final String[] school = {"國立成功大學", "國立臺南藝術大學", "南臺科技大學", "台南應用科技大學",
                "台南首府大學", "嘉南藥理大學", "國立臺南大學", "國立臺南護理專科學校", "崑山科技大學", "遠東科技大學", "長榮大學"};
        ArrayAdapter<String> schoolList = new ArrayAdapter<>(RegisterActivity.this,
                android.R.layout.simple_spinner_dropdown_item,
                school);
        spin_school.setAdapter(schoolList);

        final String[] department = {"文學院", "理學院", "工學院", "管理學院", "醫學院",
                "社會科學院", "電機資訊學院", "規劃與設計學院", "生物科學與科技學院", "視覺藝術學院",
                "文博學院", "音像藝術學院", "音樂學院", "商管學院", "人文學院", "設計學院"};
        ArrayAdapter<String> departmentList = new ArrayAdapter<>(RegisterActivity.this,
                android.R.layout.simple_spinner_dropdown_item,
                department);
        spin_department.setAdapter(departmentList);



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
                // 取得DatePicker並轉換字串
                int year = datePicker.getYear();
                int month = datePicker.getMonth() + 1;
                int day = datePicker.getDayOfMonth();
                birthday = year + "-" + month + "-" + day;
                String mail = edtxt_schoolMail.getText().toString();
                boolean flag = true;

                Log.v("showicon",String.valueOf(icon));

                if (edtxt_account.getText().toString().trim().equals("")) {
                    Toast toast = Toast.makeText(RegisterActivity.this, "帳號不可為空白!", Toast.LENGTH_LONG);
                    toast.show();
                    flag = false;
                    return;
                }
                if (edtxt_schoolMail.getText().toString().trim().equals("")) {
                    Toast toast = Toast.makeText(RegisterActivity.this, "信箱不可為空白!", Toast.LENGTH_LONG);
                    toast.show();
                    flag = false;
                    return;
                }
                if (edtxt_password.getText().toString().trim().equals("")) {
                    Toast toast = Toast.makeText(RegisterActivity.this, "密碼不可為空白!", Toast.LENGTH_LONG);
                    toast.show();
                }
                if (edtxt_doubleCheck.getText().toString().trim().equals("")) {
                    Toast toast = Toast.makeText(RegisterActivity.this, "確認密碼不可為空白!", Toast.LENGTH_LONG);
                    toast.show();
                    flag = false;
                    return;
                }
                if (edtxt_name.getText().toString().trim().equals("")) {
                    Toast toast = Toast.makeText(RegisterActivity.this, "姓名不可為空白!", Toast.LENGTH_LONG);
                    toast.show();
                    flag = false;
                    return;
                }
                if (mail.trim().equals("")) {
                    if (mail.matches("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*")) {
                        Toast toast = Toast.makeText(RegisterActivity.this, "信箱格式不符", Toast.LENGTH_LONG);
                        toast.show();
                    }
//                    if (null == edtxt_schoolMail || "".equals(edtxt_schoolMail)) return false;
//                    //Pattern p = Pattern.compile("\\w+@(\\w+.)+[a-z]{2,3}"); //简单匹配
//                    Pattern p = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");//复杂匹配
//                    Matcher m = p.matcher(edtxt_schoolMail.toString());
//                    return m.matches();
                }
                if (!edtxt_password.getText().toString().equals(edtxt_doubleCheck.getText().toString())) {
                    Toast toast = Toast.makeText(RegisterActivity.this, "確認密碼錯誤", Toast.LENGTH_LONG);
                    toast.show();
                    flag = false;
                    return;
                }

                if (flag) {
                    testting();
                }
                setUserInfo();
                testting();
            }
        });

    }
    // TODO  need  refactor
    public void testting() {
//        id = databaseUserinfos.push().getKey();
//        setUserInfo();

        //內存個人註冊設定
//                    SharedPreferences preferences_register = getSharedPreferences("Register", MODE_PRIVATE);     //呼叫getSharedPreferences()方法，產生一個檔名為temp_storge.xml的設定儲存檔，並只供本專案(app)可讀取，物件名稱為pref。
//                    preferences_register.edit()
//                            .putString("ID", id)  //呼叫edit()方法取得編輯器物件，此時使用匿名方式呼叫Editor的putString()方法將字串的內容寫入設定檔，資料標籤為”ACCOUNT”。
//                            .putString("ACCOUNT", edtxt_account.getText().toString())  //呼叫edit()方法取得編輯器物件，此時使用匿名方式呼叫Editor的putString()方法將字串的內容寫入設定檔，資料標籤為”ACCOUNT”。
//                            .putInt("ICON", icon)
//                            .putString("GENDER", spin_gender.getSelectedItem().toString())
//                            .putString("BIRTHDAY", birthday)
//                            .putString("SCHOOL", spin_school.getSelectedItem().toString())
//                            .putString("DEPARTMENT", spin_department.getSelectedItem().toString())
//                            .putString("EMAIL", edtxt_schoolMail.getText().toString())
//                            .putString("PASSWORD", edtxt_password.getText().toString())
//                            .putString("NAME", edtxt_name.getText().toString())
//                            .putString("CHECK", "0") //檢查碼(確認是從註冊進入APP)
//                            .commit();      //最後必須呼叫commit()方法，此時資料才真正寫入到設定檔中。

        Intent intent = new Intent();
        //把資料丟給下一頁
//                    intent.putExtra("account", edtxt_account.getText().toString());
//                intent.putExtra("account","xuansun");
        intent.setClass(RegisterActivity.this, OwnedSkillActivity.class);
        startActivity(intent);
    }

    public void setUserInfo() {
        Singleton.getInstance().setName(edtxt_name.getText().toString());
        Singleton.getInstance().setId(id);
        Singleton.getInstance().setIcon(icon);
        Singleton.getInstance().setBirthday(birthday);
        Singleton.getInstance().setAccount(edtxt_account.getText().toString());
        Singleton.getInstance().setPassword(edtxt_password.getText().toString());
        Singleton.getInstance().setMail(edtxt_schoolMail.getText().toString());
        Singleton.getInstance().setDepartment(spin_department.getSelectedItem().toString());
        Singleton.getInstance().setGender(spin_gender.getSelectedItem().toString());
        Singleton.getInstance().setSchool(spin_school.getSelectedItem().toString());
        Singleton.getInstance().setCheck(0);
    }



    private void initViews() {

        edtxt_schoolMail = (EditText) findViewById(R.id.edtxt_schoolMail);
        datePicker = (DatePicker) findViewById(R.id.datePicker);
        edtxt_password = (EditText) findViewById(R.id.edtxt_password);
        edtxt_doubleCheck = (EditText) findViewById(R.id.edtxt_doubleCheck);
        edtxt_name = (EditText) findViewById(R.id.edtxt_name);
        edtxt_account = (EditText) findViewById(R.id.edtxt_account);
        btn_back = (ImageButton) findViewById(R.id.btn_back);
        btn_icon = (ImageButton) findViewById(R.id.btn_icon);
        btn_next = (Button) findViewById(R.id.btn_next);
        spin_gender = (Spinner) findViewById(R.id.spin_gender);
        spin_school = (Spinner) findViewById(R.id.spin_school);
        spin_department = (Spinner) findViewById(R.id.spin_department);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {
            return;
        }
//        Toast.makeText(this,"2",Toast.LENGTH_LONG).show();
        if (requestCode == 1) {

            switch (data.getIntExtra("image", 2131165311)) {
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
