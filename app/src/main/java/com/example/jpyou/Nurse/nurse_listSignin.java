package com.example.jpyou.Nurse;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.jpyou.MyDatabaseHelper;
import com.example.jpyou.R;

import java.util.List;

public class nurse_listSignin extends AppCompatActivity {
    private ListView lv;
    private MyDatabaseHelper db;
    private List<String> users;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.nurse_listsignin);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        lv = findViewById(R.id.dsDkBenh);
        db = new MyDatabaseHelper(this); // Initialize the database helper
        users = db.dsDangKiKham();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, users);
        lv.setAdapter(adapter); // Update the ListView with new data
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedUser = users.get(position);
                showOptionsDialog(selectedUser, position);
            }
        });

    }

    private void showOptionsDialog(String selectedUser, int position) {
        db = new MyDatabaseHelper(this); // Initialize the database helper

        String numberStr = selectedUser.substring(selectedUser.indexOf("ID: ") + 4, selectedUser.indexOf(",")).trim();
        String khoa = selectedUser.substring(selectedUser.indexOf("Khoa: ") + 6).trim();

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Chọn việc cần làm với người dùng này");
        builder.setItems(new CharSequence[]{"Chọn bác sĩ", "Đóng"}, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0: {
                        Intent intent = new Intent(nurse_listSignin.this, nurse_listbacsi.class);
                        intent.putExtra("ytaID", getIntent().getStringExtra("TaiKhoanID"));
                        intent.putExtra("benhnhanID", numberStr);
                        intent.putExtra("khoa", khoa);
                        startActivity(intent);
                    }
                    break;
                    case 1:
                        break;
                }
            }
        });
        builder.show();

    }
}
