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

public class MainActivity extends AppCompatActivity  implements ListViewBtnAdapter.ListBtnClickListener{

    ScrollView scrollView;
    ArrayList<ListViewItem> items_lab;
    ArrayList<ListViewItem2> items_insti;
    ListViewBtnAdapter adapter_lab;
    ListViewBtnAdapter2 adapter_insti;
    ListView listview_lab;
    ListView listview_insti;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
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


        //스크롤 뷰 사용
        scrollView = (ScrollView)findViewById(R.id.scrollView1);
        scrollView = (ScrollView)findViewById(R.id.scrollView2);


        // 데이터 베이스에서 읽어올 준비

        items_lab = new ArrayList<ListViewItem>();
        items_insti = new ArrayList<ListViewItem2>();

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();


        //item 로드
        //LAB 탭인지 연구실 탭인지
        loadItemsFormDB(0);

        mytabs.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                //Log.i("***Selected Tab", "Im currently in tab with index::" + mytabs.getCurrentTab());
                //LAB 선택하면 tag=0 연구실 선택하면 tag=1
                //여기서 어떤 key로 로드 할 지 선택하자
                if(mytabs.getCurrentTab()==0)return;
                loadItemsFormDB(mytabs.getCurrentTab());
            }
        });

        //Adapter 생성
        //adapter_lab = new ListViewBtnAdapter(this, R.layout.list, items_lab, this);
       // adapter_insti= new ListViewBtnAdapter2(this, R.layout.list,items_insti,this);
        //리스트뷰 참조 및 Adapter 달기
        //listview_lab=(ListView)findViewById(R.id.listview1);
        //listview_insti=(ListView)findViewById(R.id.listview2);
        //listview_lab.setAdapter(adapter_lab);
        //listview_insti.setAdapter(adapter_insti);
        adapter_lab = new ListViewBtnAdapter(this, R.layout.list, items_lab, this);
        listview_lab=(ListView)findViewById(R.id.listview1);
        listview_lab.setAdapter(adapter_lab);
        adapter_insti= new ListViewBtnAdapter2(this, R.layout.list,items_insti,this);
        listview_insti=(ListView)findViewById(R.id.listview2);
        listview_insti.setAdapter(adapter_insti);



    }

    private boolean loadItemsFormDB(int index) {
        DatabaseReference mRef;
        TextView textView;
        int cnt1=19;//# of lab
        int cnt2=5;//# of insti
        int i=1;
        //탭 버튼에 따라 달라지게
        switch(index){
            case 0:{

            mRef=databaseReference.child("labid");
                //database 에서 읽어오기
                while(i<(cnt1+1)){
                    final ListViewItem listViewItem = new ListViewItem();
                    listViewItem.setKey("lab"+i);
                    mRef.child("lab"+i).child("curnum").addValueEventListener(
                            new ValueEventListener() {
                               @Override
                               public void onDataChange(DataSnapshot dataSnapshot) {
                                   String temp = dataSnapshot.getValue(String.class);
                                   if(temp==null) Log.i("here","sh");
                                   listViewItem.setCurnum(temp);
                               }

                               @Override
                               public void onCancelled(DatabaseError databaseError) {

                               }
                           }
                    );
                    mRef.child("lab"+i).child("fivea").addValueEventListener(
                            new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                String temp = dataSnapshot.getValue(String.class);
                                listViewItem.setFive_a(temp);
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        }
                    );
                    mRef.child("lab"+i).child("fiveb").addValueEventListener(
                            new ValueEventListener() {
                             @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                String temp = dataSnapshot.getValue(String.class);
                                 listViewItem.setFive_b(temp);
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                             }
                        }
                    );
                    mRef.child("lab"+i).child("fivec").addValueEventListener(
                            new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    String temp = dataSnapshot.getValue(String.class);
                                    listViewItem.setFive_c(temp);
                             }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                        }
                    );
                    mRef.child("lab"+i).child("fived").addValueEventListener(
                            new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String temp = dataSnapshot.getValue(String.class);
                        listViewItem.setFive_d(temp);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                }
                    );
                    mRef.child("lab"+i).child("fivee").addValueEventListener(
                            new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String temp = dataSnapshot.getValue(String.class);
                        listViewItem.setFive_e(temp);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                }
                    );
                    mRef.child("lab"+i).child("keyword").addValueEventListener(
                            new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String temp = dataSnapshot.getValue(String.class);
                        listViewItem.setMajor(temp);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                }
                    );
                    mRef.child("lab"+i).child("labname").addValueEventListener(
                            new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String temp = dataSnapshot.getValue(String.class);
                        listViewItem.setName(temp);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                }
                    );
                    mRef.child("lab"+i).child("lasta").addValueEventListener(
                            new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String temp = dataSnapshot.getValue(String.class);
                        listViewItem.setLast_a(temp);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                }
                    );
                    mRef.child("lab"+i).child("lastb").addValueEventListener(
                            new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String temp = dataSnapshot.getValue(String.class);
                        listViewItem.setLast_b(temp);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                }
                    );
                    mRef.child("lab"+i).child("lastc").addValueEventListener(
                            new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String temp = dataSnapshot.getValue(String.class);
                        listViewItem.setLast_c(temp);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                }
                    );
                    mRef.child("lab"+i).child("lastd").addValueEventListener(
                            new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String temp = dataSnapshot.getValue(String.class);
                        listViewItem.setLast_d(temp);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                }
                    );
                    mRef.child("lab"+i).child("laste").addValueEventListener(
                            new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String temp = dataSnapshot.getValue(String.class);
                        listViewItem.setLast_e(temp);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                }
                    );
                    mRef.child("lab"+i).child("lastinstinum").addValueEventListener(
                            new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String temp = dataSnapshot.getValue(String.class);
                        listViewItem.setLast_inst_num(temp);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                }
                    );
                    mRef.child("lab"+i).child("lasttotalnum").addValueEventListener(
                            new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String temp = dataSnapshot.getValue(String.class);
                        listViewItem.setLast_total_num(temp);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                }
                    );
                    mRef.child("lab"+i).child("proname").addValueEventListener(
                            new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String temp = dataSnapshot.getValue(String.class);
                        listViewItem.setProname(temp);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                }
                    );
                    mRef.child("lab"+i).child("url").addValueEventListener(
                            new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    String temp = dataSnapshot.getValue(String.class);
                                    listViewItem.setUrl(temp);
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            }
                    );
                    items_lab.add(listViewItem);
                i++;
                }


           }break;
            case 1:{
                mRef=databaseReference.child("instiid");

                i=1;
                //database에서 읽어오기
                while(i<(cnt2+1)){
                    final ListViewItem2 listViewItem = new ListViewItem2();
                    listViewItem.setKey("insti"+i);
                    mRef.child("insti"+i).child("addr").addValueEventListener(
                            new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    String temp = dataSnapshot.getValue(String.class);
                                    if(temp==null) Log.i("here","sh");
                                    listViewItem.setAddr(temp);
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            }
                    );
                    mRef.child("insti"+i).child("duration").addValueEventListener(
                            new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    String temp = dataSnapshot.getValue(String.class);
                                    listViewItem.setDuration(temp);
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            }
                    );
                    mRef.child("insti"+i).child("instiname").addValueEventListener(
                            new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    String temp = dataSnapshot.getValue(String.class);
                                    listViewItem.setName(temp);
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            }
                    );
                    mRef.child("insti"+i).child("lastfromlab").addValueEventListener(
                            new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    String temp = dataSnapshot.getValue(String.class);
                                    listViewItem.setLastfromlab(temp);
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            }
                    );
                    mRef.child("insti"+i).child("lasttotal").addValueEventListener(
                            new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    String temp = dataSnapshot.getValue(String.class);
                                    listViewItem.setLasttotal(temp);
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            }
                    );
                    mRef.child("insti"+i).child("major").addValueEventListener(
                            new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    String temp = dataSnapshot.getValue(String.class);
                                    listViewItem.setMajor(temp);
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            }
                    );
                    mRef.child("insti"+i).child("pay").addValueEventListener(
                            new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    String temp = dataSnapshot.getValue(String.class);
                                    listViewItem.setPay(temp);
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            }
                    );
                    mRef.child("insti"+i).child("required").addValueEventListener(
                            new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    String temp = dataSnapshot.getValue(String.class);
                                    listViewItem.setRequired(temp);
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            }
                    );
                    mRef.child("insti"+i).child("url").addValueEventListener(
                            new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    String temp = dataSnapshot.getValue(String.class);
                                    listViewItem.setUrl(temp);
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            }
                    );
                    items_insti.add(listViewItem);
                    i++;
                }




            }break;
            default:{
                Toast.makeText(this, "index error : "+index, Toast.LENGTH_SHORT).show();
            }break;
        }
       return  true;
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
