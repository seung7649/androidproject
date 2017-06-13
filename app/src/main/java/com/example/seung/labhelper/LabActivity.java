package com.example.seung.labhelper;

import android.app.ActionBar;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

public class LabActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //액션바 불러오기
        try{
            getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
            getSupportActionBar().setCustomView(R.layout.custom_bar_lab);
            //뒤로가기 버튼 추가
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        setContentView(R.layout.activity_lab);
    }
    //뒤로가기 선택됫을때 동작
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
