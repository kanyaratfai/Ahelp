package com.example.pareeya.ahelp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    //Explicit
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

    }//Main Method
    public void clickSaveData(View view) {

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

    private void confirmData() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setIcon(R.drawable.help555_03);
        builder.setTitle("โปรดตรวจสอบช้อมูล");
        builder.setMessage("Name = " + nameString + "\n" +
                            "MyPhone = " + MyPhoneString);
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.setPositiveButton("Confim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                savesqlite();

                dialog.dismiss();
            }
        });
        builder.show();

    }

    private void savesqlite() {

    }

    private boolean checkSpace() {
        return nameString.equals("") ||
                MyPhoneString.equals("");
    }

}//Main Class
