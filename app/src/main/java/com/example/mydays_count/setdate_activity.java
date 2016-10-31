package com.example.mydays_count;

import android.content.Intent;

import android.database.sqlite.SQLiteDatabase;

import android.support.v7.app.AppCompatActivity;


import android.os.Bundle;

import android.view.View;
import android.view.View.OnClickListener;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;


public class setdate_activity extends AppCompatActivity {
    private MyDbOpenHelper mydb;
    private SQLiteDatabase dbWrite;
    EditText year, month, dayofmonth, des;
    Button b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setdate_activity);
        b = (Button) findViewById(R.id.submit);
        year = (EditText) findViewById(R.id.year);
        month = (EditText) findViewById(R.id.month);
        dayofmonth = (EditText) findViewById(R.id.dayofmonth);
        des = (EditText) findViewById(R.id.describe);

        b.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //TimeZone.setDefault(TimeZone.getTimeZone("GMT+9"));
                Calendar c = Calendar.getInstance();
                c.set(Calendar.YEAR, Integer.parseInt(year.getText().toString()));
                c.set(Calendar.MONTH, Integer.parseInt(month.getText().toString()) - 1);
                c.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dayofmonth.getText().toString()));
                c.set(Calendar.HOUR, 0);
                c.set(Calendar.MINUTE, 0);
                c.set(Calendar.SECOND, 0);
                long temp = c.getTimeInMillis();
                System.out.println(new Date(temp));
                mydb = new MyDbOpenHelper(setdate_activity.this, "my.db", null, 1);
                //用可读方法打开数据库
                dbWrite = mydb.getWritableDatabase();
                dbWrite.execSQL("insert into rili(describe,day) values('" + des.getText().toString() + "'," + temp + ")");
                Toast.makeText(setdate_activity.this, "添加成功!", Toast.LENGTH_LONG).show();
                Intent intent = new Intent();
                intent.putExtra("des", des.getText().toString());
                intent.putExtra("date", temp);
                setResult(300, intent);
                finish();
            }
        });

    }
}

