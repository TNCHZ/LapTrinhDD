package com.example.jpyou.Nurse;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

public class nurse_listbacsi extends AppCompatActivity {
    private ListView lv;
    private MyDatabaseHelper db;
    private List<String> users;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.nurse_listdoctor);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        lv = findViewById(R.id.dsbacsi);
        db = new MyDatabaseHelper(this); // Initialize the database helper
        users = db.dsBacSiTheoKhoa(getIntent().getStringExtra("khoa"));
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, users);
        lv.setAdapter(adapter);
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

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Chọn việc cần làm với người dùng này");
        builder.setItems(new CharSequence[]{"Chọn bác sĩ này", "Đóng"}, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0: {
                        db.taoLichHenBS(numberStr, getIntent().getStringExtra("ytaID"), "1", getIntent().getStringExtra("benhnhanID"));
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
