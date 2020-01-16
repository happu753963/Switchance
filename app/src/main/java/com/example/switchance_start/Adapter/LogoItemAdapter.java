package com.example.switchance_start.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.switchance_start.R;
import com.example.switchance_start.register.InterestedExperience;
import com.example.switchance_start.register.OwnedExperience;

import java.util.ArrayList;

public class LogoItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    ArrayList logoItemList;
    Context mContext;

    public LogoItemAdapter(Context context) {
        mContext = context;
        logoItemList = new ArrayList<OwnedExperience>();
    }

    @NonNull
    @Override
    public LogoItemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_logo_item, parent, false);
        return new LogoItemAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        OwnedExperience experience = (OwnedExperience) logoItemList.get(position);
        ((LogoItemAdapter.ViewHolder)holder).bindToOwnedExperience(experience);
    }

    @Override
    public int getItemCount() {
        return logoItemList.size();
    }

    public ArrayList getArrayList () {
        return logoItemList;
    }

    public void addItem(OwnedExperience experience) {
        logoItemList.add(experience);
        notifyDataSetChanged();
    }

    public void clearItem() {
        logoItemList.clear();
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        LinearLayout layoutBackground;
//        ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.txt_ownedExperience);
            layoutBackground = (LinearLayout)itemView.findViewById(R.id.layoutBackground);
//            imageView = (ImageView) itemView.findViewById(R.id.btn_add);
//            imageView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    int position = getLayoutPosition();
//                    ownedExperienceList.remove(position);
//                    notifyDataSetChanged();
//                }
//            });
        }

        public void bindToOwnedExperience(OwnedExperience experience) {
            if(experience.getType().equals("ownedSkill")) {
                layoutBackground.setBackgroundResource(R.color.colorSkillLight);
            } else if(experience.getType().equals("ownedExperience")){
                layoutBackground.setBackgroundResource(R.color.colorSkillMiddle);
            } else if(experience.getType().equals("ownedItem")){
                layoutBackground.setBackgroundResource(R.color.colorSkillDark);
            }
            textView.setText(experience.getOwnedExperience());
        }
    }
}