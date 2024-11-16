package com.example.jpyou.Nurse;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.example.jpyou.NurseFragments.ViewPagerAdapterNurse;
import com.example.jpyou.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class NurseInterface extends AppCompatActivity {
    private ViewPager2 viewPager;
    private BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.nurse_interface);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        viewPager = findViewById(R.id.viewPagerNurse);
        bottomNavigationView = findViewById(R.id.bottomNavigationNurse);
        ViewPagerAdapterNurse adapter = new ViewPagerAdapterNurse(this);
        viewPager.setAdapter(adapter);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.nav_nurse_schedule) {
                    viewPager.setCurrentItem(0);
                } else if (id == R.id.nav_nurse_profile) {
                    viewPager.setCurrentItem(1);
                }
                return true;
            }
        });

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                switch (position) {
                    case 0:
                        bottomNavigationView.getMenu().findItem(R.id.nav_nurse_schedule).setChecked(true);
                        break;
                    case 1:
                        bottomNavigationView.getMenu().findItem(R.id.nav_nurse_profile).setChecked(true);
                        break;
                }
            }
        });
    }
}