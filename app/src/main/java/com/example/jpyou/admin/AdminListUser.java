package com.example.jpyou.admin;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.jpyou.MyDatabaseHelper;
import com.example.jpyou.R;

import java.util.List;

public class AdminListUser extends AppCompatActivity {
    private ListView lv;
    private MyDatabaseHelper db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.list_user_admin);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        lv = findViewById(R.id.list_ngDung);
        db = new MyDatabaseHelper(this); // Initialize the database helper

        // Retrieve the list of users from the database
        List<String> users = db.getAllUser("NguoiDangKi"); // Replace "1" with the desired vaitroID
        if (users.isEmpty()) {
            Toast.makeText(this, "Lỗi", Toast.LENGTH_LONG).show();
        } else {
            // Create an ArrayAdapter and set it to the ListView
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, users);
            lv.setAdapter(adapter);
        }

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedUser = users.get(position);

                // Show options to update or delete the user
                showOptionsDialog(selectedUser, position);
            }
        });
    }

    private void showOptionsDialog(String selectedUser, int position) {
        db = new MyDatabaseHelper(this); // Initialize the database helper

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Chọn việc cần làm với người dùng này");
        builder.setItems(new CharSequence[]{"Dừng hoạt động", "Cập nhật thông tin"}, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        break;
                    case 1: // Update User

                        break;
                }
            }
        });
        builder.show();
    }


}
