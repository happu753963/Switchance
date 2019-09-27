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

public class OwnedSkillAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    ArrayList ownedskillList;
    Context mContext;

    public OwnedSkillAdapter(Context context) {
        mContext = context;
        ownedskillList = new ArrayList<OwnedSkill>();
    }

    @NonNull
    @Override
    public OwnedSkillAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.recycler_view_owned_skill, parent, false);
        return new OwnedSkillAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        OwnedSkill skill = (OwnedSkill) ownedskillList.get(position);
        ((OwnedSkillAdapter.ViewHolder)holder).bindToOwnedSkill(skill);
    }

    @Override
    public int getItemCount() {
        return ownedskillList.size();
    }

    public void addItem(OwnedSkill skill) {
        ownedskillList.add(skill);
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.txt_ownedSkill);
            imageView = (ImageView) itemView.findViewById(R.id.btn_add);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getLayoutPosition();
                    ownedskillList.remove(position);
                    notifyDataSetChanged();
                }
            });
        }

        public void bindToOwnedSkill(OwnedSkill skill) {
            textView.setText(skill.getOwnedSkill());
        }
    }
}
