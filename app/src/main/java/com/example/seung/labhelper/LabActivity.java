package com.example.seung.labhelper;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ScrollView;
import android.widget.TextView;

public class LabActivity extends AppCompatActivity {

    ScrollView scrollView;
    TextView tv[]=new TextView[27];
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
        scrollView = (ScrollView)findViewById(R.id.scrollView_lab);

        tv[0]=(TextView)findViewById(R.id.page_labname);
        tv[1]=(TextView)findViewById(R.id.page_labproname);
        tv[2]=(TextView)findViewById(R.id.page_labcurnum);
        tv[3]=(TextView)findViewById(R.id.page_labkeyword);
        tv[4]=(TextView)findViewById(R.id.page_lablastratio);
        tv[5]=(TextView)findViewById(R.id.page_laburl);
        tv[6]=(TextView)findViewById(R.id.page_lablasta_bar);
        tv[7]=(TextView)findViewById(R.id.page_lablasta_num);
        tv[8]=(TextView)findViewById(R.id.page_lablastb_bar);
        tv[9]=(TextView)findViewById(R.id.page_lablastb_num);
        tv[10]=(TextView)findViewById(R.id.page_lablastc_bar);
        tv[11]=(TextView)findViewById(R.id.page_lablastc_num);
        tv[12]=(TextView)findViewById(R.id.page_lablastd_bar);
        tv[13]=(TextView)findViewById(R.id.page_lablastd_num);
        tv[14]=(TextView)findViewById(R.id.page_lablaste_bar);
        tv[15]=(TextView)findViewById(R.id.page_lablaste_num);
        tv[16]=(TextView)findViewById(R.id.page_labfivea_bar);
        tv[17]=(TextView)findViewById(R.id.page_labfivea_num);
        tv[18]=(TextView)findViewById(R.id.page_labfiveb_bar);
        tv[19]=(TextView)findViewById(R.id.page_labfiveb_num);
        tv[20]=(TextView)findViewById(R.id.page_labfivec_bar);
        tv[21]=(TextView)findViewById(R.id.page_labfivec_num);
        tv[22]=(TextView)findViewById(R.id.page_labfived_bar);
        tv[23]=(TextView)findViewById(R.id.page_labfived_num);
        tv[24]=(TextView)findViewById(R.id.page_labfivee_bar);
        tv[25]=(TextView)findViewById(R.id.page_labfivee_num);
        tv[26]=(TextView)findViewById(R.id.lab_actionbar);

        Intent intent = getIntent();

        String st1 = intent.getStringExtra("name");
        String st2 = intent.getStringExtra("proname");
        String st3 = intent.getStringExtra("currnum");
        String st4 = intent.getStringExtra("keyword");
        String st5 = intent.getStringExtra("lastratio");
        String st6 = intent.getStringExtra("url");
        String st7 = intent.getStringExtra("lasta");
        String st8 = intent.getStringExtra("lastb");
        String st9 = intent.getStringExtra("lastc");
        String st10 = intent.getStringExtra("lastd");
        String st11 = intent.getStringExtra("laste");
        String st12 = intent.getStringExtra("fivea");
        String st13 = intent.getStringExtra("fiveb");
        String st14 = intent.getStringExtra("fivec");
        String st15 = intent.getStringExtra("fived");
        String st16 = intent.getStringExtra("fivee");

        tv[0].setText(st1);tv[26].setText(st1);
        tv[1].setText(st2);
        tv[2].setText(new StringBuilder().append(st3).append(" 명").toString());
        tv[3].setText(st4);
        tv[4].setText(st5);
        tv[5].setText(st6);
        tv[6].setWidth(convertDp(Integer.parseInt(st7))*20);
        tv[7].setText(new StringBuilder().append("(").append(st7).append(")").toString());
        tv[8].setWidth(convertDp(Integer.parseInt(st8))*20);
        tv[9].setText(new StringBuilder().append("(").append(st8).append(")").toString());
        tv[10].setWidth(convertDp(Integer.parseInt(st9))*20);
        tv[11].setText(new StringBuilder().append("(").append(st9).append(")").toString());
        tv[12].setWidth(convertDp(Integer.parseInt(st10))*20);
        tv[13].setText(new StringBuilder().append("(").append(st10).append(")").toString());
        tv[14].setWidth(convertDp(Integer.parseInt(st11))*20);
        tv[15].setText(new StringBuilder().append("(").append(st11).append(")").toString());
        tv[16].setWidth(convertDp(Integer.parseInt(st12))*20);
        tv[17].setText(new StringBuilder().append("(").append(st12).append(")").toString());
        tv[18].setWidth(convertDp(Integer.parseInt(st13))*20);
        tv[19].setText(new StringBuilder().append("(").append(st13).append(")").toString());
        tv[20].setWidth(convertDp(Integer.parseInt(st14))*20);
        tv[21].setText(new StringBuilder().append("(").append(st14).append(")").toString());
        tv[22].setWidth(convertDp(Integer.parseInt(st15))*20);
        tv[23].setText(new StringBuilder().append("(").append(st15).append(")").toString());
        tv[24].setWidth(convertDp(Integer.parseInt(st16))*20);
        tv[25].setText(new StringBuilder().append("(").append(st16).append(")").toString());

    }
    //뒤로가기 선택됫을때 동작
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

    private int convertDp(int px){
        float mScale=getResources().getDisplayMetrics().density;
        int calHeight = (int)(px*mScale);
        return calHeight;
    }
}
