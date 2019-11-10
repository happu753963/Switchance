package com.example.switchance_start;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;


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


        //從內部註冊時儲存的資料讀取檔案
        TextView name;
        name = getActivity().findViewById(R.id.txt_name);

        SharedPreferences preferences_register = this.getActivity().getSharedPreferences("Register", Context.MODE_PRIVATE);
        SharedPreferences preferences_login = this.getActivity().getSharedPreferences("Log_in", Context.MODE_PRIVATE);
        String check = preferences_register.getString("CHECK","");
        if (check.matches("0")) {       //若是註冊進入APP
            String account = preferences_register.getString("ACCOUNT", "nothing"); //取得標籤”ACCOUNT”的設定值，getString方法的第二個參數是預設值(default)，當讀取不到或設定檔內無該設定值時，會傳回這個預設值
            name.setText(account);
        }
        else {                      //若是登入進入APP
            String account = preferences_login.getString("ACCOUNT", "nothing");
            name.setText(account);
        }



        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getInstance().getReferenceFromUrl("https://switchance-e8900.firebaseio.com/");






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
