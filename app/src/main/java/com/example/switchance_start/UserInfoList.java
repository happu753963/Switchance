package com.example.switchance_start;

import com.example.switchance_start.model.UserInfo;
import com.example.switchance_start.register.OwnedSkill;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class UserInfoList extends ArrayAdapter<UserInfo> {
    private Activity context;
    List<UserInfo> userInfos;

    public UserInfoList(Activity context, List<UserInfo> userInfos) {
        super(context, R.layout.layout_userinfo_list, userInfos);
        this.context = context;
        this.userInfos = userInfos;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.layout_userinfo_list, null, true);

        ImageView imageViewIcon = (ImageView) listViewItem.findViewById((R.id.imageViewIcon));
        TextView textViewName = (TextView) listViewItem.findViewById(R.id.textViewName);
        TextView textViewDepartment = (TextView) listViewItem.findViewById(R.id.textViewDepartment);
        GridView gridViewOwnedSkill = (GridView) listViewItem.findViewById((R.id.gridViewOwnedSkill));

        UserInfo userInfo = userInfos.get(position);
        switch (userInfo.getIcon()) {
            case R.drawable.chameleon:
                imageViewIcon.setImageResource(R.drawable.chameleon);
                break;
            case R.drawable.chicken:
                imageViewIcon.setImageResource(R.drawable.chicken);
                break;
            case R.drawable.fox:
                imageViewIcon.setImageResource(R.drawable.fox);
                break;
            case R.drawable.goose:
                imageViewIcon.setImageResource(R.drawable.goose);
                break;
            case R.drawable.lion:
                imageViewIcon.setImageResource(R.drawable.lion);
                break;
            case R.drawable.hedgehog:
                imageViewIcon.setImageResource(R.drawable.hedgehog);
                break;
        }
        textViewName.setText(userInfo.getName());
        textViewDepartment.setText(userInfo.getDepartment());
        gridViewOwnedSkill.setAdapter(new ArrayAdapter<String>(this.context, R.layout.layout_simple_list, userInfo.getOwnedSkillList()));

        return listViewItem;
    }
}
