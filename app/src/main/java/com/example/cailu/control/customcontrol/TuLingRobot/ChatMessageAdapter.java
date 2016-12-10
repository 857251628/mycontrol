package com.example.cailu.control.customcontrol.TuLingRobot;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.cailu.control.R;
import com.example.cailu.control.customcontrol.TuLingRobot.bean.ChatMessage;

import java.util.List;

/**
 * Created by Administrator on 2016/11/13 0013.
 */
public class ChatMessageAdapter extends BaseAdapter{
    private Context mContext;
    private List<ChatMessage> mDatas;
    private LayoutInflater from;

    public ChatMessageAdapter(Context mContext, List<ChatMessage> mDatas) {
        this.mContext = mContext;
        from = LayoutInflater.from(mContext);
        this.mDatas = mDatas;
    }

    @Override
    public int getCount() {
        return mDatas==null?0: mDatas.size();
    }

    @Override
    public Object getItem(int i) {
        return mDatas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public int getItemViewType(int position) {
        ChatMessage msg = mDatas.get(position);
        return msg.getType() == ChatMessage.Type.INPUT ? 1 : 0;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ChatMessage mChatMessage = mDatas.get(i);

        ViewHolder mViewHolder = null;
        if (view == null) {
            mViewHolder = new ViewHolder();
            if (mChatMessage.getType() == ChatMessage.Type.INPUT) {

                view = from.inflate(R.layout.tulin_chat_from_msg,
                        viewGroup, false);
                mViewHolder.createDate = (TextView) view
                        .findViewById(R.id.chat_from_createDate);
                mViewHolder.content = (TextView) view
                        .findViewById(R.id.chat_from_content);
                view.setTag(mViewHolder);
            } else {

                view = from.inflate(R.layout.tulin_chat_send_msg,
                        viewGroup, false);
                mViewHolder.createDate = (TextView) view
                        .findViewById(R.id.chat_send_createDate);
                mViewHolder.content = (TextView) view
                        .findViewById(R.id.chat_send_content);
                view.setTag(mViewHolder);
            }
        } else {
            mViewHolder = (ViewHolder) view.getTag();
        }

        mViewHolder.content.setText(mChatMessage.getMsg());
        mViewHolder.createDate.setText(mChatMessage.getDateStr());

        return view;
    }

    private class ViewHolder
    {
        public TextView createDate;
        public TextView name;
        public TextView content;
    }
}
