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

public class OwnedExperienceAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {

    ArrayList ownedExperienceList;
    Context mContext;

    public OwnedExperienceAdapter(Context context) {
        mContext = context;
        ownedExperienceList = new ArrayList<OwnedExperience>();
    }

    @NonNull
    @Override
    public OwnedExperienceAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.recycler_view_owned_experience, parent, false);
        return new OwnedExperienceAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        OwnedExperience experience = (OwnedExperience) ownedExperienceList.get(position);
        ((OwnedExperienceAdapter.ViewHolder)holder).bindToOwnedExperience(experience);
    }

    @Override
    public int getItemCount() {
        return ownedExperienceList.size();
    }

    public void addItem(OwnedExperience experience) {
        ownedExperienceList.add(experience);
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.txt_ownedExperience);
            imageView = (ImageView) itemView.findViewById(R.id.btn_add);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getLayoutPosition();
                    ownedExperienceList.remove(position);
                    notifyDataSetChanged();
                }
            });
        }

        public void bindToOwnedExperience(OwnedExperience experience) {
            textView.setText(experience.getOwnedExperience());
        }
    }
}
