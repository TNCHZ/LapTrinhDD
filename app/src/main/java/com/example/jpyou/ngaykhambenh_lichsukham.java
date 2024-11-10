package com.example.jpyou;

import android.os.Bundle;
import android.widget.CalendarView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Calendar;

public class ngaykhambenh_lichsukham extends AppCompatActivity {
    CalendarView clv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.ngaykhambenh_lichsukham);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        clv = findViewById(R.id.lichkham);

        // Lấy ngày hiện tại
        Calendar calendar = Calendar.getInstance();
        long currentDate = calendar.getTimeInMillis();

        // Đặt ngày hiện tại cho CalendarView
        clv.setDate(currentDate, true, true);
    }
}
