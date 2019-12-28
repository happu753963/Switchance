package com.example.switchance_start;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Debug;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.switchance_start.register.RegisterActivity;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.NotNull;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Personal.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Personal#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Personal extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Personal() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Personal.
     */
    // TODO: Rename and change types and number of parameters
    public static Personal newInstance(String param1, String param2) {
        Personal fragment = new Personal();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_personal2, container, false);
    }


private static final String TAG = Personal.class.getSimpleName();
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        //從內部註冊或登入時儲存的資料讀取檔案
        TextView name;
        name = getActivity().findViewById(R.id.txt_name);
        final String account;
        final String[] img_icon = new String[1];   //icon碼



        SharedPreferences preferences_register = this.getActivity().getSharedPreferences("Register", Context.MODE_PRIVATE);
        SharedPreferences preferences_login = this.getActivity().getSharedPreferences("Log_in", Context.MODE_PRIVATE);
        String check = preferences_register.getString("CHECK","");
        if (check.matches("0")) {       //若是註冊進入APP
            account = preferences_register.getString("ACCOUNT", "nothing"); //取得標籤”ACCOUNT”的設定值，getString方法的第二個參數是預設值(default)，當讀取不到或設定檔內無該設定值時，會傳回這個預設值
            name.setText(account);
        }
        else {                      //若是登入進入APP
            account = preferences_login.getString("ACCOUNT", "nothing");
            name.setText(account);
        }

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getInstance().getReferenceFromUrl("https://switchance-e8900.firebaseio.com");


        ListView ListView_interestedSkill = getActivity().findViewById(R.id.ListView_interestedSkill);
        ListView ListView_interestedExperience = getActivity().findViewById(R.id.ListView_interestedExperience);
        ListView ListView_interestedItem = getActivity().findViewById(R.id.ListView_interestedItem);
        ListView ListView_ownedSkill = getActivity().findViewById(R.id.ListView_ownedSkill);
        ListView ListView_ownedExperience = getActivity().findViewById(R.id.ListView_ownedExperience);
        ListView ListView_ownedItem = getActivity().findViewById(R.id.ListView_ownedItem);

        //建立好adapter
        final ArrayAdapter<String> adapter_interestedExperience = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1);
        final ArrayAdapter<String> adapter_interestedSkill = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1);
        final ArrayAdapter<String> adapter_interestedItem = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1);
        final ArrayAdapter<String> adapter_ownedExperience = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1);
        final ArrayAdapter<String> adapter_ownedSkill = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1);
        final ArrayAdapter<String> adapter_ownedItem = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1);

        //把adapter放進ListView
        ListView_interestedSkill.setAdapter(adapter_interestedSkill);
        ListView_interestedItem.setAdapter(adapter_interestedItem);
        ListView_interestedExperience.setAdapter(adapter_interestedExperience);
        ListView_ownedSkill.setAdapter(adapter_ownedSkill);
        ListView_ownedItem.setAdapter(adapter_ownedItem);
        ListView_ownedExperience.setAdapter(adapter_ownedExperience);

        //清空adapter
        adapter_interestedExperience.clear();
        adapter_interestedSkill.clear();
        adapter_interestedItem.clear();
        adapter_ownedSkill.clear();
        adapter_ownedItem.clear();
        adapter_ownedExperience.clear();

        final ImageView icon = getActivity().findViewById(R.id.img_icon);


        //把firebase資料加進adapter，以及隨時更新資料
        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NotNull DataSnapshot dataSnapshot, @NotNull String s) {
                for (int num = 0 ; num < 10 ; num++) {
                    if (String.valueOf(dataSnapshot.child(account).child("interestedExperience").child(String.valueOf(num)).child("interestedExperience").getValue()) != "null") {
                        adapter_interestedExperience.add(String.valueOf(dataSnapshot.child(account).child("interestedExperience").child(String.valueOf(num)).child("interestedExperience").getValue()));
                    }
                    if (String.valueOf(dataSnapshot.child(account).child("interestedSkill").child(String.valueOf(num)).child("interestedSkill").getValue()) != "null") {
                        adapter_interestedSkill.add(String.valueOf(dataSnapshot.child(account).child("interestedSkill").child(String.valueOf(num)).child("interestedSkill").getValue()));
                    }
                    if (String.valueOf(dataSnapshot.child(account).child("interestedItem").child(String.valueOf(num)).child("interestedItem").getValue()) != "null") {
                        adapter_interestedItem.add(String.valueOf(dataSnapshot.child(account).child("interestedItem").child(String.valueOf(num)).child("interestedItem").getValue()));
                    }
                    if (String.valueOf(dataSnapshot.child(account).child("ownedExperience").child(String.valueOf(num)).child("ownedExperience").getValue()) != "null") {
                        adapter_ownedExperience.add(String.valueOf(dataSnapshot.child(account).child("ownedExperience").child(String.valueOf(num)).child("ownedExperience").getValue()));
                    }
                    if (String.valueOf(dataSnapshot.child(account).child("ownedSkill").child(String.valueOf(num)).child("ownedSkill").getValue()) != "null") {
                        adapter_ownedSkill.add(String.valueOf(dataSnapshot.child(account).child("ownedSkill").child(String.valueOf(num)).child("ownedSkill").getValue()));
                    }
                    if (String.valueOf(dataSnapshot.child(account).child("ownedItem").child(String.valueOf(num)).child("ownedItem").getValue()) != "null") {
                        adapter_ownedItem.add(String.valueOf(dataSnapshot.child(account).child("ownedItem").child(String.valueOf(num)).child("ownedItem").getValue()));
                    }

                }
//                Log.d("count",String.valueOf(adapter.getItemViewType()));
                img_icon[0] = String.valueOf(dataSnapshot.child(account).child("icon").getValue());      //firebase抓icon資料
                switch (img_icon[0]){
                    case "2131165282":  //雞
                        icon.setImageResource(R.drawable.chicken);
                        break;
                    case "2131165280":  //變色龍
                        icon.setImageResource(R.drawable.chameleon);
                        break;
                    case "2131165324":  //獅子
                        icon.setImageResource(R.drawable.lion);
                        break;
                    case "2131165315":  //刺蝟
                        icon.setImageResource(R.drawable.hedgehog);
                        break;
                    case "2131165314":  //鵝
                        icon.setImageResource(R.drawable.goose);
                        break;
                    case "0":  //狐狸
                        icon.setImageResource(R.drawable.fox);
                        break;

                }
                Log.d("~~~~~~~~~~~~~~~~~~~~~~~~~~~~`img_icon:",String.valueOf(img_icon[0]));
            }

            @Override
            public void onChildChanged(@NotNull DataSnapshot dataSnapshot, @NotNull String s) {
                adapter_interestedExperience.clear();
                adapter_interestedSkill.clear();
                adapter_interestedItem.clear();
                adapter_ownedSkill.clear();
                adapter_ownedItem.clear();
                adapter_ownedExperience.clear();
                for (int num = 0; num < 10; num++) {
                    if (String.valueOf(dataSnapshot.child(account).child("interestedExperience").child(String.valueOf(num)).child("interestedExperience").getValue()) != "null") {
                        adapter_interestedExperience.add(String.valueOf(dataSnapshot.child(account).child("interestedExperience").child(String.valueOf(num)).child("interestedExperience").getValue()));
                    }
                    if (String.valueOf(dataSnapshot.child(account).child("interestedSkill").child(String.valueOf(num)).child("interestedSkill").getValue()) != "null") {
                        adapter_interestedSkill.add(String.valueOf(dataSnapshot.child(account).child("interestedSkill").child(String.valueOf(num)).child("interestedSkill").getValue()));
                    }
                    if (String.valueOf(dataSnapshot.child(account).child("interestedItem").child(String.valueOf(num)).child("interestedItem").getValue()) != "null") {
                        adapter_interestedItem.add(String.valueOf(dataSnapshot.child(account).child("interestedItem").child(String.valueOf(num)).child("interestedItem").getValue()));
                    }
                    if (String.valueOf(dataSnapshot.child(account).child("ownedExperience").child(String.valueOf(num)).child("ownedExperience").getValue()) != "null") {
                        adapter_ownedExperience.add(String.valueOf(dataSnapshot.child(account).child("ownedExperience").child(String.valueOf(num)).child("ownedExperience").getValue()));
                    }
                    if (String.valueOf(dataSnapshot.child(account).child("ownedSkill").child(String.valueOf(num)).child("ownedSkill").getValue()) != "null") {
                        adapter_ownedSkill.add(String.valueOf(dataSnapshot.child(account).child("ownedSkill").child(String.valueOf(num)).child("ownedSkill").getValue()));
                    }
                    if (String.valueOf(dataSnapshot.child(account).child("ownedItem").child(String.valueOf(num)).child("ownedItem").getValue()) != "null") {
                        adapter_ownedItem.add(String.valueOf(dataSnapshot.child(account).child("ownedItem").child(String.valueOf(num)).child("ownedItem").getValue()));
                    }
                }

            }
            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




    }









    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
