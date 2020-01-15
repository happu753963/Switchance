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

import com.example.switchance_start.OtherUser;
import com.example.switchance_start.R;
import com.example.switchance_start.model.UserInfo;

import java.util.ArrayList;

public class LogoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    ArrayList userArrayList;
    Context mContext;

    public LogoAdapter(Context context) {
        mContext = context;
        userArrayList = new ArrayList<UserInfo>();
    }

    @NonNull
    @Override
    public LogoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_logo, parent, false);
        return new LogoAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        UserInfo userInfo = (UserInfo) userArrayList.get(position);
        ((LogoAdapter.ViewHolder) holder).bindToInterestedExperience(userInfo);
    }

    @Override
    public int getItemCount() {
        return userArrayList.size();
    }

    public ArrayList getArrayList() {
        return userArrayList;
    }

    public void addItem(UserInfo userInfo) {
        userArrayList.add(userInfo);
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView txtAccount;
        TextView txtDepartment;
        ImageView imgIcon;
        RecyclerView recyclerView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            imgIcon = (ImageView) itemView.findViewById(R.id.img_icon);
            txtAccount = (TextView) itemView.findViewById(R.id.txt_account);
            txtDepartment = (TextView) itemView.findViewById(R.id.txt_department);
            recyclerView = (RecyclerView) itemView.findViewById(R.id.recyclerview);
        }

        public void bindToInterestedExperience(UserInfo userInfo) {
            imgIcon.setImageResource(userInfo.getIcon());
            txtAccount.setText(userInfo.getAccount());
            txtDepartment.setText(userInfo.getDepartment());
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.putExtra("userInfo", (UserInfo) userArrayList.get(getLayoutPosition()));
            intent.setClass(mContext, OtherUser.class);
            mContext.startActivity(intent);
        }
    }
}