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

import java.util.ArrayList;

public class OtherUserInterestedExperienceAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    ArrayList interestedExperienceList;
    Context mContext;

    public OtherUserInterestedExperienceAdapter(Context context) {
        mContext = context;
        interestedExperienceList = new ArrayList<InterestedExperience>();
    }

    @NonNull
    @Override
    public OtherUserInterestedExperienceAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.recycler_view_owned_personal_experience, parent, false);
        return new OtherUserInterestedExperienceAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        InterestedExperience experience = (InterestedExperience) interestedExperienceList.get(position);
        ((OtherUserInterestedExperienceAdapter.ViewHolder)holder).bindToInterestedExperience(experience);
    }

    @Override
    public int getItemCount() {
        return interestedExperienceList.size();
    }

    public ArrayList getArrayList () {
        return interestedExperienceList;
    }

    public void addItem(InterestedExperience experience) {
        interestedExperienceList.add(experience);
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        LinearLayout layoutBackground;
//        ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.txt_interestedExperience);
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

        public void bindToInterestedExperience(InterestedExperience experience) {
            if(experience.getType().equals("ownedSkill")) {
                layoutBackground.setBackgroundResource(R.color.colorSkillLight);
            } else if(experience.getType().equals("ownedExperience")){
                layoutBackground.setBackgroundResource(R.color.colorSkillMiddle);
            } else if(experience.getType().equals("ownedItem")){
                layoutBackground.setBackgroundResource(R.color.colorSkillDark);
            }
            textView.setText(experience.getInterestedExperience());
        }
    }
}