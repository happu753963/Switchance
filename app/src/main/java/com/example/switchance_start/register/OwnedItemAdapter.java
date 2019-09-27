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

public class OwnedItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    ArrayList ownedItemList;
    Context mContext;

    public OwnedItemAdapter(Context context) {
        mContext = context;
        ownedItemList = new ArrayList<OwnedItem>();
    }

    @NonNull
    @Override
    public OwnedItemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.recycler_view_owned_item, parent, false);
        return new OwnedItemAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        OwnedItem item = (OwnedItem) ownedItemList.get(position);
        ((OwnedItemAdapter.ViewHolder)holder).bindToOwnedItem(item);
    }

    @Override
    public int getItemCount() {
        return ownedItemList.size();
    }

    public void addItem(OwnedItem item) {
        ownedItemList.add(item);
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.txt_ownedItem);
            imageView = (ImageView) itemView.findViewById(R.id.btn_add);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getLayoutPosition();
                    ownedItemList.remove(position);
                    notifyDataSetChanged();
                }
            });
        }

        public void bindToOwnedItem(OwnedItem item) {
            textView.setText(item.getOwnedItem());
        }
    }
}
