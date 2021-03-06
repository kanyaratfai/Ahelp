package com.example.pareeya.ahelp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    //Explicit ประกาศตัวแปร
    private MyManage myManage;
    private EditText nameEditText, MyPhoneEditText;
    private String nameString, MyPhoneString;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Bind Widget
        nameEditText = (EditText) findViewById(R.id.editText7);
        MyPhoneEditText = (EditText) findViewById(R.id.editText8);



        myManage = new MyManage(this);
        //Check SQLite ถ้า Login แล้ว เมื่อเข้าแอพครั้งต่อไปให้เข้าหน้า Home
        if (checkSQLite()) {
            startActivity(new Intent(MainActivity.this,HomeActivity.class));
            finish();
        }

    }//Main Method

    private boolean checkSQLite() {//ตรวจสอบ SQLite

        boolean result = false;

        SQLiteDatabase sqLiteDatabase = openOrCreateDatabase(MyOpenHelper.database_name,
                MODE_PRIVATE, null);
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM userTABLE", null);//เลือก จากตารางผู้ใช้และค่าว่าง
        cursor.moveToFirst();//บนลงล่าง
        if (cursor.getCount() !=0) {
            result = true;
        }

        Log.d("4SepV1", "Result==>" + result);

        return result;
    }

    public void clickSaveData(View view) {//บันทึกข้อมูล

        nameString = nameEditText.getText().toString().trim();
        MyPhoneString = MyPhoneEditText.getText().toString().trim();

        //Check Space

        if (checkSpace()) {
            //Have Space ถ้ามีความว่างเปล่า
            MyAlert myAlert = new MyAlert();
            myAlert.myDialog(this, "กรอกข้อมูลไม่ครบ", "กรุณากรอกข้อมูลใหม่ คะ");
        } else {
            confirmData();

        }

    }//clickSaveData

    private void confirmData() {//การยืนยันข้อมูล
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setIcon(R.drawable.a55);
        builder.setTitle("โปรดตรวจสอบข้อมูล");
        builder.setMessage("ชื่อ-นามสกุล = " + nameString + "\n" +
                            "เบอร์โทร = " + MyPhoneString);
        builder.setNegativeButton("ยกเลิก", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.setPositiveButton("ยืนยัน", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                SaveSQLite();

                dialog.dismiss();
            }
        });
        builder.show();

    }

    private void SaveSQLite() {

        myManage.addValueToSQLite(nameString, MyPhoneString);//เพิ่มข้อมูลเข้าไปในฐานข้อมูล
        startActivity(new Intent(MainActivity.this,ContentActivity.class));


    }

    private boolean checkSpace() {//ตรวจสอบพื้นที่
        return nameString.equals("") ||
                MyPhoneString.equals("");
    }

}//Main Class
