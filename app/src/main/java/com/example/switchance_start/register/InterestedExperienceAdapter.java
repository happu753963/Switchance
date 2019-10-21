package com.example.switchance_start.register;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.switchance_start.R;

import java.util.ArrayList;

public class InterestedExperienceAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    ArrayList interestedExperienceList;
    Context mContext;

    public InterestedExperienceAdapter(Context context) {
        mContext = context;
        interestedExperienceList = new ArrayList<InterestedExperience>();
    }

    @NonNull
    @Override
    public InterestedExperienceAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.recycler_view_interested_experience, parent, false);
        return new InterestedExperienceAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        InterestedExperience experience = (InterestedExperience) interestedExperienceList.get(position);
        ((InterestedExperienceAdapter.ViewHolder)holder).bindToInterestedExperience(experience);
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
        ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.txt_interestedExperience);
            imageView = (ImageView) itemView.findViewById(R.id.btn_add);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getLayoutPosition();
                    interestedExperienceList.remove(position);
                    notifyDataSetChanged();
                }
            });
        }

        public void bindToInterestedExperience(InterestedExperience experience) {
            textView.setText(experience.getInterestedExperience());
        }
    }
}
