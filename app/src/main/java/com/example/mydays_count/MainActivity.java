package com.example.mydays_count;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<jinianri> dayslist;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter adapter;
    private MyDbOpenHelper mydb;
    private SQLiteDatabase dbRead;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        //核心代码
        dayslist = new ArrayList<>();
        mydb = new MyDbOpenHelper(MainActivity.this,"my.db", null, 1);
        //用可读方法打开数据库
        dbRead = mydb.getReadableDatabase();
        Cursor c = dbRead.query("rili",null,null,null, null, null, null);
        String desc = null;
        Long d = null;
        while(c.moveToNext()){
            desc = c.getString(c.getColumnIndex("describe"));
            d = c.getLong(c.getColumnIndex("day"));
           // Toast.makeText(MainActivity.this,Long.toString(d),Toast.LENGTH_SHORT).show();
            dayslist.add(new jinianri(d,desc));
        }


        //LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerView= (RecyclerView)findViewById(R.id.recyclerView);
        adapter=new RecyclerViewAdapter(dayslist,this);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        /////////////////////////////////////////////

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,setdate_activity.class);
                startActivityForResult(i,200);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 200 && resultCode == 300) {
             adapter.addData(adapter.getItemCount(),new jinianri(data.getLongExtra("date",0),data.getStringExtra("des")));
        }
    }
}
