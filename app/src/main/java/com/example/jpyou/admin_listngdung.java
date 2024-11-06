package com.example.jpyou;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;


import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

public class admin_listngdung extends AppCompatActivity {
    private ListView lv;
    private MyDatabaseHelper db;
    Button btn_xoa, btnCapNhat;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.admin_listngdung);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        lv = findViewById(R.id.list_ngDung);
        db = new MyDatabaseHelper(this); // Initialize the database helper

        // Retrieve the list of users from the database
        List<String> users = db.getAllUser("1"); // Replace "1" with the desired vaitroID
        if (users.isEmpty()) {
            Log.d("User List", "No users found for the specified role.");
        } else {
            // Create an ArrayAdapter and set it to the ListView
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, users);
            lv.setAdapter(adapter);
        }

        btn_xoa = findViewById(R.id.btn_adminXoa);
        btn_xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
