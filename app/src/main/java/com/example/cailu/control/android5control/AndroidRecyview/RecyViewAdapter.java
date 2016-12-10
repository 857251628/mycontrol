package com.example.cailu.control.android5control.AndroidRecyview;

import android.content.Context;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cailu.control.R;

import java.util.ArrayList;

/**
 * Created by cailu on 2016/8/17.
 */
public class RecyViewAdapter extends RecyclerView.Adapter<RecyViewAdapter.ViewHolder> {
    private OnItemClickListener mOnItemClickListener;
    private ArrayList<String> mArrayList;
    private Context mContext;

    public void setmArrayList(ArrayList<String> mArrayList) {
        this.mArrayList = mArrayList;
    }

    public RecyViewAdapter(Context mContext) {
        this.mContext = mContext;
    }


    //  用于创建控键
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //获取列表控件
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_android5_recyview, parent, false);
       // View item = View.inflate(parent.getContext(),R.layout.item_android5_recyview,null);
        return new ViewHolder(item);
    }

    //为控件设置数据
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        String s = mArrayList.get(position);
        holder.mTextView.setText(s);
        holder.itemView.setTag(s);
        if (mOnItemClickListener != null) {
            /**
             * 这里加了判断，itemViewHolder.itemView.hasOnClickListeners()
             * 目的是减少对象的创建，如果已经为view设置了click监听事件,就不用重复设置了
             * 不然每次调用onBindViewHolder方法，都会创建两个监听事件对象，增加了内存的开销
             */
            if (!holder.itemView.hasOnClickListeners()) {
                Log.e("ListAdapter", "setOnClickListener");
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int pos = holder.getPosition();
                        mOnItemClickListener.onItemClick(v, pos);

                    }
                });
                holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        int pos = holder.getPosition();
                        mOnItemClickListener.onItemClick(v, pos);
                        return true;
                    }
                });
            }
        }
    }


    @Override
    public int getItemCount() {
        return mArrayList.size()!=0?mArrayList.size():null;
    }

    /**
     *      * 向指定位置添加元素
     *      * @param position
     *       * @param value
     *
     */
    public void add(int position, String value) {
        if (position > mArrayList.size()) {
            position = mArrayList.size();
        }
        if (position < 0) {
            position = 0;
        }
        mArrayList.add(position, value);
        /**
                  * 使用notifyItemInserted/notifyItemRemoved会有动画效果
                  * 而使用notifyDataSetChanged()则没有
                  */
        notifyItemInserted(position);
    }

    /**
     *      * 移除指定位置元素
     *       * @param position
     *      * @return
     *
     */
    public String remove(int position) {
        if (position > mArrayList.size() - 1) {
            return null;
        }
        String value = mArrayList.remove(position);
        notifyItemRemoved(position);
        return value;
    }


    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = (OnItemClickListener) mOnItemClickListener;
    }

    /**
     *      * 处理item的点击事件和长按事件
     *
     */
    interface OnItemClickListener {
        public void onItemClick(View view, int position);

        public void onItemLongClick(View view, int position);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView mTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.Recyview_textview);
        }
    }
}
