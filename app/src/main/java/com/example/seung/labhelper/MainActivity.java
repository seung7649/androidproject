package com.example.seung.labhelper;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TabHost;

public class MainActivity extends AppCompatActivity {

    ScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //layout 새로 만들어서 새로운 action bar 뿌려주기
        try{
            getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
            getSupportActionBar().setCustomView(R.layout.custom_bar);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        setContentView(R.layout.activity_main);

        //Tab 사용
        TabHost tabHost1 = (TabHost) findViewById(R.id.tabHost1);
        tabHost1.setup();

        TabHost.TabSpec ts1 = tabHost1.newTabSpec("Tab Spec 1");
        ts1.setContent(R.id.content1);
        ts1.setIndicator("Lab");
        tabHost1.addTab(ts1);

        TabHost.TabSpec ts2 = tabHost1.newTabSpec("Tab Spec 2");
        ts2.setContent(R.id.content2);
        ts2.setIndicator("연구소");
        tabHost1.addTab(ts2);

        //스크롤 뷰 사용
        scrollView = (ScrollView)findViewById(R.id.scrollView1);
        scrollView = (ScrollView)findViewById(R.id.scrollView2);


    }

    public void onbutton4Clicked(View v){
        Intent intent = new Intent(getApplicationContext(), LabActivity.class);
        startActivity(intent);
    }

    public void onbutton5Clicked(View v){
        Intent intent = new Intent(getApplicationContext(), InstituteActivity.class);
        startActivity(intent);
    }

}
