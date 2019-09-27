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

public class InterestedItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    ArrayList interestedItemList;
    Context mContext;

    public InterestedItemAdapter(Context context) {
        mContext = context;
        interestedItemList = new ArrayList<InterestedItem>();
    }

    @NonNull
    @Override
    public InterestedItemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.recycler_view_interested_item, parent, false);
        return new InterestedItemAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        InterestedItem item = (InterestedItem) interestedItemList.get(position);
        ((InterestedItemAdapter.ViewHolder)holder).bindToInterestedItem(item);
    }

    @Override
    public int getItemCount() {
        return interestedItemList.size();
    }

    public void addItem(InterestedItem item) {
        interestedItemList.add(item);
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.txt_interestedItem);
            imageView = (ImageView) itemView.findViewById(R.id.btn_add);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getLayoutPosition();
                    interestedItemList.remove(position);
                    notifyDataSetChanged();
                }
            });
        }

        public void bindToInterestedItem(InterestedItem item) {
            textView.setText(item.getInterestedItem());
        }
    }
}
