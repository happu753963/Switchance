package com.example.switchance_start.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.switchance_start.Datas.ChatData;
import com.example.switchance_start.R;
import com.example.switchance_start.Singleton;

import java.util.ArrayList;

public class ChatRoomAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    ArrayList messageList;
    Context mContext;
    RecyclerView chatRecyclerView;
    public static final int VIEW_TYPE_SELF_TEXT_CHAT = 10;
    public static final int VIEW_TYPE_FRIEND_TEXT_CHAT = 20;

    public ChatRoomAdapter(Context context, RecyclerView chatRecyclerView) {
        mContext = context;
        this.chatRecyclerView = chatRecyclerView;
        messageList = new ArrayList<ChatData>();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == VIEW_TYPE_SELF_TEXT_CHAT) {
            view = LayoutInflater.from(mContext).inflate(R.layout.recycler_view_self_chat, parent, false);
            return new SelfChatViewHolder(view);
        }  else if (viewType == VIEW_TYPE_FRIEND_TEXT_CHAT) {
            view = LayoutInflater.from(mContext).inflate(R.layout.recycler_view_friend_chat, parent, false);
            return new FriendChatViewHolder(view);
        }

        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == VIEW_TYPE_SELF_TEXT_CHAT) {
            ChatData chatData = (ChatData) messageList.get(position);
            ((SelfChatViewHolder) holder).bindToSelf(chatData);
        } else if (getItemViewType(position) == VIEW_TYPE_FRIEND_TEXT_CHAT) {
            ChatData chatData = (ChatData) messageList.get(position);
            ((FriendChatViewHolder) holder).bindToSelf(chatData);
        }
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
        Log.v("teees","yes");
        chatRecyclerView.smoothScrollToPosition(messageList.size());
        notifyDataSetChanged();
    }

    public void clearItem() {
        messageList.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if (Singleton.getInstance().getAccount().equals(((ChatData) messageList.get(position)).getSender())) {
            return VIEW_TYPE_SELF_TEXT_CHAT;
        } else {
            return VIEW_TYPE_FRIEND_TEXT_CHAT;
        }
    }

    class SelfChatViewHolder extends RecyclerView.ViewHolder {
        TextView txtContent;

        public SelfChatViewHolder(@NonNull View itemView) {
            super(itemView);

        }

        public void bindToSelf(ChatData chatData) {
            txtContent = (TextView) itemView.findViewById(R.id.txtContent);
            txtContent.setText(chatData.getMessage());

        }
    }

    class FriendChatViewHolder extends RecyclerView.ViewHolder {
        ImageView imgHeader;
        TextView txtContent;

        public FriendChatViewHolder(@NonNull View itemView) {
            super(itemView);
            imgHeader = (ImageView) itemView.findViewById(R.id.imgHeader);
            txtContent = (TextView) itemView.findViewById(R.id.txtContent);
        }

        public void bindToSelf(ChatData chatData) {
            try {
                txtContent.setText(chatData.getMessage());
            } catch (Exception e) {
                Log.v("error", e.toString());
            }
            imgHeader.setImageResource(chatData.getIcon());
            txtContent.setText(chatData.getMessage());
        }
    }


}
