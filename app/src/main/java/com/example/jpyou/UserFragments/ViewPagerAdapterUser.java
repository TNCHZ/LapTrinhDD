package com.example.jpyou.UserFragments;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewPagerAdapterUser extends FragmentStateAdapter {
    private String tkID;  // Store the tkID passed from UserInterface

    // Constructor to receive tkID
    public ViewPagerAdapterUser(@NonNull FragmentActivity fragmentActivity, String tkID) {
        super(fragmentActivity);
        this.tkID = tkID;  // Save tkID to use in fragment creation
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragment;

        // Create the correct fragment based on the position
        switch (position) {
            case 0:
                fragment = new HomeUserFragment();
                break;
            case 1:
                fragment = new ScheduleUserFragment();
                break;
            case 2:
                fragment = new ProfileUserFragment();
                break;
            default:
                fragment = new HomeUserFragment();
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
    public int getItemCount() {
        return 3;  // Number of fragments in the ViewPager
    }
}
