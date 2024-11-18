package com.example.jpyou.User;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.jpyou.MyDatabaseHelper;
import com.example.jpyou.R;

import java.util.Calendar;

public class user_schedule_appointment extends AppCompatActivity {
    private static final String TAG = "UserScheduleAppointment";  // Log tag for better identification
    private String[] khoa = {"Tai, Mũi, Họng", "Mắt", "Răng hàm mặt", "Phụ Sản", "Cơ, Xương, Khớp"};
    private Spinner sp_role;
    private String khoaChon;
    private String ngayChon;
    private Button bt;
    private CalendarView clv;
    private TextView txtTime;
    private MyDatabaseHelper db;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.schedule_appointment);

        // Adjust window padding to handle system bars (notch, navigation)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        db = new MyDatabaseHelper(this);

        // Initialize calendar view and set the minimum date to today
        clv = findViewById(R.id.calendarView_lichKham);
        Calendar calendar = Calendar.getInstance();
        long today = calendar.getTimeInMillis();
        clv.setMinDate(today);

        ngayChon = calendar.get(Calendar.DAY_OF_MONTH) + "/" + (calendar.get(Calendar.MONTH) + 1) + "/" + calendar.get(Calendar.YEAR);


        // Set listener for date selection
        clv.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            Calendar selectedDate = Calendar.getInstance();
            selectedDate.set(year, month, dayOfMonth);
            ngayChon = dayOfMonth + "/" + (month + 1) + "/" + year; // Format the date
            Log.d(TAG, "Selected date: " + ngayChon);  // Log the selected date
        });

        // Set up spinner for choosing department
        sp_role = findViewById(R.id.spn_khoa);
        ArrayAdapter<String> adapter_role = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, khoa);
        adapter_role.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_role.setAdapter(adapter_role);

        // Listener for spinner selection
        sp_role.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                khoaChon = parent.getItemAtPosition(position).toString(); // Get selected department
                Log.d(TAG, "Selected department: " + khoaChon);  // Log the selected department
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                khoaChon = null; // Default to null if nothing selected
            }
        });

        // Initialize the time input and appointment button
        txtTime = findViewById(R.id.txt_thoiGianKham);
        bt = findViewById(R.id.btn_dat);

        // Set button click listener for scheduling the appointment
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Validate date selection
                if (ngayChon == null || ngayChon.isEmpty()) {
                    Log.d(TAG, "Date not selected. Please select a date.");
                    return;
                }

                // Validate time input
                String time = txtTime.getText().toString();
                if (time.isEmpty()) {
                    Log.d(TAG, "Time not entered. Please enter a time.");
                    return;
                }

                // Validate TaiKhoanID from Intent
                String taiKhoanID = getIntent().getStringExtra("TaiKhoanID");
                if (taiKhoanID == null || taiKhoanID.isEmpty()) {
                    Log.d(TAG, "TaiKhoanID missing or invalid.");
                    return;
                }

                int userId = Integer.parseInt(taiKhoanID);

                // Ensure khoaChon is selected
                if (khoaChon == null || khoaChon.isEmpty()) {
                    Log.d(TAG, "Department not selected. Please select a department.");
                    return;
                }

                // Call database method to save appointment
                db.taoLichHen(db.getBenhNhanID(userId), ngayChon, time, khoaChon);

                // Log success message
                Log.d(TAG, "Appointment scheduled successfully. User ID: " + userId + ", Date: " + ngayChon + ", Time: " + time + ", Department: " + khoaChon);
            }
        });
    }
}
