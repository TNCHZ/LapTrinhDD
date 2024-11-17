package com.example.jpyou.UserFragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.fragment.app.Fragment;

import com.example.jpyou.R;
import com.example.jpyou.User.user_schedule_appointment;

public class HomeUserFragment extends Fragment {
    private String tkID;

    public HomeUserFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the fragment's layout
        View view = inflater.inflate(R.layout.fragment_user_home, container, false);

        // Lấy các Button từ layout của Fragment
        Button button1 = view.findViewById(R.id.btn_datKham);
        Button button2 = view.findViewById(R.id.button4);
        Button button3 = view.findViewById(R.id.button6);
        Button button4 = view.findViewById(R.id.button3);
        Button button5 = view.findViewById(R.id.button5);

        if (getArguments() != null) {
            tkID = getArguments().getString("TaiKhoanID");  // Lấy tkID từ Bundle
        }


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), user_schedule_appointment.class);
                intent.putExtra("TaiKhoanID", tkID);
                startActivity(intent);
            }
        });

        return view;
    }
}
