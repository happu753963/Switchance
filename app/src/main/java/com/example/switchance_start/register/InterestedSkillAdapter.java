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

public class InterestedSkillAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    ArrayList interestedskillList;
    Context mContext;

    public InterestedSkillAdapter(Context context) {
        mContext = context;
        interestedskillList = new ArrayList<InterestedSkill>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.recycler_view_interested_skill, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        InterestedSkill skill = (InterestedSkill) interestedskillList.get(position);
        ((ViewHolder)holder).bindToInterestedSkill(skill);
    }

    @Override
    public int getItemCount() {
        return interestedskillList.size();
    }

    public ArrayList getArrayList () {
        return interestedskillList;
    }

    public void addItem(InterestedSkill skill) {
        interestedskillList.add(skill);
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.txt_interestedSkill);
            imageView = (ImageView) itemView.findViewById(R.id.btn_add);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getLayoutPosition();
                    interestedskillList.remove(position);
                    notifyDataSetChanged();
                }
            });
        }

        public void bindToInterestedSkill(InterestedSkill skill) {
            textView.setText(skill.getInterestedSkill());
        }
    }
}
