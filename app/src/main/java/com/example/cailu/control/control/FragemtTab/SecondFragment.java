package com.example.cailu.control.control.FragemtTab;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cailu.control.R;

/**
 * Created by cailu on 2016/6/20.
 */
public class SecondFragment  extends Fragment{
    private View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = LayoutInflater.from(getActivity()).inflate(R.layout.mycontact_indicator, null);
        TextView tv= (TextView) view.findViewById(R.id.tabText);
        tv.setTextSize(50);
        tv.setText("第2个页面");
        return view;
    }
//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        TextView tv= (TextView) view.findViewById(R.id.tabText);
//        tv.setText("第2个页面");
//    }
}
