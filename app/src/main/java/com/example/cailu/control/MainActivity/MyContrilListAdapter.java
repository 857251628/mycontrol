package com.example.cailu.control.MainActivity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.cailu.control.R;

import java.util.ArrayList;


/**
 * Created by cailu on 2016/6/2.
 */
public class MyContrilListAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<String> Contrilname;
    private ArrayList<Intent> myIntentlist;

    public MyContrilListAdapter(Context context) {
        this.context = context;
    }

    public void setName(ArrayList<String> Contrilname) {
        this.Contrilname = Contrilname;
    }

    public void setMyIntentlist(ArrayList<Intent> myIntentlist) {
        this.myIntentlist = myIntentlist;
    }

    @Override
    public int getCount() {
        return Contrilname != null ? Contrilname.size() : 0;
    }

    @Override
    public Object getItem(int position) {

        return Contrilname.get(position);
    }

    @Override
    public long getItemId(int position) {

        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_mycontrillistview, null);
            viewHolder = new ViewHolder();
           viewHolder.textView = (TextView) convertView.findViewById(R.id.item_Contrilname);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.textView.setText(position+"  "+Contrilname.get(position));
        viewHolder.textView.setTag(position);
        viewHolder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = myIntentlist.get(position);
                context.startActivity(intent);
            }
        });

        return convertView;
    }



//    int myposition;

//    @Override
//    public void onClick(View v) {
//
//        Intent intent = myIntentlist.get();
//        context.startActivity(intent);
//    }

  static class ViewHolder {
        private TextView textView;


    }

}
