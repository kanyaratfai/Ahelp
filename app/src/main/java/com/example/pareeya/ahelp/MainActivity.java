package com.example.pareeya.ahelp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    //Explicit
    private MyManage myManage;
    private EditText nameEditText, MyPhoneEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Bind Widget
        nameEditText = (EditText) findViewById(R.id.editText7);
        MyPhoneEditText = (EditText) findViewById(R.id.editText8);



        myManage = new MyManage(this);

    }//Main Method
    public void clickLoginMain (View view){
        startActivity(new Intent(MainActivity.this,ContentActivity.class));

    }//clickLoginManin
}//Main Class
