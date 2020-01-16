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

public class PersonalInterestedOwnedAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    ArrayList ownedExperienceList;
    Context mContext;

    public PersonalInterestedOwnedAdapter(Context context) {
        mContext = context;
        ownedExperienceList = new ArrayList<OwnedExperience>();
    }

    @NonNull
    @Override
    public PersonalInterestedOwnedAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.recycler_view_owned_personal_experience, parent, false);
        return new PersonalInterestedOwnedAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        OwnedExperience ownedExperience = (OwnedExperience) ownedExperienceList.get(position);
        ((PersonalInterestedOwnedAdapter.ViewHolder)holder).bindToInterestedExperience(ownedExperience);
    }

    @Override
    public int getItemCount() {
        return ownedExperienceList.size();
    }

    public ArrayList getArrayList () {
        return ownedExperienceList;
    }

    public void addItem(OwnedExperience experience) {
        ownedExperienceList.add(experience);
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

        public void bindToInterestedExperience(OwnedExperience ownedExperience) {
            if(ownedExperience.getType().equals("ownedSkill")) {
                layoutBackground.setBackgroundResource(R.color.colorSkillLight);
            } else if(ownedExperience.getType().equals("ownedExperience")){
                layoutBackground.setBackgroundResource(R.color.colorSkillMiddle);
            } else if(ownedExperience.getType().equals("ownedItem")){
                layoutBackground.setBackgroundResource(R.color.colorSkillDark);
            }
            textView.setText(ownedExperience.getOwnedExperience());
        }
    }
}