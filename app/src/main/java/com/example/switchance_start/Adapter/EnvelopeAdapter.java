package com.example.switchance_start.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.switchance_start.ChatList;
import com.example.switchance_start.ChatRoom;
import com.example.switchance_start.Datas.ChatData;
import com.example.switchance_start.MainActivity;
import com.example.switchance_start.R;
import com.example.switchance_start.register.InterestedExperience;
import com.example.switchance_start.register.LogInActivity;

import java.util.ArrayList;

public class EnvelopeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{


    ArrayList chatList;
    Context mContext;

    public EnvelopeAdapter(Context context) {
        mContext = context;
        chatList = new ArrayList<ChatData>();
    }

    @NonNull
    @Override
    public EnvelopeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.recycler_view_envelope, parent, false);
        return new EnvelopeAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ChatData chatData = (ChatData) chatList.get(position);
        ((ViewHolder)holder).bindToMySelf(chatData);
    }

    @Override
    public int getItemCount() {
        return chatList.size();
    }

    public ArrayList getArrayList () {
        return chatList;
    }

    public void addItem(ChatData experience) {
        chatList.add(experience);
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView img_icon;
        TextView txt_account;
        TextView txt_messenge;


        //        ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            img_icon=(ImageView)itemView.findViewById(R.id.img_icon);
            txt_account = (TextView) itemView.findViewById(R.id.txt_account);
            txt_messenge=(TextView)itemView.findViewById(R.id.txt_messenge);
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
