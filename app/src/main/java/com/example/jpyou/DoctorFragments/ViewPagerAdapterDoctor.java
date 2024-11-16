package com.example.jpyou.DoctorFragments;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewPagerAdapterDoctor extends FragmentStateAdapter {
    public ViewPagerAdapterDoctor(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new ScheduleDoctorFragment();
            case 1:
                return new ExamineDoctorFragment();
            case 2:
                return new MedicationDoctorFragment();
            case 3:
                return new ProfileDoctorFragment();
            default:
                return new ScheduleDoctorFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
