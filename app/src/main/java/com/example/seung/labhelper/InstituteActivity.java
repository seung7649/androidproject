package com.example.seung.labhelper;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ScrollView;
import android.widget.TextView;

public class InstituteActivity extends AppCompatActivity {

    ScrollView scrollView;
    TextView tv[]= new TextView[9];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //액션바 불러오기
        try{
            getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
            getSupportActionBar().setCustomView(R.layout.custom_bar_insti);
            //뒤로가기 버튼 추가
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        setContentView(R.layout.activity_institute);
        scrollView = (ScrollView)findViewById(R.id.scrollView_insti);

        tv[0]=(TextView)findViewById(R.id.page_instiname);
        tv[1]=(TextView)findViewById(R.id.page_instimajor);
        tv[2]=(TextView)findViewById(R.id.page_instiaddr);
        tv[3]=(TextView)findViewById(R.id.page_instipay);
        tv[4]=(TextView)findViewById(R.id.page_instiduration);
        tv[5]=(TextView)findViewById(R.id.page_instirequired);
        tv[6]=(TextView)findViewById(R.id.page_instilastratio);
        tv[7]=(TextView)findViewById(R.id.page_instiurl);
        tv[8]=(TextView)findViewById(R.id.institute_actionbar);

        Intent intent = getIntent();

        String str1 = intent.getStringExtra("name");
        String str2 = intent.getStringExtra("major");
        String str3 = intent.getStringExtra("addr");
        String str4 = intent.getStringExtra("pay");
        String str5 = intent.getStringExtra("duration");
        String str6 = intent.getStringExtra("required");
        String str7 = intent.getStringExtra("lastratio");
        String str8 = intent.getStringExtra("url");

        tv[8].setText(str1);
        tv[0].setText(checkStr(str1));
        tv[1].setText(str2);
        tv[2].setText(str3);
        tv[3].setText(new StringBuilder().append(str4).append(" 만원").toString());
        tv[4].setText(str5+" 년");
        tv[5].setText(str6);
        tv[6].setText(str7);
        tv[7].setText(str8);

    }
    //뒤로가기 선택됫을 때 동작
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case android.R.id.home:
                finish();
                //NavUtils.navigateUpFromSameTask(this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private String checkStr(String temp){
        if(temp.equals("기초과학연구원"))temp=temp+"(IBS)";
        else if(temp.equals("목암생명과학연구소"))temp="녹십자 "+temp;
        else if(temp.equals("한국과학기술연구원"))temp=temp+"(KIST)";
        else if(temp.equals("한국생명공학연구원"))temp=temp+"(KRIBB)";
        else ;
        return temp;
    }
}
