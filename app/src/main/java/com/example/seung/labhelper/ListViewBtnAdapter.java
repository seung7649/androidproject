package com.example.seung.labhelper;

import android.content.Context;
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
        //final int pos = position;
        final Context context = parent.getContext();

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(this.resourcId,parent,false);
        }

        final TextView nameView = (TextView)convertView.findViewById(R.id.item_name);
        final TextView pronameView = (TextView) convertView.findViewById(R.id.item_proname);
        final TextView majorView = (TextView)convertView.findViewById(R.id.item_major);

        final ListViewItem listViewItem = (ListViewItem)getItem(position);

        nameView.setText(listViewItem.getName());
        pronameView.setText(listViewItem.getProname());
        majorView.setText(listViewItem.getMajor());

        Button button = (Button)convertView.findViewById(R.id.linklab);
        //key값을 넘겨줌
        //이 버튼을 누르면 원하는 database 값에 접근 가능
        button.setTag(listViewItem.getKey());
        button.setOnClickListener(this);

        return convertView;
    }

    public void onClick(View v){
        if(this.listBtnClickListener!=null){
            //this.listBtnClickListener.onListBtnClick((int)v.getTag());
        }
    }
}
