package com.example.switchance_start;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.switchance_start.Adapter.EnvelopeAdapter;
import com.example.switchance_start.Datas.ChatData;


public class ChatList extends Fragment {
    ImageView imageView5;
    Button button2;
    EnvelopeAdapter envelopeAdapter;
    RecyclerView recyclerViewInterestedSkill;


    public ChatList() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_envelope2, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        setAdapter();
    }

    public void initView(View view) {
        recyclerViewInterestedSkill = (RecyclerView) view.findViewById(R.id.recyclerView_interestedSkill);
    }

    public void setAdapter() {
        recyclerViewInterestedSkill.setLayoutManager(new LinearLayoutManager(getActivity()));
        envelopeAdapter = new EnvelopeAdapter(getActivity());
        recyclerViewInterestedSkill.setAdapter(envelopeAdapter);

        envelopeAdapter.addItem(new ChatData("123","123",R.drawable.goose));
        envelopeAdapter.addItem(new ChatData("123","123",R.drawable.goose));
        envelopeAdapter.addItem(new ChatData("123","123",R.drawable.goose));
    }
}
