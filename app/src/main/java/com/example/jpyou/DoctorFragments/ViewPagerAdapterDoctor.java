package com.example.jpyou.DoctorFragments;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.jpyou.UserFragment.HistoryFragment;
import com.example.jpyou.UserFragment.HomeFragment;
import com.example.jpyou.UserFragment.ProfileFragment;

public class ViewPagerAdapterDoctor extends FragmentStateAdapter {
    public ViewPagerAdapterDoctor(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new ScheduleFragment();
            case 1:
                return new ExamineFragment();
            case 2:
                return new MedicationFragment();
            default:
                return new ScheduleFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
