package com.example.seung.labhelper;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity  implements  ListViewBtnAdapter.ListBtnClickListener{

    ScrollView scrollView;


    private FirebaseDatabase mFirebaseDatabase;
    private TabHost mytabs;
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

        mytabs= tabHost1;
            mytabs.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
                @Override
                public void onTabChanged(String tabId) {
                    //Log.i("***Selected Tab", "Im currently in tab with index::" + mytabs.getCurrentTab());
                    //LAB 선택하면 tag=0 연구실 선택하면 tag=1
                    //여기서 어떤 key로 로드 할 지 선택하자
                }
            });

        //스크롤 뷰 사용
        scrollView = (ScrollView)findViewById(R.id.scrollView1);
        scrollView = (ScrollView)findViewById(R.id.scrollView2);


        // 데이터 베이스에서 읽어올 준비
        ListView listview;
        ListViewBtnAdapter adapter;
        ArrayList<ListViewItem> items = new ArrayList<ListViewItem>();

        //item 로드
        //LAB 탭인지 연구실 탭인지
        loadItemsFormDB(items,mytabs.getCurrentTab());

        //Adapter 생성
        adapter = new ListViewBtnAdapter(this, R.layout.list, items, this);

        //리스트뷰 참조 및 Adapter 달기
        listview=(ListView)findViewById(R.id.listview1);
        listview.setAdapter(adapter);


    }

    private void loadItemsFormDB(ArrayList<ListViewItem> items, int index) {
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        DatabaseReference mRef;
        TextView textView;

        //탭 버튼에 따라 달라지게
        switch(index){
            case 0:{
                mRef=mDatabase.child("lab_id");

                //database 에서 읽어오기
                mRef.child("cnt").addListenerForSingleValueEvent(
                        new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                String temp = dataSnapshot.getValue(String.class);
                                if(temp!=null){
                                   // Log.i("read well", temp);
                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        }
                );
            }break;
            case 1:{
                mRef=mDatabase.child("insti_id");
                //database에서 읽어오기
                mRef.child("cnt").addListenerForSingleValueEvent(
                        new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                String temp = dataSnapshot.getValue(String.class);
                                if(temp!=null){
                                    Log.i("read well", temp);
                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        }
                );
            }break;
            default:{
                Toast.makeText(this, "index error : "+index, Toast.LENGTH_SHORT).show();
            }break;
        }

    }

    public void onbutton4Clicked(View v){
        Intent intent = new Intent(getApplicationContext(), LabActivity.class);
        startActivity(intent);
    }

    public void onbutton5Clicked(View v){
        Intent intent = new Intent(getApplicationContext(), InstituteActivity.class);
        startActivity(intent);
    }

    @Override
    public void onListBtnClick(int position) {
        Toast.makeText(this, Integer.toString(position+1)+"Item is selected", Toast.LENGTH_SHORT).show();
    }


}
