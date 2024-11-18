package com.example.jpyou.NurseFragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.jpyou.Nurse.nurse_listSignin;
import com.example.jpyou.R;
import com.example.jpyou.User.user_schedule_appointment;


public class ProfileNurseFragment extends Fragment {
    private String tkID;


    public ProfileNurseFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nurse_profile, container, false);

        if (getArguments() != null) {
            tkID = getArguments().getString("TaiKhoanID");  // Lấy tkID từ Bundle
        }


        Button bt1 = view.findViewById(R.id.btn_dsYTa);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), nurse_listSignin.class);
                intent.putExtra("TaiKhoanID", tkID);
                startActivity(intent);
            }
        });


        return view;
    }
}