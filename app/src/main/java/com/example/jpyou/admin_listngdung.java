package com.example.jpyou;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;


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
        builder.setItems(new CharSequence[]{"Xóa người dùng", "Cập nhật thông tin"}, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0: {
                        String[] parts = selectedUser.split(" ", 2);
                        int selectedUserId = Integer.parseInt(parts[0]); // Parse ID
                        if (db.isDelete_user(selectedUserId)) { // Assuming delete_user returns true if successful
                            Toast.makeText(admin_listngdung.this, "Người dùng đã được xóa", Toast.LENGTH_SHORT).show();
                            recreate(); // Restart activity to reload data
                        } else {
                            Toast.makeText(admin_listngdung.this, "Không thể xóa người dùng", Toast.LENGTH_SHORT).show();
                        }
                    }
                    case 1: // Update User

                        break;
                }
            }
        });
        builder.show();
    }


}
