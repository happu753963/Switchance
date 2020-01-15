package com.example.switchance_start.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.switchance_start.ChatRoom;
import com.example.switchance_start.Datas.ChatData;
import com.example.switchance_start.R;
import com.example.switchance_start.Singleton;

import java.util.ArrayList;

public class ChatRoomAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    ArrayList messageList;
    Context mContext;
    public static final int VIEW_TYPE_SELF_TEXT_CHAT = 10;
    public static final int VIEW_TYPE_FRIEND_TEXT_CHAT = 20;

    public ChatRoomAdapter(Context context) {
        mContext = context;
        messageList = new ArrayList<ChatData>();
    }

    @NonNull
    @Override
    public ChatRoomAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.recycler_view_envelope, parent, false);
        return new ChatRoomAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ChatData chatData = (ChatData) messageList.get(position);
        ((ViewHolder) holder).bindToMySelf(chatData);
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }

    public ArrayList getArrayList() {
        return messageList;
    }

    public void addItem(ChatData experience) {
        messageList.add(experience);
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if (Singleton.getInstance().getAccount().equals(((ChatData) messageList.get(position)).getId())) {
            return VIEW_TYPE_SELF_TEXT_CHAT;
        } else {
            return VIEW_TYPE_FRIEND_TEXT_CHAT;
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView img_icon;
        TextView txt_account;
        TextView txt_messenge;


        //        ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            img_icon = (ImageView) itemView.findViewById(R.id.img_icon);
            txt_account = (TextView) itemView.findViewById(R.id.txt_account);
            txt_messenge = (TextView) itemView.findViewById(R.id.txt_messenge);
        }

        public void bindToMySelf(ChatData chatData) {
            img_icon.setImageResource(chatData.getIcon());
            txt_account.setText(chatData.getId());
            txt_messenge.setText(chatData.getMessenge());
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent();
            intent.setClass(mContext, ChatRoom.class);
            mContext.startActivity(intent);
        }
    }


}
