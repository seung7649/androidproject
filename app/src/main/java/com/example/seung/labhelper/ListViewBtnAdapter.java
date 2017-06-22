package com.example.seung.labhelper;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by seung on 2017-06-20.
 */

public class ListViewBtnAdapter extends ArrayAdapter implements View.OnClickListener {
    public interface  ListBtnClickListener{
        void onListBtnClick(int position);


    }

    int resourcId;
    private ListBtnClickListener listBtnClickListener;



    ListViewBtnAdapter(Context context, int resource, ArrayList<ListViewItem>list, ListBtnClickListener clickListener){
        super(context ,resource, list);

        this.resourcId=resource;
        this.listBtnClickListener=clickListener;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(this.resourcId,parent,false);
        }

        final TextView nameView = (TextView)convertView.findViewById(R.id.item_name);
        final TextView pronameView = (TextView) convertView.findViewById(R.id.item_proname);
        final TextView majorView = (TextView)convertView.findViewById(R.id.item_major);

        final ListViewItem listViewItem = (ListViewItem)getItem(pos);
        //Log.i("position",Integer.toString(pos));

        nameView.setText(listViewItem.getName());
        pronameView.setText(listViewItem.getProname());
        majorView.setText(listViewItem.getMajor());

        Button button = (Button)convertView.findViewById(R.id.linklab);
        //key값을 넘겨줌
        //이 버튼을 누르면 원하는 database 값에 접근 가능
        //button.setTag(listViewItem.getKey());
        //button.setOnClickListener(this);
        int temp=Integer.parseInt(listViewItem.getKey().substring(3))-1;
        button.setTag(temp);
        button.setOnClickListener(this);

        return convertView;
    }

    public void onClick(View v){
        ListViewItem listViewItem = (ListViewItem)getItem((int)v.getTag());
        if(this.listBtnClickListener!=null){
            //lab list의 버튼에선 lab 번호*1000
            /*String temp= (String) v.getTag();
            int t = Integer.parseInt(temp.substring(3))*1000;
            this.listBtnClickListener.onListBtnClick(t);*/
            String str1=listViewItem.getName();
            String str2=listViewItem.getProname();
            String str3=listViewItem.getCurnum();
            String str4=listViewItem.getMajor();
            String str5=listViewItem.getLast_inst_num()+"/"+listViewItem.getLast_total_num();
            String str6=listViewItem.getUrl();
            if(str6==null)str6="(없음)";
            String str7=listViewItem.getLast_a();
            String str8=listViewItem.getLast_b();
            String str9=listViewItem.getLast_c();
            String str10=listViewItem.getLast_d();
            String str11=listViewItem.getLast_e();
            String str12=listViewItem.getFive_a();
            String str13=listViewItem.getFive_b();
            String str14=listViewItem.getFive_c();
            String str15=listViewItem.getFive_d();
            String str16=listViewItem.getFive_e();

            Intent intent = new Intent(v.getContext(),LabActivity.class);

            intent.putExtra("name",str1);
            intent.putExtra("proname",str2);
            intent.putExtra("currnum",str3);
            intent.putExtra("keyword",str4);
            intent.putExtra("lastratio",str5);
            intent.putExtra("url",str6);
            intent.putExtra("lasta",str7);
            intent.putExtra("lastb",str8);
            intent.putExtra("lastc",str9);
            intent.putExtra("lastd",str10);
            intent.putExtra("laste",str11);
            intent.putExtra("fivea",str12);
            intent.putExtra("fiveb",str13);
            intent.putExtra("fivec",str14);
            intent.putExtra("fived",str15);
            intent.putExtra("fivee",str16);
            v.getContext().startActivity(intent);
        }
    }
}
