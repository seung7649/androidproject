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

public class ListViewBtnAdapter2 extends ArrayAdapter implements View.OnClickListener {
    public interface  ListBtnClickListener{
        void onListBtnClick(int position);
    }
    int resourcId;
    private com.example.seung.labhelper.ListViewBtnAdapter.ListBtnClickListener listBtnClickListener;

    ListViewBtnAdapter2(Context context, int resource, ArrayList<ListViewItem2>list, com.example.seung.labhelper.ListViewBtnAdapter.ListBtnClickListener clickListener){
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
        //final TextView pronameView = (TextView) convertView.findViewById(R.id.item_proname);
        final TextView majorView = (TextView)convertView.findViewById(R.id.item_major);

        final ListViewItem2 listViewItem = (ListViewItem2)getItem(position);

        nameView.setText(listViewItem.getName());
        //pronameView.setText(listViewItem.getProname());
        majorView.setText(listViewItem.getMajor());

        Button button = (Button)convertView.findViewById(R.id.linklab);
        //key값을 넘겨줌
        //이 버튼을 누르면 원하는 database 값에 접근 가능
        int temp=Integer.parseInt(listViewItem.getKey().substring(5))-1;
        button.setTag(temp);
        //if(temp>=5)button.setVisibility(View.INVISIBLE);
        button.setOnClickListener(this);

        return convertView;
    }

    public void onClick(View v){
        if((int)v.getTag()>=5)return;
        ListViewItem2 listViewItem = (ListViewItem2)getItem((int)v.getTag());
        if(this.listBtnClickListener!=null){
            String str1 = listViewItem.getName();
            String str2 = listViewItem.getMajor();
            String str3 = listViewItem.getAddr();
            String str4 = listViewItem.getPay();
            String str5 = listViewItem.getDuration();
            String str6 = listViewItem.getRequired();
            String str7 = listViewItem.getLastfromlab()+"/"+listViewItem.getLasttotal();
            String str8 = listViewItem.getUrl();

            Intent intent = new Intent(v.getContext(),InstituteActivity.class);

            intent.putExtra("name", str1);
            intent.putExtra("major", str2);
            intent.putExtra("addr", str3);
            intent.putExtra("pay", str4);
            intent.putExtra("duration", str5);
            intent.putExtra("required", str6);
            intent.putExtra("lastratio", str7);
            intent.putExtra("url", str8);
            v.getContext().startActivity(intent);
        }
    }
}
