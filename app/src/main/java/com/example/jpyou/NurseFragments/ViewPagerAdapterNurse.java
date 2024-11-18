package com.example.jpyou.NurseFragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewPagerAdapterNurse extends FragmentStateAdapter {
    private String tkID;  //

    public ViewPagerAdapterNurse(@NonNull FragmentActivity fragmentActivity, String tkID) {
        super(fragmentActivity);

    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragment;

        switch (position){
            case 0:
                fragment =  new ScheduleNurseFragment();
                break;
            case 1:
                fragment = new ProfileNurseFragment();
                break;
            default:
                fragment =  new ScheduleNurseFragment();
                break;
        }

        // Pass tkID to the fragment using a Bundle
        if (fragment != null) {
            Bundle bundle = new Bundle();
            bundle.putString("TaiKhoanID", tkID);  // Put tkID in the Bundle
            fragment.setArguments(bundle);  // Set the arguments for the fragment
        }

        return fragment;
    }

    @Override
    public int getItemCount() { return 2; }
}
