package com.example.switchance_start.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.switchance_start.OtherUser;
import com.example.switchance_start.R;
import com.example.switchance_start.model.UserInfo;
import com.example.switchance_start.register.InterestedExperience;
import com.example.switchance_start.register.InterestedItem;
import com.example.switchance_start.register.InterestedSkill;
import com.example.switchance_start.register.OwnedExperience;
import com.example.switchance_start.register.OwnedItem;
import com.example.switchance_start.register.OwnedSkill;

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

    public void clearItem() {
        userArrayList.clear();
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView txtAccount;
        TextView txtDepartment;
        ImageView imgIcon;
        RecyclerView recyclerView;
        LogoItemAdapter logoItemAdapter;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            imgIcon = (ImageView) itemView.findViewById(R.id.img_icon);
            txtAccount = (TextView) itemView.findViewById(R.id.txt_account);
            txtDepartment = (TextView) itemView.findViewById(R.id.txt_department);
            recyclerView = (RecyclerView) itemView.findViewById(R.id.logo_item_recyclerview);


            logoItemAdapter = new LogoItemAdapter(mContext);
            recyclerView.setAdapter(logoItemAdapter);
            recyclerView.setLayoutManager(new GridLayoutManager(mContext,3));
        }

        public void bindToInterestedExperience(UserInfo userInfo) {
            imgIcon.setImageResource(userInfo.getIcon());
            txtAccount.setText(userInfo.getAccount());
            txtDepartment.setText(userInfo.getDepartment());

            setOwnedItem(userInfo,logoItemAdapter);

        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.putExtra("userInfo", (UserInfo) userArrayList.get(getLayoutPosition()));
            intent.setClass(mContext, OtherUser.class);
            mContext.startActivity(intent);
        }
    }

    public void setOwnedItem(UserInfo userInfo,LogoItemAdapter logoItemAdapter){
        logoItemAdapter.clearItem();
        for(int i=0;i<userInfo.getOwnedSkill().size();i++){
            OwnedSkill ownedSkill  = userInfo.getOwnedSkill().get(i);
            logoItemAdapter.addItem(new OwnedExperience(ownedSkill.getOwnedSkill(),"ownedSkill"));
        }

        for(int i=0;i<userInfo.getOwnedExperience().size();i++){
            OwnedExperience ownedExperience  = userInfo.getOwnedExperience().get(i);
            logoItemAdapter.addItem(new OwnedExperience(ownedExperience.getOwnedExperience(),"ownedExperience"));
        }

        for(int i=0;i<userInfo.getOwnedItem().size();i++){
            OwnedItem ownedItem  = userInfo.getOwnedItem().get(i);
            logoItemAdapter.addItem(new OwnedExperience(ownedItem.getOwnedItem(),"ownedItem"));
        }
    }
}