package com.example.jpyou.NurseFragments;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewPagerAdapterNurse extends FragmentStateAdapter {
    public ViewPagerAdapterNurse(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new ScheduleNurseFragment();
            case 1:
                return new ProfileNurseFragment();
            default:
                return new ScheduleNurseFragment();
        }
    }

    @Override
    public int getItemCount() { return 2; }
}
