package com.example.jpyou.UserFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.fragment.app.Fragment;

import com.example.jpyou.R;

public class HomeUserFragment extends Fragment {

    public HomeUserFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the fragment's layout
        View view = inflater.inflate(R.layout.fragment_user_home, container, false);

        // Lấy các Button từ layout của Fragment
        Button button1 = view.findViewById(R.id.button);
        Button button2 = view.findViewById(R.id.button4);
        Button button3 = view.findViewById(R.id.button6);
        Button button4 = view.findViewById(R.id.button3);
        Button button5 = view.findViewById(R.id.button5);

        return view;
    }
}
